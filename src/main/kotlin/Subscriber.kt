package com.rnimour.messageing.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.LongDeserializer
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.*

fun main() {

    // specify connection properties
    val props = Properties()
    props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = "localhost:9092"
    props[ConsumerConfig.GROUP_ID_CONFIG] = "MyFirstConsumer"
    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = LongDeserializer::class.java.name
    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java.name
    // receive messages that were sent before the consumer started
    props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "earliest"
    // time out after only 7.5 seconds: We poll every 5 seconds.
    // If we quit, Kafka won't wait so long until finding a new consumer.
    props[ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG] = 7500

    KafkaConsumer<Long, String>(props).use { consumer ->
        // subscribe to the topic.
        val topic = "my-first-topic"
        consumer.subscribe(listOf(topic))
        // poll messages from the topic and print them to the console
        while (true) {
            println("waiting for message") // debugging
            consumer
                .poll(Duration.ofSeconds(5))
                .forEach { println("Received message <${it.value()}>. Details: $it") }
        }
    }
}