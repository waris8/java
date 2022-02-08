package com.github.waris.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithCallBack {
    public static void main(String[] args) {
        System.out.println("hello world");
        Logger logger = LoggerFactory.getLogger(ProducerDemoWithCallBack.class);
        String bootstrapServers = "127.0.0.1:9092";

//        create producer properties
        Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

//        create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(property);

//        create producer record for topic
        for(int i=0;i<10;i++) {
            ProducerRecord<String, String> record =
                    new ProducerRecord<String, String>("first_topic", "hello kafka world " + i);

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
            });
        }
//        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
//        flush data and close producer
        producer.flush();
        producer.close();

    }
}
