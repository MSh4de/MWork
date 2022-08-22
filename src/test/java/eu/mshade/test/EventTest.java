package eu.mshade.test;

import eu.mshade.mwork.event.EventBus;
import eu.mshade.mwork.event.EventFilter;
import eu.mshade.mwork.event.EventPriorities;
import eu.mshade.test.event.Event;
import eu.mshade.test.event.HelloWorldEvent;
import eu.mshade.test.event.WelcomeEvent;

public class EventTest {

    public static void main(String[] args) {
        EventBus<Event> eventBus = new EventBus<>();

        eventBus.subscribe(Event.class, (event) -> System.out.println(event.getClass()))
                .withEventFilter(EventFilter.DERIVE)
                .withEventPriority(EventPriorities.LOW);

        eventBus.subscribe(HelloWorldEvent.class, (event) -> System.out.println("HelloWorldEvent"))
                .withEventFilter(EventFilter.ONLY);

        eventBus.subscribe(HelloWorldEvent.class, (event) -> System.out.println("HEY"))
                .withEventFilter(EventFilter.DERIVE)
                .withEventPriority(EventPriorities.HIGH);

        System.out.println(eventBus.getEventContexts(WelcomeEvent.class, EventFilter.DERIVE));

        System.out.println(profile(() -> {
            eventBus.publish(new WelcomeEvent());
            eventBus.publish(new HelloWorldEvent());
        })+" MS");
    }

    public static long profile(Runnable runnable){
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }

}
