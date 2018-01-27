package xyz.digicard.app.android

import android.nfc.NdefMessage
import com.jayrave.falkon.dao.insertOrReplace
import timber.log.Timber
import xyz.digicard.app.android.models.UserMapper
import xyz.digicard.app.android.models.UsersTable
import java.util.*

object NdefMessageProcessor {

    /**
     * Extracts user from the incoming message, puts it in the db & returns the UUID
     */
    fun process(ndefMessage: NdefMessage): UUID? {
        val userJson = String(ndefMessage.records.first().payload)

        return try {
            val user = UserMapper.fromJson(userJson)
            UsersTable.instance.dao.insertOrReplace(user)
            user.id

        } catch (e: Exception) {
            Timber.e(e, null)
            null
        }
    }
}