package eu.mshadeproduction.mwork.packet;

import eu.mshadeproduction.mwork.packet.common.HeartBeatPacket;
import eu.mshadeproduction.mwork.packet.player.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PacketType {

    HEART_BEAT_PACKET(HeartBeatPacket.class),

    PLAYER_JOIN_PACKET(PlayerJoinPacket.class),
    PLAYER_MOVE_PACKET(PlayerMovePacket.class),
    PLAYER_REDUCE_MOVE_PACKET(PlayerReduceMovePacket.class),
    PLAYER_MESSAGE_PACKET(PlayerMessagePacket.class),
    PLAYER_VECTOR_PACKET(PlayerVectorPacket.class),
    PLAYER_TELEPORT_PACKET(PlayerTeleportPacket.class),
    PLAYER_EYE_LOCATION_PACKET(PlayerEyeLocationPacket.class);




    private final static Map<Class<? extends Packet>, PacketType> packetTypeHashMap = new HashMap<>();
    private final static Map<String, PacketType> stringPacketTypeHashMap = new HashMap<>();

    static {
        for (PacketType packetType : PacketType.values()) {
            packetTypeHashMap.put(packetType.getPacketClass(), packetType);
            stringPacketTypeHashMap.put(packetType.name(), packetType);
        }
    }

    private final Class<? extends Packet> packetClass;

    PacketType(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    public static PacketType getPacketTypeByClass(Class<? extends Packet> packetClass){
        return packetTypeHashMap.get(packetClass);
    }

    public static Optional<PacketType> getPacketTypeByName(String s){
        return Optional.ofNullable(stringPacketTypeHashMap.get(s));
    }



}
