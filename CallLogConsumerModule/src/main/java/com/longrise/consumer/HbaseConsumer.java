package com.longrise.consumer;

/**
 * @Author ：Tanger.
 * @Date ：Created in 15:53 2019/9/28
 * @Description：消费kafka主题calllogs的消息
 * @Version: 1.0$
 */

import com.longrise.consumer.udp.HeartBeatThread;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hbase消费者，从kafka提取数据，存储到hbase中。
 */
public class HbaseConsumer {
    public static void main(String[] args) {
        HbaseDao dao = new HbaseDao();
        //创建配置对象
        ConsumerConfig config = new ConsumerConfig(PropertiesUtil.props);

        //获得主题
        String topic = PropertiesUtil.getProp("topic");
        //
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put(topic, new Integer(1));
        Map<String, List<KafkaStream<byte[], byte[]>>> msgs = Consumer.createJavaConsumerConnector(new ConsumerConfig(PropertiesUtil.props)).createMessageStreams(map);

        List<KafkaStream<byte[], byte[]>> msgList = msgs.get(topic);

        String msg = null ;
        for (KafkaStream<byte[], byte[]> stream : msgList) {
            ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
                byte[] message = it.next().message();
                //取得kafka的消息
                msg = new String(message) ;
                //写入hbase中。
                dao.put(msg);
            }
        }

        //开启kafka消费者的心跳发送
        new HeartBeatThread().start();
    }
}

