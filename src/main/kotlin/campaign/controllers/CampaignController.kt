package campaign.controllers

import campaign.AuthorizationService
import campaign.database.MongoDBManager
import campaign.models.Campaign
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.util.JSONPObject
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.HashMap
import sun.security.provider.certpath.Vertex


@CrossOrigin(origins = ["https://volunteero-front-end-base.herokuapp.com/", "http://localhost:4200"])
@RestController
@RequestMapping("/campaigns")
class CampaignController {

    private val client = OkHttpClient()
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
        val campaign1 = databaseManager.add(campaign)
        val map = HashMap<String, String>()
        map.put("campaign_id", campaign1.id.toString())
        campaign.id = campaign1.id.toString()

        val list = HashMap<String, List<Campaign>>()
        list.put("entities", listOf(campaign))

        val mapperObj = ObjectMapper()
        mapperObj.writeValueAsString(campaign)

        val formBody = FormBody.Builder()
                .add("entities", "["+mapperObj.writeValueAsString(campaign)+"]")
                .build()
        val request = Request.Builder()
                .url("https://volunteero-search.herokuapp.com/api/v1/search/create")
                .post(formBody)
                .build()
        client.newCall(request).execute()
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