package hospital;

import com.rabbitmq.client.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Doctor {

    private final static String DOCTOR_REQ_EXCHANGE = "doctor_req_exchange";
    private final static String TECHNICIAN_CONFIRM_EXCHANGE = "technician_confirm_exchange";
    private final static String ADMIN_LISTENER_QUEUE = "admin_listener_queue";
    private final static String ADMIN_LOG_EXCHANGE = "admin_log_exchange0";
    private final String doctorConfirmQueue;
    private final String doctorLogQueue;
    private final String doctorName;

    public Doctor(String doctorConfirmQueue, String doctorLogQueue, String doctorName) {
        this.doctorConfirmQueue = doctorConfirmQueue;
        this.doctorLogQueue = doctorLogQueue;
        this.doctorName = doctorName;
    }

    public static void main(String[] argv) throws Exception {
        if (argv.length != 1) {
            System.err.println("Usage: Doctor [doctorNumber]");
            System.exit(1);
        }

        int doctorNumber = Integer.parseInt(argv[0]);
        Doctor doctor = new Doctor("doctor" + doctorNumber + "_confirm_queue", "doctor" + doctorNumber + "_log_queue", "Doctor" + doctorNumber);
        System.out.println(doctor.doctorName + " started.");
        doctor.run();
    }

    public void run() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(DOCTOR_REQ_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(TECHNICIAN_CONFIRM_EXCHANGE, BuiltinExchangeType.TOPIC);

        channel.queueDeclare(doctorConfirmQueue, false, false, false, null);

        channel.queueDeclare(ADMIN_LISTENER_QUEUE, false, false, false, null);
        channel.queueBind(doctorConfirmQueue, TECHNICIAN_CONFIRM_EXCHANGE, "confirm." + doctorName);

        channel.queueDeclare(doctorLogQueue, false, false, false, null);
        channel.exchangeDeclare(ADMIN_LOG_EXCHANGE, BuiltinExchangeType.FANOUT);
        channel.queueBind(doctorLogQueue, ADMIN_LOG_EXCHANGE, "");

        Consumer confirmConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(doctorName + " received: " + message);
                logActivity(channel, "Received result: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicQos(1);
        channel.basicConsume(doctorConfirmQueue, false, confirmConsumer);

        Consumer logConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(doctorName + " log received: " + message);
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(doctorLogQueue, false, logConsumer);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.println("Enter test type (hip, knee, elbow) or 'exit' to quit: ");
            String testType = br.readLine();
            if ("exit".equals(testType)) {
                break;
            }

            System.out.println("Enter patient name: ");
            String patientName = br.readLine();

            String message = patientName + " " + testType + " " + doctorName;
            channel.basicPublish(DOCTOR_REQ_EXCHANGE, testType, null, message.getBytes("UTF-8"));
            System.out.println(doctorName + " sent: " + message);
            logActivity(channel, "Sent test request: " + message);
        }

        channel.close();
        connection.close();
    }

    private void logActivity(Channel channel, String logMessage) throws IOException {
        channel.basicPublish("", ADMIN_LISTENER_QUEUE, null, logMessage.getBytes("UTF-8"));
    }
}
