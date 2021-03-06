package mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MQMessageConsumer {

  private Logger logger = LoggerFactory.getLogger(MQMessageConsumer.class);


  public boolean consume(String queueName) {
    //连接RabbitMQ
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("127.0.0.1");
    Connection connection = null;
    Channel channel = null;
    try {
      connection = factory.newConnection();
      channel = connection.createChannel();
      //这里声明queue是为了取消息的时候，queue肯定会存在
      //注意，queueDeclare是幂等的，也就是说，消费者和生产者，不论谁先声明，都只会有一个queue
      channel.queueDeclare(queueName, false, false, false, null);

      //这里重写了DefaultConsumer的handleDelivery方法，因为发送的时候对消息进行了getByte()，在这里要重新组装成String
      channel.basicQos(10);
      Channel finalChannel = channel;
      Consumer consumer = new DefaultConsumer(finalChannel) {
        @Override

        public void handleDelivery(String consumerTag, Envelope envelope,
            AMQP.BasicProperties properties, byte[] body)
            throws IOException

        {
          String message = new String(body, "UTF-8");
          logger.info("Received '" + message + "'");

          try {
            Thread.sleep(10000L);
            logger.info("我处理完了！");
          } catch (InterruptedException e) {
            e.printStackTrace();
          }finally {
            finalChannel.basicAck(envelope.getDeliveryTag(),false);
          }
        }

      };
      //上面是声明消费者，这里用声明的消费者消费掉队列中的消息
      //关闭自动确认
      channel.basicConsume(queueName, false, consumer);

      //这里不能关闭连接，调用了消费方法后，消费者会一直连接着rabbitMQ等待消费

    } catch (IOException | TimeoutException e) {
      //失败后记录日志，返回false，代表消费失败
      logger.error("send message faild!", e);
      return false;

    }

    return true;

  }

  //声明一个队列名字
  private final static String QUEUE_NAME = "hello";

  public static void main(String[] args) {
    MQMessageConsumer consumer = new MQMessageConsumer();
    consumer.consume(QUEUE_NAME);
  }
}
