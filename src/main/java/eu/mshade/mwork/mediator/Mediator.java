package eu.mshade.mwork.mediator;

public interface Mediator {

    Mediator MEDIATOR = (source, key) -> {};

    void notify(Object source, String key);

}
