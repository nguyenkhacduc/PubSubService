package pubsub.service;

import pubsub.Formula;
import pubsub.subscriber.Subscriber;

import java.util.*;

public class PubSubService {

    Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<String, Set<Subscriber>>();
    Queue<Formula> formulaQueue = new LinkedList<Formula>();

    public void addMessageToQueue(Formula formula) {
        formulaQueue.add(formula);
    }

    public void addSubscriber(String topic, Subscriber subscriber) {
        Set<Subscriber> subscribers = getSubscriberFromTopic(topic);
        subscribers.add(subscriber);
        subscribersTopicMap.put(topic, subscribers);
    }

    private Set<Subscriber> getSubscriberFromTopic(String topic) {
        if (subscribersTopicMap.containsKey(topic)){
            return subscribersTopicMap.get(topic);
        }
        else{
            return new HashSet<Subscriber>();
        }
    }

    public void removeSubscriber(String topic, Subscriber subscriber) {
        if (subscribersTopicMap.containsKey(topic)){
            Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
            subscribers.remove(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }
    }

    public void broadcast(){
        boolean emptyQueue = true;
        while (!formulaQueue.isEmpty()){
            emptyQueue = false;
            Formula formula = formulaQueue.remove();
            String topic = formula.getTopic();

            Set<Subscriber> subscriberOfTopic = subscribersTopicMap.get(topic);
            for (Subscriber subscriber : subscriberOfTopic){
                List<Formula> subsriberFormulas = subscriber.getSubscribeFormulas();
                subsriberFormulas.add(formula);
                subscriber.setSubscribeFormulas(subsriberFormulas);
            }
        }

        if (emptyQueue){
            System.out.println("No formulas from publishers to display.");
        }
    }

    public void getFormulasForSubscriberTopic(String topic, Subscriber subscriber) {
        boolean emptyQueue = true;
        while (!formulaQueue.isEmpty()){
            emptyQueue = false;
            Formula formula = formulaQueue.remove();

            if (formula.getTopic().equalsIgnoreCase(topic)){
                Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

                for (Subscriber _subscriber : subscribersOfTopic){
                    if (_subscriber.equals(subscriber)){
                        List<Formula> subscriberFormulas = subscriber.getSubscribeFormulas();
                        subscriberFormulas.add(formula);
                        subscriber.setSubscribeFormulas(subscriberFormulas);
                    }
                }
            }
        }

        if (emptyQueue){
            System.out.println("No formulas from publishers to display.");
        }
    }
}
