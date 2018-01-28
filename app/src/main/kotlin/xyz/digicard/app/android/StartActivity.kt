package xyz.digicard.app.android

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val currentUserId = CurrentUser.getId(this)
        when (currentUserId) {
            null -> startActivity(Intent(this, NewActivity::class.java))
            else -> startActivity(Intent(this, Menu::class.java))
        }

        finish()
    }
}