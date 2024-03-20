package publisher;

import messages.Message;
import pubSubService.PubSubService;

public interface Publisher{
	void publish(Message message, PubSubService pubSubService);
}
