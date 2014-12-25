import javax.jms.*;
import javax.naming.*;

public class SimpleReceiver
{

    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Usage: java SimpleReceiver <queue-name>");
            return;
        }

        Context                 jndiContext = null;
        QueueConnectionFactory  queueConnectionFactory = null;
        Queue                   queue = null;
        /* 
         * 1. Create a JNDI InitialContext object if none exists yet.
         * 2. Look up connection factory and queue.  If either does
         *    not exist, exit.
         */
        try {
            jndiContext = new InitialContext();
            queueConnectionFactory = (QueueConnectionFactory)
                    jndiContext.lookup("QueueConnectionFactory");
            queue = (Queue) jndiContext.lookup(args[0]);
        } catch (NamingException e) {
            e.printStackTrace();
            System.exit(0);
        }

        QueueConnection         queueConnection = null;
        QueueSession            queueSession = null;
        QueueReceiver           queueReceiver = null;
        TextMessage             message = null;
        /*
         * 3. Create connection.
         * 4. Create session from connection; false means session is
         *    not transacted.
         * 5. Create receiver, then start message delivery.
         * 6. Receive all text messages from queue until
         *    a non-text message is received indicating end of message stream.
         * 7. Close connection.
         */
        try {
            queueConnection =
                    queueConnectionFactory.createQueueConnection();
            queueSession =
                    queueConnection.createQueueSession(false,
                            Session.AUTO_ACKNOWLEDGE);
            queueReceiver = queueSession.createReceiver(queue);
            queueConnection.start();
            while (true) {
                Message m = queueReceiver.receive(1);
                if (m != null) {
                    if (m instanceof TextMessage) {
                        message = (TextMessage) m;
                        System.out.println("Reading message: " +
                                message.getText());
                    } else {
                        break;
                    }
                }
            }
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
