package xyz.digicard.app.android

import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import timber.log.Timber

class NdefMessageCreator : NfcAdapter.CreateNdefMessageCallback {
    override fun createNdefMessage(event: NfcEvent): NdefMessage {
        Timber.e("Creating an ndef message")

        return NdefMessage(arrayOf(
                NdefRecord.createMime(
                        "application/vnd.xyz.digicard.app.android.beam",
                        "Digicard beam Time: ${System.currentTimeMillis()}".toByteArray()
                )
        ))
    }
}