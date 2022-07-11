package eu.mshade.test;

import java.io.RandomAccessFile;

public class FileTest {

    public static void main(String[] args) throws Exception{
        RandomAccessFile randomAccessFile = new RandomAccessFile("test.dat","rw");
        /*
        System.out.println(randomAccessFile.getFilePointer());
        System.out.println(randomAccessFile.length());
        randomAccessFile.seek(0);
        System.out.println(randomAccessFile.readByte());
        System.out.println(randomAccessFile.read());
        System.out.println(randomAccessFile.read());
        System.out.println(randomAccessFile.readByte());
        System.out.println(randomAccessFile.getFilePointer());

         */
        randomAccessFile.seek(3);
        System.out.println(randomAccessFile.readByte());
        System.out.println(randomAccessFile.getFilePointer());
        System.out.println(randomAccessFile.length());

    }


}
