package campaign.database

import campaign.models.Campaign
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Morphia

class MongoDBManager {

    val morphia: Morphia
    val datastore: Datastore

    init {
        val uri = MongoClientURI("mongodb+srv://prodrom:prodrom@campaigns-service-qaapp.mongodb.net/")
        val client = MongoClient(uri)

        morphia = Morphia()
        morphia.mapPackage("campaign.models")
        datastore = morphia.createDatastore(client, "Volunteero")
        datastore.ensureIndexes();
    }

    fun getById(id: String): Campaign = datastore.find(Campaign::class.java)
            .field("id")
            .equal(ObjectId(id))
            .first()

    fun getByOrganizationId(organizationId: String): List<Campaign> = datastore.find(Campaign::class.java)
            .field("organizationId")
            .equal(organizationId)
            .asList()

    fun getAll():List<Campaign>  = datastore.find(Campaign::class.java).asList()

    fun add(campaign: Campaign) {
        datastore.save(campaign)
    }

    fun updateInfluencePoints(influencePoints: Int) {
    }

}