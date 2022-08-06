package eu.mshade.test.poet;

import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ZstdCompoundBinaryTag;
import eu.mshade.mwork.binarytag.poet.BinaryTagPoet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestPoet {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        System.out.println("WAITING");
        Thread.sleep(40*1000);
        System.out.println("RUN");
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();

        File poetIndex = new File("poetIndex.dat");
        File poetData = new File("poetData.dat");

        /*
        if (!poetIndex.exists()) poetIndex.createNewFile();
        if (!poetData.exists()) poetData.createNewFile();

         */

        BinaryTagPoet binaryTagPoet = new BinaryTagPoet(poetIndex, poetData, binaryTagDriver);

        //System.out.println(binaryTagPoet.getCompoundSectionIndex());

        /*
        System.out.println(binaryTagPoet.readCompoundBinaryTag("cc"));
        System.out.println(binaryTagPoet.readCompoundBinaryTag("aa"));

         */


        for (int i = 0; i < 1000; i++) {
            CompoundBinaryTag compoundBinaryTag = new ZstdCompoundBinaryTag();

            for (int i2 = 0; i2 < 25; i2++) {
                compoundBinaryTag.putInt(""+i2, i2);
            }

            binaryTagPoet.writeCompoundBinaryTag("cc", compoundBinaryTag);
        }

        binaryTagPoet.writeCompoundSectionIndex();


       // System.out.println(binaryTagPoet.getCompoundSectionIndex());



    }

}
