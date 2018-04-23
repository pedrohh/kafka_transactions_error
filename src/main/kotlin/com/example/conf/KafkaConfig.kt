package com.example.conf

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "kafka")
class KafkaConfig {

    var bootstrap: String = ""

    var groupId: String = ""
}
