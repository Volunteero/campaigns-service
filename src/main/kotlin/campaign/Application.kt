package campaign

import campaign.models.Campaign
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import jdk.nashorn.internal.parser.JSONParser
import okhttp3.FormBody
import okhttp3.Request
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude = [(MongoAutoConfiguration::class)])
class Application

fun main(args: Array<String>) {
  SpringApplication.run(Application::class.java, *args)
}


