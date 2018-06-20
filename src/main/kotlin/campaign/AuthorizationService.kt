package campaign

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.FormBody



class AuthorizationService {

    private val client = OkHttpClient()

    fun isUserAuthorizedOnResource(accessToken: String, resource: String, action: String) {
        val formBody = FormBody.Builder()
                .add("message", "token")
                .build()
        val request = Request.Builder()
                .url("https://volunteero-auth.herokuapp.com/auth/access?accessToken=$accessToken&resource=$resource&action=$action")
                .post(formBody)
                .build()
        val response = client.newCall(request).execute()
    }
}