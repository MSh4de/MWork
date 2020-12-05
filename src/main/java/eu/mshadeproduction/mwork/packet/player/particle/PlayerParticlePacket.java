package eu.mshadeproduction.mwork.packet.player.particle;

import eu.mshadeproduction.mwork.packet.PacketHandle;
import eu.mshadeproduction.mwork.packet.items.PlayerItem;
import eu.mshadeproduction.mwork.packet.world.LocationItem;

import java.util.Optional;

public class PlayerParticlePacket extends PacketHandle {

    private final PlayerItem player;
    private final ParticleType particleType;
    private final LocationItem location;
    private final double offsetX, offsetY ,offsetZ, speed;
    private final int count;
    private final Optional<ColorItem> color;

    public PlayerParticlePacket(PlayerItem player, ParticleType particleType, LocationItem location, double offsetX, double offsetY, double offsetZ, double speed, int count, Optional<ColorItem> color) {
        this.player = player;
        this.particleType = particleType;
        this.location = location;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.speed = speed;
        this.count = count;
        this.color = color;
    }

    public PlayerItem getPlayer() {
        return player;
    }

    public ParticleType getParticleType() {
        return particleType;
    }

    public LocationItem getLocation() {
        return location;
    }

    public double getOffsetX() {
        return offsetX;
    }

    public double getOffsetY() {
        return offsetY;
    }

    public double getOffsetZ() {
        return offsetZ;
    }

    public double getSpeed() {
        return speed;
    }

    public int getCount() {
        return count;
    }

    public Optional<ColorItem> getColor() {
        return color;
    }
}
