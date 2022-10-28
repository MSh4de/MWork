package eu.mshade.test.poet;

import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.poet.BinaryTagPoet;
import eu.mshade.mwork.binarytag.poet.SectionIndex;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class TestChunk {


    public static void main(String[] args) throws IOException {
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        BinaryTagPoet binaryTagPoet = new BinaryTagPoet(new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/indices/0,0.dat"), new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/regions/0,0.dat"), binaryTagDriver);

        System.out.println(binaryTagPoet.getCompoundSectionIndex().getSectionIndices("0,0"));
        SectionIndex target = binaryTagPoet.getCompoundSectionIndex().getSectionIndices("0,0").get(0);
        Map<String, List<SectionIndex>> collision = new HashMap<>();
        System.out.println(Arrays.equals(binaryTagPoet.readCompoundBinaryTag("0,0").getByteArray("biomes"), binaryTagPoet.readCompoundBinaryTag("1,0").getByteArray("biomes")));
        binaryTagPoet.getCompoundSectionIndex().getSectionIndicesByName().forEach((s, sectionIndices) -> {

            if (!s.equalsIgnoreCase("0,0")){
                for (SectionIndex sectionIndex : sectionIndices) {
                    if (inside(target, sectionIndex) ){
                        collision.put(s, sectionIndices);
                    }
                }
            }
        });

//        System.out.println(collision);
        //System.out.println(binaryTagPoet.getCompoundSectionIndex());

    }

    public static boolean inside(SectionIndex a, SectionIndex b) {
        return (a.getStart() <= b.getStart() && a.getStart() >= b.getEnd()) || (a.getEnd() <= b.getStart() && a.getEnd() >= b.getEnd());
    }


}
