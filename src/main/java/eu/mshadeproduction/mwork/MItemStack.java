package eu.mshadeproduction.mwork;

public class MItemStack {

    private MMaterial material;
    private int data;
    private int amount;

    private MItemStack(){

    }

    public MItemStack(MMaterial material, int data, int amount) {
        this.material = material;
        this.data = data;
        this.amount = amount;
    }

    public MMaterial getMaterial() {
        return material;
    }

    public int getData() {
        return data;
    }

    public int getAmount() {
        return amount;
    }
}
