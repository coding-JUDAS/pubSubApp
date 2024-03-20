package DriverProgram;

import messages.Message;
import pubSubService.PubSubService;
import publisher.Publisher;
import subscriber.SubsImplementation;
import subscriber.Subscriber;

public class Tester {

	public static void main(String[] args) {
		// Instantiate Publishers, Subscribers, and PubSubService.
		PubSubService pubSubService = PubSubService.getInstance();
		
		// create new messages to publish - Data type
		Message math1 = new Message("Calculus", "Calculus is about the study of change...");
		Message compSciMessage = new Message("compSci", "Computer science is fun !");
		Message phyMessage = new Message("physics", "Particle science");
		Message statsMessage = new Message("stats", "Statistitcs is the sexiest job");
		
		//region instantiate publishers - FUNCTIONAL interface
		Publisher mathPubisher = (message, pubSubService1) -> {
			pubSubService1.addMessageToQueue(message);
		};
		Publisher compSciPublisher = (message, pubSubService1) -> {
			pubSubService1.addMessageToQueue(message);
		};
		Publisher phyPublisher = (message, pubSubService1) -> {
			pubSubService1.addMessageToQueue(message);
		};
		Publisher statsPublisher = (message, pubSubService1) -> {
			pubSubService1.addMessageToQueue(message);
		};
		//end-region
		
		// Instantiate subscribers
		Subscriber subscriber1 = new SubsImplementation();
		Subscriber subscriber2 = new SubsImplementation();
		Subscriber subscriber3 = new SubsImplementation();
		
		// register subscribers with PubSub Broker
		System.out.println("Registering subscribers with PubSub Broker");
		subscriber1.addSubscriber("stats", pubSubService);
		subscriber2.addSubscriber("stats", pubSubService);
		subscriber3.addSubscriber("physics", pubSubService);
		
		subscriber1.addSubscriber("Calculus", pubSubService);
		subscriber3.addSubscriber("compSci", pubSubService);
		
		subscriber2.addSubscriber("Calculus", pubSubService);
		subscriber1.addSubscriber("compSci", pubSubService);
		
		//publish messages
		System.out.println();
		compSciPublisher.publish(compSciMessage, pubSubService);
		statsPublisher.publish(statsMessage, pubSubService);
		mathPubisher.publish(math1, pubSubService);
		phyPublisher.publish(phyMessage, pubSubService);
		
		System.out.println("\t\tBroadcasting to subscribers");
		pubSubService.broadcast();
		System.out.println("\t\t\t...End of broadcast");
		System.out.println("===================================================");
		
		System.out.println("Mesages from subscribers by topic: ");
		
		System.out.println("Subscriber 1 : ");
		subscriber1.printMessages();
		System.out.printf("End of transmission....\n\n");
		
		System.out.println("Subscriber 2 : ");
		subscriber2.printMessages();
		System.out.printf("End of transmission....\n\n");
		
		System.out.println("Subscriber 3 : ");
		subscriber3.printMessages();
		System.out.printf("End of transmission....\n\n");
		
		Message compSciMessage1 = new Message("compSci", "Java is a fun programming language");
		Message compSciMessage2 = new Message("compSci", "eclipse is a cool IDE");
		
		Message statsMessage1 = new Message("stats", "Statistitcs is the sexiest job");
		Message statsMessage2 = new Message("stats", "Statistitcs is the sexiest job");
		System.out.println("=====================================================================");
		
		System.out.println("Publishing new messages...");
		compSciPublisher.publish(compSciMessage1, pubSubService);
		statsPublisher.publish(statsMessage1, pubSubService);
		statsPublisher.publish(statsMessage2, pubSubService);
		compSciPublisher.publish(compSciMessage2, pubSubService);
		System.out.printf("\t\t...broadcasting to subsribers\n\n");
		pubSubService.broadcast();
		System.out.printf(" \t\t....End of Broadcast\n\n");
		
		System.out.println("Getting messages by topic for subscriber 3 from pubSub broker. . .");
		System.out.println("NOTE: There should no more messages in PubSub messageQueue after a Broadcast");
		System.out.printf("RESULT:\t");
		pubSubService.getMessageForSubscriberOfTopic("compSci", subscriber3);
		System.out.printf("\n\t\t\t...Getting all messages from Subscriber 3\n");
		subscriber3.printMessages();
		
		System.out.printf("\n\t\t\t...Getting all messages from Subscriber 1\n");
		subscriber1.printMessages();
		
		System.out.printf("\n\t\t\t...Getting all messages from Subscriber 2\n");
		subscriber2.printMessages();
		
		System.out.println("\t\t\t=====================end of program=========================");
		System.out.println("Thank You !");
	}

}
