package campaign.controllers

import campaign.database.MongoDBManager
import campaign.models.Campaign
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/campaigns")
class CampaignController {

    final val databaseManager by lazy {
        MongoDBManager()
    }

    init {
        println("**********WE ARE HERE DICKIE 1")
        databaseManager.add(Campaign("1234", "Don Job", "NONE", 50))
        println("**********WE ARE HERE DICKIE 3")
    }

    @GetMapping("/")
    fun getAllCampaigns(): ResponseEntity<List<Campaign>> {
        val campaigns = databaseManager.getAll()
        return if (campaigns.isEmpty()) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else ResponseEntity(campaigns, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getCampaign(@PathVariable("id") id: String): ResponseEntity<Campaign> {
        val campaign = databaseManager.getById(id)
        return ResponseEntity(campaign, HttpStatus.OK)
    }

    @GetMapping("/fromOrganization/{organizationId}")
    fun getCampaignByOrganizationId(@PathVariable("organizationId") orgazationId: String): ResponseEntity<List<Campaign>> {
        val campaign = databaseManager.getByOrganizationId(orgazationId)
        return ResponseEntity(campaign, HttpStatus.OK)
    }

    @PostMapping("/")
    fun createCampaign(@RequestBody campaign: Campaign): ResponseEntity<Unit> {
        databaseManager.add(campaign)
        return ResponseEntity(HttpStatus.OK)
    }

    @PatchMapping("/{id}/updateDescription")
    fun updateDescription(@RequestBody description: String, @PathVariable("id") id: String):
            ResponseEntity<Campaign> {
        databaseManager.updateDescription(id, description)
        return ResponseEntity(HttpStatus.OK)
    }

    @PatchMapping("/{id}/updateInfluencePoints")
    fun updateInfluencePoints(@RequestBody influencePoints: Int, @PathVariable("id") id: String):
            ResponseEntity<Campaign> {
        databaseManager.updateInfluencePoints(id, influencePoints)
        return ResponseEntity(HttpStatus.OK)
    }
}