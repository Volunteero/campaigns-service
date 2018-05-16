package campaign

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration

@SpringBootApplication
@EnableAutoConfiguration(exclude=arrayOf(MongoAutoConfiguration::class))
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}




