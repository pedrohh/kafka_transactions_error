package com.example.conf

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.context.annotation.Bean
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import java.util.HashMap
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.transaction.KafkaTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableKafka
@EnableTransactionManagement
class KafkaProducerConfig {

    @Autowired
    private lateinit var kafkaConfig: KafkaConfig

    @Bean
    fun producerFactory(): ProducerFactory<String, String> {
        val configProps = HashMap<String, Any>()

        configProps[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaConfig.bootstrap
        configProps[ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java
        configProps[ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG] = StringSerializer::class.java

        val factory = DefaultKafkaProducerFactory<String, String> (configProps)
        factory.setTransactionIdPrefix("txId")

        return factory
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, String> {
        return KafkaTemplate(producerFactory())
    }

    @Bean
    fun transactionManager(): KafkaTransactionManager<*, *> {

        return KafkaTransactionManager(producerFactory())
    }
}