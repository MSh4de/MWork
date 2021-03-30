package eu.mshadeproduction.mwork.streaming;



public abstract class Streaming {

    private final StreamingType streamingType;

    public Streaming() {
        this.streamingType  = StreamingType.getStreamingTypeByClass(this.getClass()).get();
    }

    public StreamingType getStreamingType() {
        return streamingType;
    }
}
