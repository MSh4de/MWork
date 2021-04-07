package eu.mshade.test;

import java.util.ArrayList;
import java.util.List;

public class AccountContext {

    private String name;
    private int age;
    private long time;
    private List<Location> locations = new ArrayList<>();


    private AccountContext() {
    }

    public AccountContext(String name, int age) {
        this.name = name;
        this.age = age;
        this.time = System.currentTimeMillis();
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

    public void addLocation(Location location){
        this.locations.add(location);
    }

    public List<Location> getLocations() {
        return locations;
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
}
