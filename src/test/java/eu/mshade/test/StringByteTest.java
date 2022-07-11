package eu.mshade.test;

import eu.mshade.mwork.binarytag.BinaryTagDriver;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class StringByteTest {

    public static void main(String[] args) throws IOException, DataFormatException {
        byte[] bytes = {    10, 0, 0,  13,   0,   4, 116, 101, 115, 116,
                1, 1, 0,   5, 116, 101, 115, 116,  50,   2,
                7, 0, 5, 116, 101, 115, 116,  51,   0,   0,
                0, 6, 1,   2,   3,   4,   5,   6,   0 };

        BinaryTagDriver defaultBinaryTagBufferDriver = new BinaryTagDriver();
        System.out.println(defaultBinaryTagBufferDriver.readCompoundBinaryTag(new ByteArrayInputStream(bytes)));

        byte[] compressedData = {
                120, (byte) 156,  93, (byte) 192, (byte) 161,  17,   0,
                0,   8, (byte) 128, (byte) 192,   3, (byte) 221, 127,
                102, (byte) 130, (byte) 205, (byte) 199, (byte) 217, (byte) 131,  79,
                4,  34,   0,  68

        };
        Inflater inflater = new Inflater();
        inflater.setInput(compressedData, 0, 25);
        byte[] result = new byte[27];
        int resultLength = inflater.inflate(result);
        inflater.end();
        System.out.println(Arrays.toString(result));
        /*
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        dataOutputStream.writeBoolean(true);
        dataOutputStream.writeDouble(1);
        dataOutputStream.writeFloat(1);
        dataOutputStream.writeByte(1);

        dataOutputStream.writeShort(1);
        dataOutputStream.writeUTF("hey");

        dataOutputStream.writeInt(-2);
        System.out.println(byteArrayOutputStream.size());
        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));

         */
    }

}

