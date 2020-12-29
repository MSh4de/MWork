package eu.mshadeproduction.mwork.service.entity;

import eu.mshadeproduction.mwork.player.PlayerItem;
import eu.mshadeproduction.mwork.player.ScoreboardItem;
import eu.mshadeproduction.mwork.service.Service;
import eu.mshadeproduction.mwork.service.TradeItem;
import eu.mshadeproduction.mwork.world.LocationItem;
import eu.mshadeproduction.mwork.world.ParticleType;

public interface PlayerService extends Service {

    void sendMessage(PlayerItem playerItem, TradeItem tradeItem, String message);

    void teleport(PlayerItem playerItem, TradeItem tradeItem, LocationItem locationItem);

    void sendParticle(PlayerItem playerItem, TradeItem tradeItem, ParticleType particleType, LocationItem location, double offsetX, double offsetY, double offsetZ, double speed, int count);

    int getHealth(PlayerItem playerItem, TradeItem tradeItem);

    int getFoodLevel(PlayerItem playerItem, TradeItem tradeItem);

    void getLocation(PlayerItem playerItem, TradeItem tradeItem);

    void getEyeLocation(PlayerItem playerItem, TradeItem tradeItem);

    void setVelocity(PlayerItem playerItem, TradeItem tradeItem);

    void isFlying(PlayerItem playerItem, TradeItem tradeItem);

    void getAllowFly(PlayerItem playerItem, TradeItem tradeItem);

    void sendScoreboard(PlayerItem playerItem, TradeItem tradeItem, ScoreboardItem scoreboardItem);

}
