package xyz.digicard.app.android

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import timber.log.Timber

class NfcActivity : AppCompatActivity() {

    private lateinit var contentFrame: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)
        contentFrame = findViewById(R.id.content_frame)

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        when (nfcAdapter) {
            null -> {
                Snackbar
                        .make(contentFrame, R.string.nfc_not_available, Snackbar.LENGTH_LONG)
                        .show()
            }

            else -> {
                nfcAdapter.setNdefPushMessageCallback(NdefMessageCreator(), this)
                Timber.e("Setup ndef message callback")
                Snackbar
                        .make(contentFrame, R.string.trying_to_pair_up, Snackbar.LENGTH_LONG)
                        .show()
            }
        }
    }


    override fun onResume() {
        super.onResume()

        Timber.e("Activity resumed")
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) { // may be due to an Android Beam

            Timber.e("Activity resumed because of receiving NDEF message!")
            processIntent(intent)
        }
    }


    override fun onNewIntent(intent: Intent) {
        setIntent(intent) // onResume gets called after this to handle the intent
    }


    private fun processIntent(intent: Intent) {
        NdefMessageProcessor.process(
                ndefMessage = intent
                        .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
                        .first() as NdefMessage // Only one message is sent at a time
        )
    }
}
