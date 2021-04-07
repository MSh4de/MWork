package eu.mshade.test;

import eu.mshade.mwork.nametag.NameTagDriver;

import java.io.File;

public class Test  {


    public Test() throws Exception {
        NameTagDriver nameTagDriver = new NameTagDriver();
        File file = new File("test.dat");
        System.out.println(file);
        file.createNewFile();

        /*
        NameTagToken<List<String>> token = new NameTagToken<>(){};

        System.out.println(token.getTypeToken().getOwnerType());
        System.out.println(List.class);

        System.out.println(token.getTypeToken().getActualTypeArguments()[0]);

         */

        System.out.println(System.getProperty("user.dir"));

        //AccountContext accountContext = nameTagDriver.deserialize(BinaryTagIO.readCompressedPath(file.toPath()), AccountContext.class);


    }

    public long profile(Runnable runnable){
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) throws Exception {
        new Test();
    }


    private static int locToBlock(double loc) {
        final int floor = (int) loc;
        return floor == loc ? floor : floor - (int) (Double.doubleToRawLongBits(loc) >>> 63);
    }


}
