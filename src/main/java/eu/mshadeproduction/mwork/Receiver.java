package eu.mshadeproduction.mwork;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Receiver {


    private MOptional<String> receiver;
    private ReceiverType receiverType;

    private Receiver(){}

    public Receiver(ReceiverType receiverType) {
        this(MOptional.empty(), receiverType);
    }

    public Receiver(MOptional<String> receiver, ReceiverType receiverType) {
        this.receiver = receiver;
        this.receiverType = receiverType;
    }

    public MOptional<String> getReceiver() {
        return receiver;
    }

    public ReceiverType getReceiverType() {
        return receiverType;
    }

    @Override
    public String toString() {
        return "Receiver{" +
                "receiver=" + receiver +
                ", receiverType=" + receiverType +
                '}';
    }
}
