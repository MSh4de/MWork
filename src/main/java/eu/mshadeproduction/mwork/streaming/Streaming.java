package eu.mshadeproduction.mwork.streaming;


import eu.mshadeproduction.mwork.ContextType;

public abstract class Streaming {

    private final ContextType contextType = ContextType.STREAMING;
    private final StreamingType streamingType;

    public Streaming() {
        this.streamingType  = StreamingType.getStreamingTypeByClass(this.getClass()).get();
    }

    public ContextType getContextType() {
        return contextType;
    }

    public StreamingType getStreamingType() {
        return streamingType;
    }
}
