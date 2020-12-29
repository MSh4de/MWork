package eu.mshadeproduction.mwork.service;

import eu.mshadeproduction.mwork.player.PlayerItem;
import eu.mshadeproduction.mwork.player.ScoreboardItem;
import eu.mshadeproduction.mwork.world.LocationItem;
import eu.mshadeproduction.mwork.world.ParticleType;
import eu.mshadeproduction.mwork.world.WorldItem;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum TradeContextType {

    PLAYER(PlayerItem.class),
    LOCATION(LocationItem.class),
    WORLD(WorldItem.class),
    PARTICLE(ParticleType.class),
    SCOREBOARD(ScoreboardItem.class),
    NUMBER(Double.class),
    BOOLEAN(Boolean.class),
    STRING(String.class);

    private final Class<?> bodyClass;
    private static final Map<Class<?>, TradeContextType> MAP = new HashMap<>();

    static {
        for (TradeContextType tradeContextType : TradeContextType.values()) {
            MAP.put(tradeContextType.getBodyClass(), tradeContextType);
        }
    }

    TradeContextType(Class<?> bodyClass) {
        this.bodyClass = bodyClass;
    }

    public Class<?> getBodyClass() {
        return bodyClass;
    }

    public static Optional<TradeContextType> getBodyTypeByClass(Class<?> bodyClass){
        return Optional.ofNullable(MAP.get(bodyClass));
    }
}
