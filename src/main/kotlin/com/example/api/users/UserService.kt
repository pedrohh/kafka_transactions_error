package com.example.api.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    private lateinit var kafka: KafkaTemplate<String, String>

    @Retryable(maxAttempts = 10, backoff = Backoff(5000))
    fun kafka(user: String) {

        println("----------------------------------- RUNNING RETRY...")

        kafka.send("user_updated", user).get()
    }
}