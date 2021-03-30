package eu.mshade.mwork.dispatcher;

public final class DispatcherContainerBuilder {

    private final DefaultDispatcherContainer dispatcherContainer = new DefaultDispatcherContainer();

    public static DispatcherContainerBuilder builder(){
        return new DispatcherContainerBuilder();
    }

    public DispatcherContainerBuilder setContainer(Object value){
        this.dispatcherContainer.setContainer(value);
        return this;
    }

    public DispatcherContainerBuilder setContainer(String key, Object value){
        this.dispatcherContainer.setContainer(key, value);
        return this;
    }

    public DispatcherContainer build(){
        return dispatcherContainer;
    }

}
