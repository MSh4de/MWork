package eu.mshade.test;

import eu.mshade.mwork.binarytag.*;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestData {

    public static void main(String[] args) {

        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        byte[] bytes = {    10, 0, 0,  13,   0,   4, 116, 101, 115, 116,
                1, 1, 0,   5, 116, 101, 115, 116,  50,   2,
                7, 0, 5, 116, 101, 115, 116,  51,   0,   0,
                0, 6, 1,   2,   3,   4,   5,   6,   0 };

        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        compoundBinaryTag.putIntArray("test", new int[]{-1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});

        System.out.println(compoundBinaryTag.toPrettyString());

        System.out.println(binaryTagDriver.readCompoundBinaryTag(new ByteArrayInputStream(bytes)).toPrettyString());


        File poetIndex = new File("poetIndex.dat");
        System.out.println(binaryTagDriver.readCompoundBinaryTag(poetIndex).toPrettyString());

    }

}
