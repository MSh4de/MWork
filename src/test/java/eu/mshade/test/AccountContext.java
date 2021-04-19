package eu.mshade.mwork;

import eu.mshade.mwork.binarytag.BinaryTagType;
import eu.mshade.mwork.binarytag.marshal.BinaryTagProperty;

import java.util.Objects;

public class AccountContext {

    private String name;
    private int age;
    private long time;
    @BinaryTagProperty(BinaryTagType.ZSTD_INTEGER_ARRAY)
    private int[] ints = new int[65536];


    private AccountContext() {
    }

    public AccountContext(String name, int age) {
        this.name = name;
        this.age = age;
        this.time = System.currentTimeMillis();
    }

    public int[] getInts() {
        return ints;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public long getTime() {
        return time;
    }



    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountContext that = (AccountContext) o;
        return age == that.age && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
