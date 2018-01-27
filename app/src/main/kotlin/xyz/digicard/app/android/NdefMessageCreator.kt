package xyz.digicard.app.android

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import com.jayrave.falkon.dao.findById
import timber.log.Timber
import xyz.digicard.app.android.models.UserMapper
import xyz.digicard.app.android.models.UsersTable
import java.util.*

class NdefMessageCreator(private val userId: UUID) : NfcAdapter.CreateNdefMessageCallback {
    override fun createNdefMessage(event: NfcEvent): NdefMessage {
        Timber.e("Creating an ndef message")
        val user = UsersTable.instance.dao.findById(userId)!!

        return NdefMessage(arrayOf(
                NdefRecord.createMime(
                        "application/vnd.xyz.digicard.app.android.beam",
                        UserMapper.toJson(user).toByteArray()
                )
        ))
    }
}