package pubsub.publisher;

import pubsub.Formula;
import pubsub.service.PubSubService;

public class Publisher {
    public void publish(Formula formula, PubSubService pubSubService){
        pubSubService.addMessageToQueue(formula);
    }
}
