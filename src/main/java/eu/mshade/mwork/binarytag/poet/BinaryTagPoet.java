package eu.mshade.mwork.binarytag.poet;

import eu.mshade.mwork.binarytag.BinaryTag;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.ZstdCompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ZstdListBinaryTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BinaryTagPoet {

    private final static Logger LOGGER = LoggerFactory.getLogger(BinaryTagPoet.class);

    private final BinaryTagDriver binaryTagDriver;
    private final File indexFile, dataFile;
    //private final RandomAccessFile randomAccessFile;
    private final CompoundSectionIndex compoundSectionIndex;
    private final AsynchronousFileChannel asynchronousFileChannel;

    public BinaryTagPoet(File indexFile, File dataFile, BinaryTagDriver binaryTagDriver) {
        this.binaryTagDriver = binaryTagDriver;
        this.indexFile = indexFile;
        this.dataFile = dataFile;
        try {
            //this.randomAccessFile = new RandomAccessFile(dataFile, "rw");
            this.asynchronousFileChannel = AsynchronousFileChannel.open(dataFile.toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.READ);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        compoundSectionIndex = readCompoundSectionIndex(indexFile);
    }

    public CompoundBinaryTag readCompoundBinaryTag(String key) throws IOException {
        List<SectionIndex> binaryTagIndices = compoundSectionIndex.getSectionIndices(key);
        if (binaryTagIndices == null) return null;
        Map<Integer, SectionIndex> sectionIndexByIndex = new HashMap<>();
        binaryTagIndices.forEach(sectionIndex -> sectionIndexByIndex.put(sectionIndex.getIndex(), sectionIndex));

        ByteBuffer payload = ByteBuffer.allocate(getSizeOfSectionIndex(binaryTagIndices));
        int offset = 0;
        for (int i = 0; i < binaryTagIndices.size(); i++) {
            SectionIndex sectionIndex = sectionIndexByIndex.get(i);
            try {
                ByteBuffer byteBuffer = ByteBuffer.allocate(sectionIndex.getSize());
                Future<Integer> read = asynchronousFileChannel.read(byteBuffer, sectionIndex.getStart());
                read.get();
                payload.put(byteBuffer.array());
                /*
                randomAccessFile.seek(sectionIndex.getStart());
                randomAccessFile.read(payload, offset, sectionIndex.getSize());

                 */
                offset += sectionIndex.getSize();
            } catch (InterruptedException | ExecutionException e) {
                LOGGER.error("Impossible to build data", e);
            }
        }
        return binaryTagDriver.readCompoundBinaryTag(new ByteArrayInputStream(payload.array()));

    }

    public synchronized void writeCompoundBinaryTag(String key, CompoundBinaryTag compoundBinaryTag) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, byteArrayOutputStream);
        byte[] payload = byteArrayOutputStream.toByteArray();

        List<SectionIndex> sectionIndices = compoundSectionIndex.getSectionIndices(key);

        if (!sectionIndices.isEmpty()) {
            compoundSectionIndex.addFreeSectionIndices(sectionIndices);
            sectionIndices.clear();

            writeInFreeSectionIndices(payload, sectionIndices);


        } else {
            if (compoundSectionIndex.getFreeSectionIndices().isEmpty()) {

                long length = asynchronousFileChannel.size();
                SectionIndex sectionIndex = new SectionIndex(0, (int) length, (int) (length + payload.length));
                sectionIndices.add(sectionIndex);
                try {
                    asynchronousFileChannel.write(ByteBuffer.wrap(payload), length).get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }

            } else {

                writeInFreeSectionIndices(payload, sectionIndices);

            }
        }

        compoundSectionIndex.setSectionIndex(key, sectionIndices);

    }

    private void writeInFreeSectionIndices(byte[] payload, Collection<SectionIndex> sectionIndices) throws IOException {
        int index = 0;
        int binaryTagIndex = 0;

        while (index != payload.length) {
            SectionIndex sectionIndex = compoundSectionIndex.getFreeSectionIndices().poll();
            if (sectionIndex != null) {
                compoundSectionIndex.removeFreeSectionIndices(sectionIndex);
                int i = payload.length - index;
                int size = sectionIndex.getSize();

                if (sectionIndex.getSize() > i) {
                    size = i;
                    SectionIndex freeSectionIndex = new SectionIndex(-1, sectionIndex.getStart()+size, sectionIndex.getEnd());
                    compoundSectionIndex.addFreeSectionIndices(freeSectionIndex);
                }
                sectionIndex = new SectionIndex(binaryTagIndex++, sectionIndex.getStart(), sectionIndex.getStart()+size);
                Future<Integer> write = asynchronousFileChannel.write(ByteBuffer.wrap(payload, index, size), sectionIndex.getStart());
                try {
                    Integer integer = write.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                /*
                randomAccessFile.seek(sectionIndex.getStart());
                randomAccessFile.write(payload, index, size);

                 */
                index+=size;
            } else {
                long length = asynchronousFileChannel.size();
                //long length = randomAccessFile.length();
                sectionIndex = new SectionIndex(binaryTagIndex++, (int) length, (int) (length + (payload.length - index)));
                Future<Integer> write = asynchronousFileChannel.write(ByteBuffer.wrap(payload, index, sectionIndex.getSize()), sectionIndex.getStart());
                try {
                    Integer integer = write.get();
                } catch (InterruptedException | ExecutionException e) {
                    throw new RuntimeException(e);
                }
                /*
                randomAccessFile.seek(sectionIndex.getStart());
                randomAccessFile.write(payload, index, sectionIndex.getSize());

                 */
                index += sectionIndex.getSize();

            }
            sectionIndices.add(sectionIndex);
        }
    }


    private int getSizeOfSectionIndex(List<SectionIndex> sectionIndices) {
        int size = 0;

        for (SectionIndex sectionIndex : sectionIndices) {
            size += sectionIndex.getSize();
        }

        return size;
    }

    private CompoundSectionIndex readCompoundSectionIndex(File file) {
        CompoundSectionIndex compoundSectionIndex = new CompoundSectionIndex();
        CompoundBinaryTag compoundBinaryTag = binaryTagDriver.readCompoundBinaryTagOrDefault(file, CompoundBinaryTag::new);

        if (compoundBinaryTag.containsKey("sectionIndices")) {
            CompoundBinaryTag sectionIndices = (CompoundBinaryTag) compoundBinaryTag.getBinaryTag("sectionIndices");

            sectionIndices.getValue().forEach((s, binaryTag) -> {
                ListBinaryTag listBinaryTag = (ListBinaryTag) binaryTag;
                List<SectionIndex> binaryTagSectionIndices = new ArrayList<>();

                for (BinaryTag<?> tag : listBinaryTag) {
                    CompoundBinaryTag sectionIndex = (CompoundBinaryTag) tag;
                    int index = sectionIndex.getInt("index");
                    int start = sectionIndex.getInt("start");
                    int end = sectionIndex.getInt("end");

                    binaryTagSectionIndices.add(new SectionIndex(index, start, end));
                }

                compoundSectionIndex.setSectionIndex(s, binaryTagSectionIndices);
            });
        }

        if (compoundBinaryTag.containsKey("freeSectionIndices")) {
            ListBinaryTag freeSectionIndicesBinaryTag = (ListBinaryTag) compoundBinaryTag.getBinaryTag("freeSectionIndices");
            Queue<SectionIndex> freeSectionIndices = new ConcurrentLinkedQueue<>();
            freeSectionIndicesBinaryTag.forEach(binaryTag -> {
                CompoundBinaryTag sectionIndex = (CompoundBinaryTag) binaryTag;
                int start = sectionIndex.getInt("start");
                int end = sectionIndex.getInt("end");
                freeSectionIndices.add(new SectionIndex(-1, start, end));
            });

            compoundSectionIndex.setFreeSectionIndices(freeSectionIndices);
        }

        return compoundSectionIndex;
    }

    public void writeCompoundSectionIndex() {
        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();

        CompoundBinaryTag compoundBinaryTagSectionIndices = new ZstdCompoundBinaryTag();

        compoundSectionIndex.getSectionIndicesByName().forEach((s, sectionIndices) -> {
            ListBinaryTag listBinaryTag = new ZstdListBinaryTag(BinaryTagType.COMPOUND);
            sectionIndices.forEach(sectionIndex -> {
                CompoundBinaryTag compoundBinaryTagSectionIndex = new CompoundBinaryTag();
                compoundBinaryTagSectionIndex.putInt("index", sectionIndex.getIndex());
                compoundBinaryTagSectionIndex.putInt("start", sectionIndex.getStart());
                compoundBinaryTagSectionIndex.putInt("end", sectionIndex.getEnd());
                listBinaryTag.add(compoundBinaryTagSectionIndex);
            });
            compoundBinaryTagSectionIndices.putBinaryTag(s, listBinaryTag);
        });

        compoundBinaryTag.putBinaryTag("sectionIndices", compoundBinaryTagSectionIndices);

        ListBinaryTag binaryTagFreeSectionIndices = new ZstdListBinaryTag(BinaryTagType.COMPOUND);
        for (SectionIndex freeSectionIndex : compoundSectionIndex.getFreeSectionIndices()) {
            CompoundBinaryTag compoundBinaryTagSectionIndex = new CompoundBinaryTag();
            compoundBinaryTagSectionIndex.putInt("start", freeSectionIndex.getStart());
            compoundBinaryTagSectionIndex.putInt("end", freeSectionIndex.getEnd());
            binaryTagFreeSectionIndices.add(compoundBinaryTagSectionIndex);
        }

        compoundBinaryTag.putBinaryTag("freeSectionIndices", binaryTagFreeSectionIndices);

        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, indexFile);

    }

    public CompoundSectionIndex getCompoundSectionIndex() {
        return compoundSectionIndex;
    }
}