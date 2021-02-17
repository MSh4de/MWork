package eu.mshadeproduction.mwork.item;

import java.util.ArrayList;
import java.util.List;

public class MItemStack {

    private MMaterial material;
    private String name;
    private int data;
    private int amount;
    private final List<String> lore = new ArrayList<>();

    private MItemStack(){

    }

    public MItemStack(MMaterial material, int data, int amount) {
        this.material = material;
        this.data = data;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
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

    @Override
    public String toString() {
        return "MItemStack{" +
                "material=" + material +
                ", name='" + name + '\'' +
                ", data=" + data +
                ", amount=" + amount +
                ", lore=" + lore +
                '}';
    }
}
