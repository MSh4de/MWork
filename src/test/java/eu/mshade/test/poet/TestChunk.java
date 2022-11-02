package eu.mshade.test.poet;

import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.carbon.CarbonBinaryTag;
import eu.mshade.mwork.binarytag.carbon.CarbonSection;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class TestChunk {


    public static void main(String[] args) throws IOException {
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        CarbonBinaryTag carbonBinaryTag = new CarbonBinaryTag(new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/indices/0,0.dat"), new File("C:/Users/reala/IdeaProjects/MShade/worlds/world/regions/0,0.dat"), binaryTagDriver);

        System.out.println(carbonBinaryTag.getCompoundSectionIndex().getSectionIndices("0,0"));
        CarbonSection target = carbonBinaryTag.getCompoundSectionIndex().getSectionIndices("0,0").get(0);
        Map<String, List<CarbonSection>> collision = new HashMap<>();
        System.out.println(Arrays.equals(carbonBinaryTag.readCompoundBinaryTag("0,0").getByteArray("biomes"), carbonBinaryTag.readCompoundBinaryTag("1,0").getByteArray("biomes")));
        carbonBinaryTag.getCompoundSectionIndex().getSectionIndicesByName().forEach((s, sectionIndices) -> {

            if (!s.equalsIgnoreCase("0,0")){
                for (CarbonSection carbonSection : sectionIndices) {
                    if (inside(target, carbonSection) ){
                        collision.put(s, sectionIndices);
                    }
                }
            }
        });

//        System.out.println(collision);
        //System.out.println(binaryTagPoet.getCompoundSectionIndex());

    }

    public static boolean inside(CarbonSection a, CarbonSection b) {
        return (a.getStart() <= b.getStart() && a.getStart() >= b.getEnd()) || (a.getEnd() <= b.getStart() && a.getEnd() >= b.getEnd());
    }


}
