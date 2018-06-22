package campaign.database

import campaign.models.Campaign
import com.mongodb.MongoClient
import com.mongodb.MongoClientURI
import org.bson.types.ObjectId
import org.mongodb.morphia.Datastore
import org.mongodb.morphia.Key
import org.mongodb.morphia.Morphia
import org.mongodb.morphia.query.Query

class MongoDBManager {

    private val datastore: Datastore

    init {
        val client = MongoClient(MongoClientURI("mongodb+srv://prodrom:prodrom@campaigns-service-qaapp.mongodb.net/"))
        val morphia = Morphia()
        morphia.mapPackage("campaign.models")

        datastore = morphia.createDatastore(client, "Volunteero")
        datastore.ensureIndexes()

    }

    fun getAll(): List<Campaign> = datastore.find(Campaign::class.java).asList()

    fun getById(id: String): Campaign = datastore.find(Campaign::class.java)
            .field("id")
            .equal(ObjectId(id))
            .first()

    fun getByOrganizationId(organizationId: String): List<Campaign> = datastore.find(Campaign::class.java)
            .field("organizationId")
            .equal(organizationId)
            .asList()

    fun add(campaign: Campaign): Key<Campaign> = datastore.save(campaign)

    fun updateDescription(id: String, description: String) {
        val updateOperations = datastore.createUpdateOperations(Campaign::class.java)
                .set("description", description)

        datastore.update(createQueryForRecord(id), updateOperations)
    }

    fun updateInfluencePoints(id: String, influencePoints: Int) {
        val updateOperations = datastore.createUpdateOperations(Campaign::class.java)
                .inc("influencePoints", influencePoints)

        datastore.update(createQueryForRecord(id), updateOperations)
    }

    private fun createQueryForRecord(id: String): Query<Campaign> = datastore.createQuery(Campaign::class.java)
            .field("id")
            .equal(ObjectId(id))
}