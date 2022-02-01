package com.github.waris.kafka.tutorial1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerDemo {
    public static void main(String[] args) {
        System.out.println("hello world");
        String bootstrapServers = "127.0.0.1:9092";

//        create producer properties
        Properties property = new Properties();
        property.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        property.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        property.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

//        create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(property);

//        create producer record for topic
        ProducerRecord<String, String> record =
                new ProducerRecord<String, String>("first_topic", "hello kafka world");

//        send data-asynchronous
        producer.send(record);

//        flush data and close producer
        producer.flush();
        producer.close();

    }
}
