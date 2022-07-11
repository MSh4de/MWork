package eu.mshade.test;

import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.entity.CompoundBinaryTag;
import eu.mshade.mwork.binarytag.entity.ListBinaryTag;
import eu.mshade.mwork.binarytag.entity.StringBinaryTag;

import java.io.*;
import java.lang.reflect.Proxy;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

public class Test  {

    private ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private Random randon = new Random();

    public Test() throws Exception {


        int max = (int) Math.pow(2, 5);
        int bits = 5+4;
        int x = 10 << 5;
        System.out.println(x);
        System.out.println(calculateBits(x));
        int individualValueMask = (1 << 5) - 1;
        System.out.println(individualValueMask);
        System.out.println(Arrays.toString(toBinary(x, bits)));


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        for (boolean b : toBinary(x, bits)) {
            dataOutputStream.writeBoolean(b);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
        System.out.println("READBITSNUMBER: "+readBitsNumber(bits, dataInputStream));

        ListBinaryTag binaryTags = new ListBinaryTag(BinaryTagType.STRING);
        binaryTags.add(new StringBinaryTag("hey"));
        binaryTags.add(new StringBinaryTag("hec"));
        binaryTags.add(new StringBinaryTag("hes"));

        System.out.println(binaryTags);

        System.out.println(writeVarInt(-1000));
        System.out.println(-1000 & 127);

        /*
        for (int i = -1000; i <= 1000 ; i++) {
            byte b = writeVarInt(i);
            if (b == (i & 127)){
                System.out.println("Not Match" + i);
            }
        }

         */
        System.out.println(-1 & 0b01111111);

        System.out.println(writeVarInt(-1));

        System.out.println(15 >> 4);

        stringIntegerHashMap.put("Hey", 10);
        stringIntegerHashMap.put("CC", 39);
        int nextInt = randon.nextInt(100);
        String s = randomItem(nextInt);
        System.out.println(s);
        /*
        int value = 17;
        value &= 0xf;
        System.out.println(value << 4);
        int result = (value << 4) | value;
        System.out.println(result);

         */

        System.out.println('\uffff');

        int sectionBitmask = 0;
        boolean[] sections = new boolean[]{true, false, true, true, true, true, true, true, false, false, false, false, false, false, false, false};
        int maxBitmask = (1 << sections.length) - 1;
        //sectionBitmask = maxBitmask;
        int sectionCount = Integer.bitCount(maxBitmask);
        System.out.println(maxBitmask);
        for (int i = 0; i < 16; i++) {
            if (sections[i]) {
                sectionBitmask |= 1 << i;
                sectionCount--;
            }
        }
        boolean[] sendSections = new boolean[sectionCount];
        for (int i = 0; i < sections.length; ++i) {
            if ((sectionBitmask & 1 << i) != 0) {
                System.out.println("T: "+i);
                sendSections[i] = sections[i];
            }


        }

        System.out.println(Arrays.toString(sendSections));

        System.out.println(sectionBitmask);

        int i = (int) System.currentTimeMillis();
        System.out.println(i);

        System.out.println((1 << 15));

        System.out.println(Integer.bitCount(15));

        System.out.println(countBits(15));


        byte c = 0x0F;
        System.out.println(c);

        AccountContext accountContext = new AccountContext("Oleksandr", 170987645);

        BinaryTagDriver defaultBinaryTagBufferDriver = new BinaryTagDriver();


        /*
        JoinGame joinGame = new JoinGame(3, false, (byte)1, (byte) 1, 1,  new String[]{"minecraft:owverworld"}, "minecraft:world", 58024156541321L, 20, 12, false, true, false, false);
        FileOutputStream fileOutputStream = new FileOutputStream("a.test");
        fileOutputStream.write(joinGame.serialize());

         */

        Account o = (Account) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Account.class}, (proxy, method, args) -> {
            if (method.getName().equals("toString")) return accountContext.toString();
            System.out.println(proxy);
            return method.invoke(accountContext, args);
        });
        System.out.println(o.getClass());
        System.out.println(o.getName());
        System.out.println(o.getAge());


    }

    private Map<String, Integer> stringIntegerHashMap = new HashMap<>();

    private String randomItem(int i){
        LinkedHashMap<String, Integer> linkedHashMap = stringIntegerHashMap.entrySet()
                .stream()
                .sorted((o1, o2) -> {
                    int min = Math.min(i, o1.getValue());
                    int max = Math.max(i, o1.getValue());
                    return max-min;
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return linkedHashMap.entrySet().stream().findFirst().map(Map.Entry::getKey).get();
    }

    public final int readBitsNumber(int size, DataInputStream dataInputStream) throws IOException {
        boolean[] bytes = new boolean[size];
        for (int i = 0; i < size; i++) {
            bytes[i] = dataInputStream.readBoolean();
        }
        int result = 0;
        for(int i = 0; i < size; i++){
            result <<= 1;
            result |= bytes[i] ? 1 : 0;
        }
        return result;
    }

    public final void writeBitsNumber(int v, int size, DataOutputStream dataOutputStream){

    }

    public byte writeVarInt(int value) {
        do {
            byte temp = (byte)(value & 0b01111111);
            value >>>= 7;
            System.out.println(temp);
            System.out.println(value);
            if (value != 0) {
                temp |= 0b10000000;
            }
            return temp;
        } while (value != 0);
    }

    private int countBits(int v) {
        // http://graphics.stanford.edu/~seander/bithacks.html#CountBitsSetKernighan
        int c;
        for (c = 0; v > 0; c++) {
            v &= v - 1;
        }
        return c;
    }

    public long profile(Runnable runnable){
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) throws Exception {
        new Test();
    }

    public int getIndex(int x, int y, int z){
        return (((y * 16) + z) * 16) + x;
    }

    private static int locToBlock(double loc) {
        final int floor = (int) loc;
        return floor == loc ? floor : floor - (int) (Double.doubleToRawLongBits(loc) >>> 63);
    }

    private boolean[] toBinary(int number, int bits) {
        final boolean[] ret = new boolean[bits];
        for (int i = 0; i < bits; i++) {
            ret[bits - 1 - i] = (1 << i & number) != 0;
        }
        return ret;
    }

    public int calculateBits(int v){
        int pow = 0;
        while (Math.pow(2, pow) < v){
            pow++;
        }
        return pow;
    }

}
