package eu.mshade.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AccountContext implements Account {

    private String name;
    private int age;
    private long time;
    private List<Location> locations = new ArrayList<>();

    public AccountContext(String name, int age) {
        this.name = name;
        this.age = age;
        this.time = System.currentTimeMillis();
    }

    public List<Location> getLocations() {
        return locations;
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
    public String toString() {
        return "AccountContext{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", time=" + time +
                ", locations=" + locations +
                '}';
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
