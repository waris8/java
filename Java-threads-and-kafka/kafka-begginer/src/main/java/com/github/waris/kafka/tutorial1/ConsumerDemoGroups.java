package com.github.waris.kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ConsumerDemoGroups {
    public static void main(String[] args) {
//        System.out.println("hello from consumer");
        Logger logger = LoggerFactory.getLogger(ConsumerDemoGroups.class.getName());
        String bootstrapServers = "127.0.0.1:9092";
        String groupId = "my_third_app";
        String topic = "first_topic";

        // set property for consumer
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer with the defined properties
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // subscribe to the topics
        consumer.subscribe(Arrays.asList(topic));

        // poll new data
        while(true){
            ConsumerRecords<String, String> records =  consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record : records){
                logger.info("key : " + record.key() + " value : " + record.value());
                logger.info("partition : " + record.partition() + " offset : " + record.offset());
            }
        }

    }
}
