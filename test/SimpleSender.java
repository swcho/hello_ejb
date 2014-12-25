import javax.jms.*;
import javax.naming.*;

public class SimpleSender
{

    public static void main(String[] args)
    {
        if ( args.length != 1 )
        {
            System.out.println("Usage: java SimpleSender <queue-name>");
            return;
        }

        Context jndiContext = null;
        QueueConnectionFactory queueConnectionFactory = null;
        Queue queue = null;
        /* 
         * 1. Create a JNDI InitialContext object if none exists yet.
         * 2. Look up connection factory and queue.  If either does
         *    not exist, exit.
         */
        try {
            jndiContext = new InitialContext();
            queueConnectionFactory =
                    (QueueConnectionFactory)jndiContext.lookup("QueueConnectionFactory");
            queue = (Queue) jndiContext.lookup(args[0]);
        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(0);
        }

        QueueConnection queueConnection = null;
        QueueSession queueSession = null;
        QueueSender queueSender = null;
        TextMessage message = null;
        /*
         * 3. Create connection.
         * 4. Create session from connection; false means session is
         *    not transacted.
         * 5. Create sender and text message.
         * 6. Send messages, varying text slightly.
         * 7. Send end-of-messages message.
         * 8. Finally, close connection.
         */
        try {
            queueConnection =
                    queueConnectionFactory.createQueueConnection();
            queueSession =  	  	queueConnection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
            queueSender = queueSession.createSender(queue);
            message = queueSession.createTextMessage();
            for (int i = 0; i < 5; i++) {
                String msg = new java.util.Date().toString();
                message.setText(msg + "] Hello World!! " + (i + 1));
                System.out.println("Sending message: " +
                        message.getText());
                queueSender.send(message);
            }

            /* 
             * Send a non-text control message indicating end of
             * messages.
             */
            queueSender.send(queueSession.createMessage());
        } catch (JMSException e) {
            System.out.println("Exception occurred: " +
                    e.toString());
        } finally {
            if (queueConnection != null) {
                try {
                    queueConnection.close();
                } catch (JMSException e) {}
            }
        }
    }
}
