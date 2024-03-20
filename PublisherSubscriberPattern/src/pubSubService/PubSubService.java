package pubSubService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import messages.Message;
import subscriber.Subscriber;

public class PubSubService {
	private HashMap<String, Set<Subscriber>> topicSubscriberMap = 
			new HashMap<String, Set<Subscriber>>();
	private Queue<Message> messageQueue = new LinkedList<Message>(); 
	private static PubSubService pUB_SubService = null;
	private PubSubService() {}
	
	// Singleton Pattern
	public static PubSubService getInstance() {
		if(pUB_SubService == null)
			pUB_SubService = new PubSubService();
		return pUB_SubService;
	}

	public void subscribe(String topic, Subscriber subsImplementation) {
		// TODO Auto-generated method stub
		if(topicSubscriberMap.containsKey(topic)) {
			topicSubscriberMap.get(topic).add(subsImplementation);
		}
		else {
			Set<Subscriber> subscribers = new HashSet<Subscriber>();
			subscribers.add(subsImplementation);
			topicSubscriberMap.put(topic, subscribers);
		}
	}

	public void unsubscribe(String topic, Subscriber subsImplementation) {
		// TODO Auto-generated method stub
		if(topicSubscriberMap.containsKey(topic)) {
			Set<Subscriber> subs1 = topicSubscriberMap.get(topic);
			subs1.remove(subsImplementation);
			topicSubscriberMap.put(topic, subs1);
		}
	}
	public void addMessageToQueue(Message mesage) {
		messageQueue.add(mesage);
	}

	public void getMessageForSubscriberOfTopic(String topic, Subscriber subscriber) {
		// TODO Auto-generated method stub
		if(messageQueue.isEmpty()) {
			System.out.println("No messages from publishers to displa");
		}
		else {
			while(!messageQueue.isEmpty()) {
				Message message = messageQueue.remove();
				if(message.getTopic().equalsIgnoreCase(topic)) {
					Set<Subscriber> subscribers = topicSubscriberMap.get(topic);
					subscribers.forEach((sub) -> {
						if(sub.equals(subscriber)) {
							List<Message> messages = sub.getSubscriberMessages();
							messages.add(message);
							sub.setSubscriberMessages(messages);
						}
					});
				}
			}
		}
	}
	public void broadcast() {
		while(!messageQueue.isEmpty()) {
			Message message = messageQueue.remove();
			String topicString = message.getTopic();
			if(topicSubscriberMap.containsKey(topicString)) {
				Set<Subscriber> subscribers = topicSubscriberMap.get(topicString);
				for(Subscriber subscriber : subscribers) {
					List<Message> subscriberMessages = subscriber.getSubscriberMessages();
					subscriberMessages.add(message);
					subscriber.setSubscriberMessages(subscriberMessages);
				}
			}
		}
	}
	

}
