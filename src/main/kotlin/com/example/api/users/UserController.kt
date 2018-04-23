package com.example.api.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    private lateinit var kafka: KafkaTemplate<String, String>

    @Transactional
    @RequestMapping(value = ["/user"], method = [RequestMethod.POST])
    fun user(@RequestBody user: String): String {

        kafka.send("user_updated", user).get()

        return user
    }
}