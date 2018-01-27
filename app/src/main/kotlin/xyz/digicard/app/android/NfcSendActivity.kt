package xyz.digicard.app.android

import android.content.Context
import android.content.Intent
import android.nfc.NfcAdapter
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import timber.log.Timber
import java.util.*

class NfcSendActivity : AppCompatActivity() {

    private lateinit var progressBar: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nfc_send)
        progressBar = findViewById(R.id.progress_bar)
        val currentUserId = UUID.fromString(intent.extras.getString(CURRENT_USER_ID_KEY))

        val nfcAdapter = NfcAdapter.getDefaultAdapter(this)
        when (nfcAdapter) {
            null -> {
                Snackbar
                        .make(progressBar, R.string.nfc_not_available, Snackbar.LENGTH_LONG)
                        .show()
            }

            else -> {
                nfcAdapter.setNdefPushMessageCallback(NdefMessageCreator(currentUserId), this)
                Timber.e("Setup ndef message callback")
                Snackbar
                        .make(progressBar, R.string.trying_to_pair_up, Snackbar.LENGTH_LONG)
                        .show()
            }
        }
    }


    companion object {

        private const val CURRENT_USER_ID_KEY = "current_user_id_key"

        fun start(context: Context, currentUserId: UUID) {
            context.startActivity(Intent(context, NfcSendActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putString(CURRENT_USER_ID_KEY, currentUserId.toString())
                })
            })
        }
    }
}
