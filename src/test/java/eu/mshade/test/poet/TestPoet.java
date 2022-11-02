package eu.mshade.test.poet;

import eu.mshade.mwork.binarytag.*;
import eu.mshade.mwork.binarytag.carbon.CarbonBinaryTag;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class TestPoet {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        System.out.println("WAITING");
        System.out.println("RUN");
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();

        File poetIndex = new File("poetIndex.dat");
        File poetData = new File("poetData.dat");

        /*
        if (!poetIndex.exists()) poetIndex.createNewFile();
        if (!poetData.exists()) poetData.createNewFile();

         */

        CarbonBinaryTag carbonBinaryTag = new CarbonBinaryTag(poetIndex, poetData, binaryTagDriver);

        //System.out.println(binaryTagPoet.getCompoundSectionIndex());

//        System.out.println(binaryTagPoet.readCompoundBinaryTag("cc").toPrettyString());
        System.out.println(carbonBinaryTag.readCompoundBinaryTag("aa").toPrettyString());





/*        ListBinaryTag zstdListBinaryTag = new ZstdListBinaryTag(BinaryTagType.INT);
        for (int i = 0; i < 1000; i++) {
            zstdListBinaryTag.add(new IntBinaryTag(i));

        }
        binaryTagPoet.writeCompoundBinaryTag("aa", new CompoundBinaryTag().putBinaryTag("list", zstdListBinaryTag));

        binaryTagPoet.writeCompoundSectionIndex();*/


        System.out.println(carbonBinaryTag.getCompoundSectionIndex());



    }

}
