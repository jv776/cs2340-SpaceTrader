package controllers;

/**
 * Created by Taylor on 10/14/14.
 */
public class RandomEventController {

    public void tryEvent(){
        tryEvent(1);
    }

    public void tryEvent(double probModifier){
        startEvent();
//        if (Math.random()*probModifier >.50){
//            startEvent();
//            tryEvent(probModifier/3);
//        }
    }

    public void startEvent(){
        //double eventType = Math.random();
        double eventType = 1;
        //not the best structure for this
        if(eventType<.33){
            TradeEventController event = new TradeEventController();
            //event.startEvent();
        }else if(eventType<.66){
            PoliceEventController event = new PoliceEventController();
            //event.startEvent();
        }else {
            PirateEventController event = new PirateEventController();
            //event.startEvent();
        }
    }


}
