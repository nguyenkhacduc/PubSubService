package pubsub.subscriber;

import pubsub.Formula;
import pubsub.service.PubSubService;

import java.util.ArrayList;
import java.util.List;

public class Subscriber {
    private List<Formula> subscribeFormulas = new ArrayList<Formula>();

    public List<Formula> getSubscribeFormulas(){
        return subscribeFormulas;
    }

    public void setSubscribeFormulas(List<Formula> subscribeFormulas){
        this.subscribeFormulas = subscribeFormulas;
    }

    public void addSubscriber(String topic, PubSubService pubSubService){
        pubSubService.addSubscriber(topic, this);
    }

    public void removeSubscriber(String topic, PubSubService pubSubService){
        pubSubService.removeSubscriber(topic, this);
    }

    public void getFormulasForSubscriberTopic(String topic, PubSubService pubSubService){
        pubSubService.getFormulasForSubscriberTopic(topic, this);
    }

    public void printFormula(){
        int index = 0;
        for (Formula formula : subscribeFormulas){
            index++;
            System.out.println("Formula " + index + ": " + formula.print());
        }

        if (index == 0){
            System.out.println("Formula's not found.");
        }
    }
}
