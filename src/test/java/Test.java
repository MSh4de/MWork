import eu.mshadeproduction.mwork.ContextType;
import eu.mshadeproduction.mwork.MWork;
import eu.mshadeproduction.mwork.Receiver;
import eu.mshadeproduction.mwork.ReceiverType;
import eu.mshadeproduction.mwork.player.PlayerItem;
import eu.mshadeproduction.mwork.service.*;
import eu.mshadeproduction.mwork.streaming.entity.PlayerMoveReduceEventStreaming;
import org.json.JSONObject;

import java.util.UUID;

public class Test implements Service {

    public Test() {
        PlayerMoveReduceEventStreaming playerMoveReduceEventStreaming = new PlayerMoveReduceEventStreaming(new PlayerItem(UUID.randomUUID(), "_RealAlpha_"));
        Receiver receiver = new Receiver(ReceiverType.ALL);
        JSONObject jsonObject = new JSONObject(MWork.get().serialize(playerMoveReduceEventStreaming));
        jsonObject.put("contextType", ContextType.STREAMING);
        jsonObject.put("receiver", receiver);
        System.out.println(jsonObject.toString());
    }

    public static void main(String[] args) {
        new Test();
    }


    private static int locToBlock(double loc) {
        final int floor = (int) loc;
        return floor == loc ? floor : floor - (int) (Double.doubleToRawLongBits(loc) >>> 63);
    }


}
