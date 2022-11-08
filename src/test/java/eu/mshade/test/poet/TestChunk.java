package eu.mshade.test.poet;

import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.segment.SegmentBinaryTag;
import eu.mshade.mwork.binarytag.segment.SegmentSection;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TestChunk {


    public static void main(String[] args) throws IOException {
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        SegmentBinaryTag segmentBinaryTag = new SegmentBinaryTag(new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/indices/0,0.dat"), new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/regions/0,0.dat"), binaryTagDriver);

        System.out.println(segmentBinaryTag.getCompoundSectionIndex().getSectionIndices("0,0"));
        SegmentSection target = segmentBinaryTag.getCompoundSectionIndex().getSectionIndices("0,0").get(0);
        Map<String, List<SegmentSection>> collision = new HashMap<>();
        System.out.println(Arrays.equals(segmentBinaryTag.readCompoundBinaryTag("0,0").getByteArray("biomes"), segmentBinaryTag.readCompoundBinaryTag("1,0").getByteArray("biomes")));
        segmentBinaryTag.getCompoundSectionIndex().getSectionIndicesByName().forEach((s, sectionIndices) -> {

            if (!s.equalsIgnoreCase("0,0")){
                for (SegmentSection segmentSection : sectionIndices) {
                    if (inside(target, segmentSection) ){
                        collision.put(s, sectionIndices);
                    }
                }
            }
        });

//        System.out.println(collision);
        //System.out.println(binaryTagPoet.getCompoundSectionIndex());

    }

    public static boolean inside(SegmentSection a, SegmentSection b) {
        return (a.getStart() <= b.getStart() && a.getStart() >= b.getEnd()) || (a.getEnd() <= b.getStart() && a.getEnd() >= b.getEnd());
    }


}
