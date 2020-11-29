package eu.mshadeproduction.mwork.packet.block;

import eu.mshadeproduction.mwork.packet.common.MShadeMaterial;
import eu.mshadeproduction.mwork.packet.world.LocationItem;

public class BlockItem {

    private final MShadeMaterial material;
    private final int data;
    private final LocationItem location;

    public BlockItem(MShadeMaterial material, int data, LocationItem location) {
        this.material = material;
        this.data = data;
        this.location = location;
    }

    public MShadeMaterial getMaterial() {
        return material;
    }

    public int getData() {
        return data;
    }

    public LocationItem getLocationItem() {
        return location;
    }
}
