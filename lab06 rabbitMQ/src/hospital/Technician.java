package hospital;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Technician {

    private final static String DOCTOR_REQ_EXCHANGE = "doctor_req_exchange";
    private final static String TECHNICIAN_CONFIRM_EXCHANGE = "technician_confirm_exchange";
    private final static String ADMIN_LISTENER_QUEUE = "admin_listener_queue";
    private final static String ADMIN_LOG_EXCHANGE = "admin_log_exchange0";
    private final String[] expertise;
    private final String technicianName;
    private final int technicianNumber;
    private final String technicianLogQueue;

    public Technician(String[] expertise, String technicianName, int technicianNumber, String technicianLogQueue) {
        this.expertise = expertise;
        this.technicianName = technicianName;
        this.technicianNumber = technicianNumber;
        this.technicianLogQueue = technicianLogQueue;
    }

    public static void main(String[] argv) throws Exception {
        if (argv.length != 1) {
            System.err.println("Usage: Technician [technicianNumber]");
            System.exit(1);
        }

        int technicianNumber = Integer.parseInt(argv[0]);
        System.out.println("Technician" + technicianNumber + " started.");
        Technician technician;
        if (technicianNumber == 1) {
            technician = new Technician(new String[]{"knee", "elbow"}, "Technician1",1, "technician1_log_queue");
        } else {
            technician = new Technician(new String[]{"knee", "hip"}, "Technician2",2, "technician2_log_queue");
        }
        technician.run();
    }

    public void run() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(ADMIN_LISTENER_QUEUE, false, false, false, null);

        channel.exchangeDeclare(DOCTOR_REQ_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(TECHNICIAN_CONFIRM_EXCHANGE, BuiltinExchangeType.TOPIC);


        channel.exchangeDeclare(ADMIN_LOG_EXCHANGE, BuiltinExchangeType.FANOUT);
        channel.queueBind(this.technicianLogQueue, ADMIN_LOG_EXCHANGE, "");

        for (String testKey : expertise) {
            String queueName = testKey + "_tech_queue";
            channel.queueDeclare(queueName, false, false, false, null);
            channel.queueBind(queueName, DOCTOR_REQ_EXCHANGE, testKey);
        }


        for (String testType : expertise) {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, "UTF-8");
                    System.out.println(technicianName + " received: " + message);

                    String[] parts = message.split(" ");
                    String patientName = parts[0];
                    String testType = parts[1];
                    String doctorName = parts[2];

                    try {
                       if(testType.equals("knee")){
                           Thread.sleep(1000);
                       } else if(testType.equals("elbow")) {
                           Thread.sleep(15000);
                       } else if(testType.equals("hip")) {
                           Thread.sleep(5000);
                       }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String result = patientName + " " + testType + " done";

                    channel.basicPublish(TECHNICIAN_CONFIRM_EXCHANGE, "confirm." + doctorName, null, result.getBytes("UTF-8"));
                    System.out.println(technicianName + " sent: " + result);

                    logActivity(channel, "Processed test: " + message + " Result: " + result);


                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            };
            channel.basicQos(1);
            channel.basicConsume(testType + "_tech_queue", false, consumer);
        }

        Consumer logConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(technicianName + " log received: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(technicianLogQueue, false, logConsumer);
    }

    private void logActivity(Channel channel, String logMessage) throws IOException {
        channel.basicPublish("", ADMIN_LISTENER_QUEUE, null, logMessage.getBytes("UTF-8"));
    }
}
