package xyz.digicard.app.android

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import timber.log.Timber

class NfcActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc)
        textView = findViewById(R.id.nfc_data)

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        when (nfcAdapter) {
            null -> {
                Snackbar
                        .make(textView, R.string.nfc_not_available, Snackbar.LENGTH_LONG)
                        .show()
            }

            else -> {
                nfcAdapter.setNdefPushMessageCallback(NdefMessageCreator(), this)
                Timber.e("Setup ndef message callback")
                Snackbar
                        .make(textView, R.string.trying_to_pair_up, Snackbar.LENGTH_LONG)
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
        // onResume gets called after this to handle the intent as
        // this is a `singleTop` activity
        setIntent(intent)
    }


    private fun processIntent(intent: Intent) {
        val message = intent
                .getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)
                .first() as NdefMessage // Only one message is sent at a time

        NdefMessageProcessor.process(message)
        if (BuildConfig.DEBUG) {
            textView.text = "${textView.text}\n${String(message.records.first().payload)}"
        }
    }
}
