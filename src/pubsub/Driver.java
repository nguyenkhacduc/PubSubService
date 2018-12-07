package pubsub;

import pubsub.publisher.Publisher;
import pubsub.service.PubSubService;
import pubsub.subscriber.Subscriber;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Driver {
    public static void main(String[] args) throws IOException {

        Publisher firstPublisher = new Publisher();
        Publisher secondPublisher = new Publisher();
        Publisher thirdPublisher = new Publisher();

        Subscriber plusSubscriber = new Subscriber();
        Subscriber minusSubscriber = new Subscriber();
        Subscriber multiplySubscriber = new Subscriber();
        Subscriber divideSubscriber = new Subscriber();

        PubSubService pubSubService = new PubSubService();

        plusSubscriber.addSubscriber("plus", pubSubService);
        minusSubscriber.addSubscriber("minus", pubSubService);
        multiplySubscriber.addSubscriber("multiply", pubSubService);
        divideSubscriber.addSubscriber("divide",pubSubService);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        boolean exit = false;
        while (!exit){
            System.out.print("Input command: ");
            String command = bufferedReader.readLine();

            if (command.equalsIgnoreCase("exit")){
                exit = true;
                continue;
            }

            if (command.equalsIgnoreCase("print")){
                System.out.print("Input operation: ");
                String operation = bufferedReader.readLine();
                if (operation.equalsIgnoreCase("plus")) plusSubscriber.printFormula();
                if (operation.equalsIgnoreCase("minus")) minusSubscriber.printFormula();
                if (operation.equalsIgnoreCase("multiply")) multiplySubscriber.printFormula();
                if (operation.equalsIgnoreCase("divide")) divideSubscriber.printFormula();
            }

            if (command.equalsIgnoreCase("plus") || command.equalsIgnoreCase("minus")
                || command.equalsIgnoreCase("multiply") || command.equalsIgnoreCase("divide")){
                System.out.print("Input first number: ");
                int x = Integer.parseInt(bufferedReader.readLine());
                System.out.print("Input second number: ");
                int y = Integer.parseInt(bufferedReader.readLine());

                Formula formula = new Formula(x, y, command);
                Random random = new Random();
                int publisher = random.nextInt(3) + 1;
                if (publisher == 1) firstPublisher.publish(formula, pubSubService);
                if (publisher == 2) secondPublisher.publish(formula, pubSubService);
                if (publisher == 3) thirdPublisher.publish(formula, pubSubService);
            }

            if (command.equalsIgnoreCase("transfer queue")){
                System.out.println("Input subscriber name (\"ALL\" for transfer all): ");
                String subscriber = bufferedReader.readLine();
                if (subscriber.equalsIgnoreCase("all")) pubSubService.broadcast();
                if (subscriber.equalsIgnoreCase("plus")) plusSubscriber.getFormulasForSubscriberTopic(subscriber, pubSubService);
                if (subscriber.equalsIgnoreCase("minus")) minusSubscriber.getFormulasForSubscriberTopic(subscriber, pubSubService);
                if (subscriber.equalsIgnoreCase("multiply")) multiplySubscriber.getFormulasForSubscriberTopic(subscriber, pubSubService);
                if (subscriber.equalsIgnoreCase("divide")) divideSubscriber.getFormulasForSubscriberTopic(subscriber, pubSubService);
            }
        }
    }
}
