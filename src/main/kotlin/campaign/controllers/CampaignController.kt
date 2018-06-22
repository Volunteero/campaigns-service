package campaign.controllers

import campaign.AuthorizationService
import campaign.database.MongoDBManager
import campaign.models.Campaign
import com.fasterxml.jackson.databind.util.JSONPObject
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import sun.security.provider.certpath.Vertex



@CrossOrigin(origins = ["https://volunteero-campaigns.herokuapp.com/"])
@RestController
@RequestMapping("/campaigns")
class CampaignController {

    private final val databaseManager by lazy {
         MongoDBManager()
    }

    //final val authorization:AuthorizationService

    init {
        //databaseManager.add(Campaign("1234", "Don Job", "NONE", 50))
        //authorization = AuthorizationService()
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
    fun createCampaign(@RequestBody campaign: Campaign, @RequestParam accessToken: String): ResponseEntity<Any>? {
        //authorization.isUserAuthorizedOnResource()
        val campaign = databaseManager.add(campaign)
        val map = HashMap<String, String>()
        map.put("campaign_id", campaign.id.toString())
        return ResponseEntity.ok(map);
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