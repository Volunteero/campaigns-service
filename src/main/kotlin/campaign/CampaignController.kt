package campaign

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam

@RestController
class CampaignController {

    @GetMapping("/campaign")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): String = "wow"
}