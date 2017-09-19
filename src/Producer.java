import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Producer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			ActiveMQConnectionFactory conFact = new ActiveMQConnectionFactory("tcp://LAPTOP-K7651B44:61616");
			Connection con  = conFact.createConnection();
			con.start();
			Session ses = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination dest = ses.createQueue("Test_Q");
			MessageProducer producer = ses.createProducer(dest);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			
			
			String text = "Hello from : "+Thread.currentThread().getName();
			TextMessage message = ses.createTextMessage(text);
			
			System.out.println("Send: "+message);
			
			producer.send(message);
			
			ses.close();
			con.close();
			
			
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
