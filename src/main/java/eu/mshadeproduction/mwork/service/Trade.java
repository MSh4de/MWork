package eu.mshadeproduction.mwork.service;


import eu.mshadeproduction.mwork.ContextType;

public class Trade {

    private static int index = 0;

    private final int id;
    private final ContextType contextType = ContextType.SERVICE;

    public Trade(){
        this(index++);
    }

    public Trade(int id) {
        this.id = id;
    }

    public ContextType getContextType() {
        return contextType;
    }

    public int getId() {
        return id;
    }
}
