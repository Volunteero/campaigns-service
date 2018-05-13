package campaign.models

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity("campaign")
class Campaign(var organizationId: String = "", var name: String = "",
               var description: String = "", var influencePoints: Int = 0) {

    @Id
    var id: String? = null
}