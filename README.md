# MWork

MWork is the first layer of the project and is intended to contain important systems that will be used in the upper layers.

### EventBus

The system is very similar to the system made by bukkit but it is without annotations.\
You create an interface or an abstract class that you want to be able to listen to.

```java
public interface Event {
    
}
```

When listening to a class you can specify if you want to listen only to that class or to the classes that inherit it by default it's in `ONLY`, in our case we want to listen to everything so we put in `DERIVE`.

```java
public class Main {
    public static void main(String[] args) {
        EventBus<Event> eventBus = new EventBus<>();

        eventBus.subscribe(Event.class, (event, parameterContainer) ->  System.out.println(event.getClass()))
                .withEventFilter(EventFilter.DERIVE)
                .withEventPriority(EventPriority.HIGH);
    }
}
```

The EventContainer allows you to pass variables dynamically. \
An example of how to add/retrieve variables.
```java
ParameterContainer parameterContainer = ParameterContainer.of()
        .putContainer("Hello");
System.out.println(parameterContainer.getContainer(String.class));
```

And if you have several variables with the same type you can specify a name.
```java
ParameterContainer parameterContainer = ParameterContainer.of()
        .putContainer("test", "Hello");
System.out.println(parameterContainer.getContainer("test", String.class));
```
In order to issue an event we just need to use the `publish` method which can take 2 arguments, the first is the object that inherits from our `Event` class and the second is an optional `ParameterContainer`. 
```java
public class HelloWorld implements Event {
    
}
```

```java
public class Main {
    public static void main(String[] args) {
        EventBus<Event> eventBus = new EventBus<>();

        eventBus.subscribe(Event.class, (event, parameterContainer) ->  System.out.println(event.getClass()))
                .withEventFilter(EventFilter.DERIVE)
                .withEventPriority(EventPriority.HIGH);
        
        eventBus.publish(new HelloWorld());
    }
}
```
### Name Binary Tag

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
Account acount = new Account("_RealAlpha_", 17);
DefaultBinaryTagBufferDriver defaultBinaryTagBufferDriver = new DefaultBinaryTagBufferDriver();

CompoundBinaryTag compoundBinaryTag = new CompoundBinaryTag();
compoundBinaryTag.putString("name", account.getName());
compoundBinaryTag.putInt("age", account.getAge());
compoundBinaryTag.putLong("time", account.getTime());

defaultBinaryTagBufferDriver.writeCompoundBinaryTag(compoundBinaryTag, new File("test.dat"));
```
You can see that it's quite tedious to write our object so there is another way to write it more easily.

```java
DefaultBinaryTagMarshal defaultBinaryTagMarshal = new DefaultBinaryTagMarshal();
defaultBinaryTagBufferDriver.writeCompoundBinaryTag(defaultBinaryTagMarshal.marshal(accountContext), new File("test.dat"));
```
There are lots of little tools, we can specify a special marshal for a type, apply a compression on a variable.\
Later on, there will be a mapping system to allow to read a very big file quickly and with few resources, it will be useful for schematics for example.
