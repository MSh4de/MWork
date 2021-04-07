import eu.mshade.mwork.nametag.NameTagDriver;
import eu.mshade.mwork.nametag.NameTagToken;
import net.kyori.adventure.nbt.BinaryTagIO;

import java.io.File;
import java.util.List;

public class Test  {


    public Test() throws Exception {
        NameTagDriver nameTagDriver = new NameTagDriver();
        File file = new File("test.dat");
        System.out.println(file);
        file.createNewFile();


        NameTagToken<List<String>> token = new NameTagToken<>(){};

        System.out.println(token.getTypeToken().getOwnerType());
        System.out.println(List.class);

        System.out.println(token.getTypeToken().getActualTypeArguments()[0]);

        AccountContext accountContext = nameTagDriver.deserialize(BinaryTagIO.readCompressedPath(file.toPath()), AccountContext.class);
        System.out.println();
        System.out.println(accountContext.getName());


    }

    public long profile(Runnable runnable){
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("HELLO");
        new Test();
    }


    private static int locToBlock(double loc) {
        final int floor = (int) loc;
        return floor == loc ? floor : floor - (int) (Double.doubleToRawLongBits(loc) >>> 63);
    }


}
