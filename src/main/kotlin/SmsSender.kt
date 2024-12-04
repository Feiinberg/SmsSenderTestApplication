package ru.itech.latit.service.util

import org.apache.camel.CamelContext
import org.apache.camel.builder.RouteBuilder
import org.apache.camel.impl.DefaultCamelContext
import org.apache.camel.ProducerTemplate
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component


//@Component
class SmsSender(private val smscHost: String, private val username: String, private val password: String, private val port: Int) {

    companion object {
        private const val OUR_SMSC_HOST = "80.75.132.6"
        private const val OUR_USERNAME = "ggmdgpz5hh7k"
        private const val OUR_PASSWORD = "J6sjNqm8"
        private const val OUR_PORT = 2775
    }
    constructor() : this(OUR_SMSC_HOST, OUR_USERNAME, OUR_PASSWORD, OUR_PORT)


    private val camelContext: CamelContext = DefaultCamelContext()
    private val logger: Logger = LoggerFactory.getLogger(SmsSender::class.java)

    init {
        // Configure the route for sending SMS
        camelContext.addRoutes(object : RouteBuilder() {
            override fun configure() {
                from("direct:sendSms")
                    .to("smpp://$username:$password@$smscHost:$port?systemId=$username")
            }
        })
        camelContext.start()
    }

    fun sendSms(to: String, message: String) {
        val template: ProducerTemplate = camelContext.createProducerTemplate()
        try {
            template.sendBodyAndHeader("direct:sendSms", message, "to", to)
            println("SMS sent to number $to: $message")
        } catch (e: Exception) {
            println("Error while sending SMS to number $to: ${e.message}")
        }
    }

    fun stop() {
        try {
            camelContext.stop()
            println("Camel context stopped.")
        } catch (e: Exception) {
            println("Error while stopping Camel context: ${e.message}")
        }
    }
}