package xyz.digicard.app.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        findViewById<View>(R.id.nfc).setOnClickListener {
            startActivity(Intent(this, NfcActivity::class.java))
        }
    }
}