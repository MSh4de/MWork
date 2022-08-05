package eu.mshade.test.mediator;

import eu.mshade.mwork.mediator.Mediator;

import java.util.UUID;
import java.util.function.Supplier;

public class ObjectMediator {

    private Mediator<ObjectMediator> mediator;

    public ObjectMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public String configure(String s){
        Supplier<String> supplier = () -> s;
        return mediator.notify(this, supplier, "configure", new Object[]{s});

    }
}
