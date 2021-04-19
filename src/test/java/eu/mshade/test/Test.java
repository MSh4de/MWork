package eu.mshade.mwork;

import eu.mshade.mwork.binarytag.DefaultBinaryTagBufferDriver;
import eu.mshade.mwork.binarytag.DefaultBinaryTagMarshal;

import java.io.File;
import java.io.FileOutputStream;

public class Test  {


    public Test() throws Exception {

        DefaultBinaryTagBufferDriver defaultBinaryTagBufferDriver = new DefaultBinaryTagBufferDriver();
        DefaultBinaryTagMarshal defaultBinaryTagMarshal = new DefaultBinaryTagMarshal();

        AccountContext accountContext = new AccountContext("Oleksandr", 17);
        int i = 0;
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < 256; y++) {
                    accountContext.getInts()[i++] = i;
                }
            }
        }
        System.out.println(profile(() -> {
            try {
                defaultBinaryTagBufferDriver.writeCompoundBinaryTag(defaultBinaryTagMarshal.marshal(accountContext), new FileOutputStream("test.dat"));
                System.out.println(defaultBinaryTagMarshal.unMarshal(AccountContext.class, defaultBinaryTagBufferDriver.readCompoundBinaryTag(new File("test.dat"))).getInts().length);
            }catch (Exception e){
                e.printStackTrace();
            }
        })+" ms");

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
