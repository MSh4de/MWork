# MWork

MWork is the first layer of the project and is intended to contain important systems that will be used in the upper layers.

### EventBus

The system is very similar to the system made by bukkit but it is without annotations.\
You create an interface or an abstract class that you want to be able to listen to.

```java
public interface Event {
    
}
```

When listening to a class you can specify if you want to listen only to that class or to the classes that inherit it. By default, the `ONLY` filter mode is selected, in our case we want to listen to everything so we set it in `DERIVE` mode.

```java
public class Main {
    public static void main(String[] args) {
        EventBus<Event> eventBus = new EventBus<>();

        eventBus.subscribe(Event.class, (event) ->  System.out.println(event.getClass()), EventFilter.DERIVE, EventPriority.HIGH);
    }
}
```

In order to issue an event we just need to use the `publish` method, you just need to pass the object that inherits from our `Event` class in parameter.
```java
public class HelloWorld implements Event {
    
}
```

```java
public class Main {
    public static void main(String[] args) {
        EventBus<Event> eventBus = new EventBus<>();

        eventBus.subscribe(Event.class, (event) ->  System.out.println(event.getClass()), EventFilter.DERIVE, EventPriority.HIGH);
        
        eventBus.publish(new HelloWorld());
    }
}
```
### Binary Tag

The name binary tag is a very lightweight format that allows you to store whatever you want.\
In our little example we will store an Account object.

```java
public class Account {
    private String name;
    private int age;
    private long time;
    private List<String> strings = new ArrayList<>();
    
    public Account(String name, int age) {
        this.name = name;
        this.age = age;
        this.time = System.currentTimeMillis();
    }
    
    //GETTER and SETTER
}
```

```java
public class Main {
    public static void main(String[] args) {
        Account account = new Account("_RealAlpha_", 17);
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();

        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        compoundBinaryTag.putString("name", account.getName());
        compoundBinaryTag.putInt("age", account.getAge());
        compoundBinaryTag.putLong("time", account.getTime());

        binaryTagDriver.writeCompoundBinaryTag(compoundBinaryTag, new File("test.dat"));
    }
}
```

### Carbon Binary Tag
The carbon binary tag is a system that allows you to write several compound binary tags in the same file, 
this particularity is that you don't need to rewrite everything when you want to save but only the compound you just wrote,
thanks to an index the data are written in a scattered way in the whole file so that you don't have to leave any space and at the moment of the reading you reconstruct the data

```java
import eu.mshade.mwork.binarytag.BinaryTagDriver;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("_RealAlpha_", 17);

        File index = new File("index.dat");
        File data = new File("data.dat");
        
        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        CarbonBinaryTag carbonBinaryTag = new CarbonBinaryTag(index, data, binaryTagDriver);

        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        compoundBinaryTag.putString("name", account.getName());
        compoundBinaryTag.putInt("age", account.getAge());
        compoundBinaryTag.putLong("time", account.getTime());

        carbonBinaryTag.writeCompoundBinaryTag("first", compoundBinaryTag);
    }
}
```

So it's not necessary to allocate a space for each object here everything is expandable 