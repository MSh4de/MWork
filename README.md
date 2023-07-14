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

### Segment Binary Tag
A **Segment Binary Tag** is a valuable system that provides you the capability of writing as much compound binary tags as you
wish in the same file, without having to rewrite the whole file when your purpose is to modify only one of them.
<hr>
The Binary Tag will take advantage of the scattered data written in the file to reconstruct the data when it reads it. 
This also prevent you from having to leave any space in the file.

```java
import eu.mshade.mwork.binarytag.BinaryTagDriver;
import eu.mshade.mwork.binarytag.segment.SegmentBinaryTag;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("_RealAlpha_", 17);

        File segmentFile = new File("myFile.dat");

        BinaryTagDriver binaryTagDriver = new BinaryTagDriver();
        SegmentBinaryTag segmentBinaryTag = new SegmentBinaryTag(segmentFile);

        CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
        compoundBinaryTag.putString("name", account.getName());
        compoundBinaryTag.putInt("age", account.getAge());
        compoundBinaryTag.putLong("time", account.getTime());

        segmentBinaryTag.writeCompound("first", binaryTagDriver, compoundBinaryTag);
    }
}
```
Therefore, you shall not allocate any space for each object where everything is expandable.