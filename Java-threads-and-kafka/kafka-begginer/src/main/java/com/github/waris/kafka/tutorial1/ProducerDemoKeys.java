package com.github.waris.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerDemoKeys {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        System.out.println("hello world");
        Logger logger = LoggerFactory.getLogger(ProducerDemoKeys.class);
        String bootstrapServers = "127.0.0.1:9092";

//        create producer properties
        Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

//        create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(property);

//        create producer record for topic
        String topic = "first_topic";
        for(int i=0;i<10;i++) {
            String value = "hello kafka world " + i;
            String key = "id_" + i;
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>(topic, key, value);
            logger.info("Key: " + key);
//        send data-asynchronous
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
//                    record was sent successfully
                        logger.info("received new metadata. \n"
                                + "Topic : " + recordMetadata.topic() + "\n"
                                + "Partition : " + recordMetadata.partition() + "\n"
                                + "Offset : " + recordMetadata.offset() + "\n"
                                + "Timestamp : " + recordMetadata.timestamp());
                    } else {
                        logger.error("Error while producing", e);
                    }
                }
            }).get();
        }

//        flush data and close producer
        producer.flush();
        producer.close();

    }
}
