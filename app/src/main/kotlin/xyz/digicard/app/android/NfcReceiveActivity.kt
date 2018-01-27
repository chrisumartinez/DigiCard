package xyz.digicard.app.android

import android.content.Intent
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import timber.log.Timber

class NfcReceiveActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc_receive)
        textView = findViewById(R.id.nfc_data)
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
