package hospital;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {

    private final static String ADMIN_LOG_EXCHANGE = "admin_log_exchange0";
    private final static String ADMIN_LISTENER_QUEUE = "admin_listener_queue";

    public static void main(String[] argv) throws Exception {
        System.out.println("Admin started.");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(ADMIN_LOG_EXCHANGE, BuiltinExchangeType.FANOUT);
        channel.queueDeclare(ADMIN_LISTENER_QUEUE, false, false, false, null);

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Logged: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(ADMIN_LISTENER_QUEUE, false, consumer);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter info message or 'exit' to quit: ");
            String message = br.readLine();
            if ("exit".equals(message)) {
                break;
            }

            channel.basicPublish(ADMIN_LOG_EXCHANGE, "", null, message.getBytes("UTF-8"));
            System.out.println("Sent info: " + message);
        }

        channel.close();
        connection.close();
    }
}
