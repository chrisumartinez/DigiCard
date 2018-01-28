package xyz.digicard.app.android

import android.content.Context
import java.util.*

object CurrentUser {

    private const val PREFS_NAME = "digitar_current_user"
    private const val USER_ID_KEY = "digitar_current_user_id_key"

    fun getId(context: Context): UUID? {
        val sharedPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val userIdString = sharedPrefs.getString(USER_ID_KEY, null)
        return when (userIdString) {
            null -> null
            else -> UUID.fromString(userIdString)
        }
    }


    fun putId(userId: UUID, context: Context) {
        context
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
                .edit()
                .putString(USER_ID_KEY, userId.toString())
                .apply()
    }
}