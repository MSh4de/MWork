import eu.mshadeproduction.mwork.service.*;

public class Test implements Service {

    public Test() {
        ServiceCreator serviceCreator = new ServiceCreator(this);
        TradeRequest tradeRequest = new TradeRequest("test", ServiceType.PLAYER);
        TradeResponse tradeResponse = new TradeResponse(tradeRequest.getId());
        serviceCreator.invokeService(tradeRequest, tradeResponse);

        System.out.println(tradeResponse.getResult());

        //tu vois ?
    }

    public static void main(String[] args) {
        new Test();
    }

    @TradeAction(name = "test")
    public boolean test(){
        return true;
    }

}
