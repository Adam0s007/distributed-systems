package hospital;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Admin {

    private final static String ADMIN_LOG_EXCHANGE = "admin_log_exchange2";
    private final static String ADMIN_LISTENER_QUEUE = "admin_listener_queue";

    public static void main(String[] argv) throws Exception {
        System.out.println("Admin started.");
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(ADMIN_LOG_EXCHANGE, BuiltinExchangeType.TOPIC);
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
            System.out.println("Enter message type (all, doctors, Doctor1, Doctor2, technicians, Technician1, Technician2) or 'exit' to quit: ");
            String messageType = br.readLine();
            if ("exit".equals(messageType)) {
                break;
            }

            System.out.println("Enter message: ");
            String message = br.readLine();

            String routingKey = "";
            switch (messageType.toLowerCase()) {
                case "all":
                    routingKey = "all";
                    break;
                case "doctors":
                    routingKey = "Doctor";
                    break;
                case "doctor1":
                    routingKey = "Doctor1";
                    break;
                case "doctor2":
                    routingKey = "Doctor2";
                    break;
                case "technicians":
                    routingKey = "Technician";
                    break;
                case "technician1":
                    routingKey = "Technician1";
                    break;
                case "technician2":
                    routingKey = "Technician2";
                    break;
                default:
                    System.out.println("Invalid message type");
                    continue;
            }

            channel.basicPublish(ADMIN_LOG_EXCHANGE, routingKey, null, message.getBytes("UTF-8"));
            System.out.println("Sent info: " + message + " to " + messageType);
        }

        channel.close();
        connection.close();
    }
}
