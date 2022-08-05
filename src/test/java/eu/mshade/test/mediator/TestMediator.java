package eu.mshade.test.mediator;

import eu.mshade.mwork.ShrinkingUUID;
import eu.mshade.mwork.mediator.Mediator;

import java.util.Arrays;
import java.util.UUID;
import java.util.function.Supplier;

public class TestMediator {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(ShrinkingUUID.randomUUID().length());
        System.out.println(UUID.randomUUID().toString().length());

        System.out.println("STARTING PROGRAM");
        Thread.sleep(1*1000);

        Mediator<ObjectMediator> mediator = new Mediator<>(){

            @Override
            public <T> T notify(ObjectMediator source, Supplier<T> callback, String key, Object[] args) {
                T t = callback.get();
                System.out.println("Method called "+key+" with parameters "+ Arrays.toString(args));
                if (t instanceof String string){
                    if (string.length() >= 5){
                        return (T) "Sorry the parameter is too long";
                    }
                }
                return t;
            }
        };

        ObjectMediator objectMediator = new ObjectMediator(mediator);



        System.out.println(objectMediator);

        System.out.println("STARTING LOOP");

        System.out.println(objectMediator.configure("a"));
        System.out.println(objectMediator.configure("aaaaaaaaaaaaaaaaaaaa"));


        long start = System.currentTimeMillis();
        /*
        for (int i = 0; i < 100_000; i++) {
            objectMediator.configure("aaaaaaaa");
        }

         */

        System.out.println("Finish in "+(System.currentTimeMillis() - start)+" ms");
    }

}
