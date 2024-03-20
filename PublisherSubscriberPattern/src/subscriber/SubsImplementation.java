package subscriber;

import pubSubService.PubSubService;

public class SubsImplementation extends Subscriber{

	@Override
	public void addSubscriber(String topic, PubSubService pubSubService) {
		// TODO Auto-generated method stub
		pubSubService.subscribe(topic, this);
		
	}

	@Override
	public void unsubscribe(String topic, PubSubService pubSubService) {
		// TODO Auto-generated method stub
		pubSubService.unsubscribe(topic, this);
		
	}

	@Override
	public void getMessagesForSubscriberOfTopic(String topic, PubSubService pubSubService) {
		// TODO Auto-generated method stub
		pubSubService.getMessageForSubscriberOfTopic(topic, this);
	}


}
