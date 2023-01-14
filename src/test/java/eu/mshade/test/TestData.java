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

        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();

        ListBinaryTag listBinaryTag = new ListBinaryTag(BinaryTagType.COMPOUND);

        for (int i = 0; i < 5; i++) {
            CompoundBinaryTag compoundBinaryTag1 = new CompoundBinaryTag();
            compoundBinaryTag1.putBinaryTag("name", new StringBinaryTag("name" + i));
            compoundBinaryTag1.putBinaryTag("age", new IntBinaryTag(ThreadLocalRandom.current().nextInt(0, 100)));
            listBinaryTag.add(compoundBinaryTag1);
        }

        compoundBinaryTag.putBinaryTag("list", listBinaryTag);


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, byteArrayOutputStream);
        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));


    }

}
