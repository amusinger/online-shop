package mail;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.jboss.logging.Logger;

import beans.OrderBean;

@MessageDriven(activationConfig = { 
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/MyQueue")
})
public class MyQueueConsumerMDB implements MessageListener {

    Logger log = Logger.getLogger(getClass());
    
    @EJB
    OrderBean orderBean;
    
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    public MyQueueConsumerMDB() {
    }
	
    public void onMessage(Message message) {
    	System.out.println("heeeey");
        try {
        
            TextMessage txt = (TextMessage) message;
        	System.out.println(txt);
            log.info("Message received - " + txt.getText());
            
            String emailReceiver = txt.getStringProperty("emailReceiver");
            String emailSubject  = txt.getStringProperty("emailSubject");
            String emailText     = txt.getText();
            
            boolean validEmail = emailReceiver != null && emailReceiver.matches(EMAIL_PATTERN);
            
            
            if (!validEmail) {
                log.error("Invalid email receiver" + emailReceiver + ", skipping subject " + emailSubject);
                return;
            }
 
            try {
				orderBean.sendOrderConfirmationMail(emailReceiver, emailSubject, txt.getText());
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
         //   orderBean.sendOrderConfirmationMail(txt.getText());
            
        } catch (JMSException e) {
            log.error("jms error", e);
        }
    }
}