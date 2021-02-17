package eu.mshadeproduction.mwork.service;


import eu.mshadeproduction.mwork.ContextType;
import eu.mshadeproduction.mwork.Receiver;

public class Trade {

    private static int index = 0;

    private int id;
    private ContextType contextType = ContextType.SERVICE;
    private Receiver receiver;


    public Trade(Receiver receiver){
        this(index++, receiver);
    }

    public Trade(int id, Receiver receiver) {
        this.id = id;
        this.receiver = receiver;
    }

    private Trade() { }

    public Receiver getReceiver() {
        return receiver;
    }

    public ContextType getContextType() {
        return contextType;
    }

    public int getId() {
        return id;
    }
}
