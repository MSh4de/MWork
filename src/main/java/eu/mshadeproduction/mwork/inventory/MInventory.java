package eu.mshadeproduction.mwork.inventory;

import eu.mshadeproduction.mwork.item.MItemStack;

import java.util.HashMap;
import java.util.Map;

public class MInventory {

    private String name;
    private MInventoryType inventoryType;
    private int size;
    private Map<Integer, MItemStack> contents = new HashMap<>();

    private MInventory(){}

    public MInventory(String name, MInventoryType inventoryType) {
        this(name, inventoryType, 9*3);
    }

    public MInventory(String name, MInventoryType inventoryType, int size) {
        this.name = name;
        this.inventoryType = inventoryType;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public MInventoryType getInventoryType() {
        return inventoryType;
    }

    public int getSize() {
        return size;
    }

    public Map<Integer, MItemStack> getContents() {
        return contents;
    }
}
