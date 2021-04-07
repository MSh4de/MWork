package eu.mshade.test;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.util.zip.GZIPInputStream;

public class FileTest {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("test.dat","rw");
        FileChannel channel = randomAccessFile.getChannel();
        GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream("test.dat"));
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(gzipInputStream));
        System.out.println(dataInputStream.readByte());
        System.out.println(dataInputStream.readShort());
        System.out.println(dataInputStream.read());
        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.read());
        byte[] bytes = new byte[dataInputStream.readByte()];
        dataInputStream.readFully(bytes);
        System.out.println(new String(bytes));

    }


}
