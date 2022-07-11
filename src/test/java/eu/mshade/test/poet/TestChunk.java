package eu.mshade.test.poet;

import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.poet.BinaryTagPoet;

import java.io.File;
import java.io.IOException;

public class TestChunk {


    public static void main(String[] args) throws IOException {
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        BinaryTagPoet binaryTagPoet = new BinaryTagPoet(new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/indices/0,0.dat"), new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/regions/0,0.dat"), binaryTagDriver);
        System.out.println(binaryTagPoet.getCompoundSectionIndex().getSectionIndices("0,0"));
        System.out.println(binaryTagPoet.readCompoundBinaryTag("0,0"));
        //System.out.println(binaryTagPoet.getCompoundSectionIndex());

    }

}
