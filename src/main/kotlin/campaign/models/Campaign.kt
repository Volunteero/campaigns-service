package campaign.models

import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id

@Entity("campaign")
data class Campaign(var organizationId: String = "", var name: String = "",
               var description: String = "", var influencePoints: Int = 0, var targetPoints: Int = 0, var rewardPoints: Int = 0) {
    @Id
    var id: String? = null
}


