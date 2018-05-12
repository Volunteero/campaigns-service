package campaign.controllers

import campaign.database.MongoDBManager
import campaign.models.Campaign
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestParam
import sun.rmi.runtime.Log


@RestController
class CampaignController {

    final val databaseManager by lazy {
        MongoDBManager()

    }

    init {

    }

    @GetMapping("/campaign")
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): String = "wow"

    //TODO: getByID GET
    //TODO: getAll GET
    //TODO: create POST
    //TODO: update PATCH
}