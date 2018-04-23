package com.example.api.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    @Autowired
    private lateinit var userService: UserService

    @Transactional
    @RequestMapping(value = ["/user"], method = [RequestMethod.POST])
    fun user(@RequestBody user: String): String {

        userService.kafka(user)

        return user
    }
}