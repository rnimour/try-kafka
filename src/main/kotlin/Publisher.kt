package com.rnimour.messageing.kafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.LongSerializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun main() {

    // specify connection properties
    val props = Properties()
    props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    props[ProducerConfig.CLIENT_ID_CONFIG] = "MyFirstConsumer"
    props[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = LongSerializer::class.java.name
    props[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java.name

    val topic = "my-first-topic"
    val producer = KafkaProducer<Long, String>(props)
    println("sending messages to topic $topic. To exit, enter (without quotes) 'exit'")
    do {
        val input = readln()
        println("Sending message: <$input>")
        val record = ProducerRecord<Long, String>(topic, input)
        producer.send(record).get() // get() to wait until message is sent
    } while (input != "exit")

    producer.close()
}