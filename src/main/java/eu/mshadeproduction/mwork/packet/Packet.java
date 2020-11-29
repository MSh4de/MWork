package eu.mshadeproduction.mwork.packet;


public abstract class Packet {

    private static int index = 0;

    private final int ID;
    private final PacketType packetType;

    public Packet() {
        this(index++);
    }

    public Packet(int ID) {
        this.ID = ID;
        this.packetType = PacketType.getPacketTypeByClass(this.getClass());
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public int getID() {
        return ID;
    }

    public static int getIndex() {
        return index;
    }
}
