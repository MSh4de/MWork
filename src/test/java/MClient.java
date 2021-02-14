import eu.mshadeproduction.mwork.MWork;
import eu.mshadeproduction.mwork.PacketDriver;
import eu.mshadeproduction.mwork.Receiver;
import eu.mshadeproduction.mwork.ReceiverType;
import eu.mshadeproduction.mwork.service.ServiceType;
import eu.mshadeproduction.mwork.service.TradeRequest;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class MClient {


    public MClient() throws Exception {
        MWork mWork = MWork.get();
        PacketDriver packetDriver = mWork.getPacketDriver();
        final WebSocketClient client = new WebSocketClient();
        client.start();
        final URI uri = new URI("ws://localhost:8080/mshade");
        Session session = client.connect(new MHandler(), uri).get();
        TradeRequest tradeRequest = new TradeRequest("auth", ServiceType.ATOM, new Receiver(ReceiverType.ATOM));
        tradeRequest.getParams().put("token", Base64.getEncoder().encodeToString("3b8d4856-e784-4d1c-9f5d-19b6845ec663".getBytes(StandardCharsets.UTF_8)));
        packetDriver.query(session, tradeRequest).thenAccept(System.out::println);
        System.out.println(Base64.getEncoder().encodeToString("3b8d4856-e784-4d1c-9f5d-19b6845ec663".getBytes(StandardCharsets.UTF_8)));
    }

    public static void main(String[] args) throws Exception{
        new MClient();
    }

}
