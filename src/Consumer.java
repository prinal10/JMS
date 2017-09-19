import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Consumer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ActiveMQConnectionFactory conFact = new ActiveMQConnectionFactory("tcp://LAPTOP-K7651B44:61616");
			Connection con  = conFact.createConnection();
			con.start();
			Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = ses.createQueue("Test_Q");
			
			
			MessageConsumer consumer = ses.createConsumer(dest);
			
			//producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			
			//String text = "Hello from : "+Thread.currentThread().getName();
			//TextMessage message = ses.createTextMessage(text);
			
			//producer.send(message);
			
			Message message = consumer.receive(1000);
			
			if(message instanceof TextMessage){
				System.out.println("Recieved Text : "+((TextMessage)message).getText());
			}else {
				System.out.println("Recieved : "+message);
			}
			
			ses.close();
			con.close();
			
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
