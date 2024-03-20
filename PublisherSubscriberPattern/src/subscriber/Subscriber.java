package subscriber;

import java.util.ArrayList;
import java.util.List;

import messages.Message;
import pubSubService.PubSubService;

public abstract class Subscriber {
	
	private List<Message> subscriberMessages;
	
	public Subscriber() {
		subscriberMessages = new ArrayList<Message>();
	}
	public void setSubscriberMessages(List<Message> subscriberMessages) {
		this.subscriberMessages = subscriberMessages;
	}
	public abstract void addSubscriber(String topic, PubSubService pubSubService);
	public abstract void unsubscribe(String topic, PubSubService pubSubService);
	public abstract void getMessagesForSubscriberOfTopic(String topicString , PubSubService pubSubService);
	public void printMessages() {
		for(Message message : subscriberMessages) {
			System.out.println("Topic: " + message.getTopic() + " - " + message.getMessage());
		}
	}
	public List<Message> getSubscriberMessages(){
		return this.subscriberMessages;
	}
}
