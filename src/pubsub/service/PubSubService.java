package pubsub.service;

import pubsub.Formula;
import pubsub.subscriber.Subscriber;

import java.util.*;

public class PubSubService {

    Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<String, Set<Subscriber>>();
    List<Formula> formulaQueue = new ArrayList<Formula>();

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
        boolean emptyList = true;
        while (!formulaQueue.isEmpty()){
            emptyList = false;
            Formula formula = formulaQueue.remove(0);
            String topic = formula.getTopic();

            Set<Subscriber> subscriberOfTopic = subscribersTopicMap.get(topic);
            for (Subscriber subscriber : subscriberOfTopic){
                List<Formula> subsriberFormulas = subscriber.getSubscribeFormulas();
                subsriberFormulas.add(formula);
                subscriber.setSubscribeFormulas(subsriberFormulas);
            }
        }

        if (emptyList){
            System.out.println("No formulas from publishers to display.");
        }
    }

    public void getFormulasForSubscriberTopic(String topic, Subscriber subscriber) {
        boolean emptyList = true;
        int index = 0;

        for (Formula formula : formulaQueue){
            if (formula.getTopic().equalsIgnoreCase(topic)){
                Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

                for (Subscriber _subscriber : subscribersOfTopic){
                    if (_subscriber.equals(subscriber)){
                        List<Formula> subscriberFormulas = subscriber.getSubscribeFormulas();
                        subscriberFormulas.add(formula);
                        subscriber.setSubscribeFormulas(subscriberFormulas);
                    }
                }

                emptyList = false;
                Collections.swap(formulaQueue, index, formulaQueue.indexOf(formula));
                index++;
            }
        }

        while (!formulaQueue.isEmpty() && formulaQueue.get(0).getTopic().equalsIgnoreCase(topic)){
            formulaQueue.remove(0);
        }

        if (emptyList){
            System.out.println("No formulas from publishers to display.");
        }
    }
}
