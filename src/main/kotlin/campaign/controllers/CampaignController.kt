package campaign.controllers

import campaign.database.MongoDBManager
import campaign.models.Campaign
import org.springframework.http.ResponseEntity
import sun.rmi.runtime.Log
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/campaigns")
class CampaignController {

    final val databaseManager by lazy {
        MongoDBManager()
    }

    init {
        databaseManager.getById("wow")
        databaseManager.add(Campaign("111111", "Don Job", "Any job you need to be done", 10))
    }

    @GetMapping("/")
    fun getAllCampaigns(): ResponseEntity<List<Campaign>> {
        val campaigns = databaseManager.getAll()
        return if (campaigns.isEmpty()) {
            ResponseEntity<List<Campaign>>(HttpStatus.NO_CONTENT)
        } else ResponseEntity<List<Campaign>>(campaigns, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getCampaign(@PathVariable("id") id: String): ResponseEntity<Campaign> {
        val campaign = databaseManager.getById(id)
        if (campaign == null) {
            return ResponseEntity<Campaign>(HttpStatus.NOT_FOUND)
        }
        return ResponseEntity<Campaign>(campaign, HttpStatus.OK)
    }

    @PostMapping("/")
    fun createCampaign(@RequestBody campaign: Campaign): ResponseEntity<Unit> {
        databaseManager.add(campaign)
        return ResponseEntity<Unit>(HttpStatus.OK)
    }

    //TODO: create POST
    //TODO: update PATCH
}