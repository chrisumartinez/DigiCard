package xyz.digicard.app.android

import android.nfc.NdefMessage
import timber.log.Timber

object NdefMessageProcessor {
    fun process(ndefMessage: NdefMessage) {
        Timber.e("Received ndef message: ${String(ndefMessage.records.first().payload)}")
    }
}