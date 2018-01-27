package xyz.digicard.app.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import java.util.*

class ConnectedUserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_connected_user)
        val userId = UUID.fromString(intent.extras.getString(USER_ID_KEY))

        supportFragmentManager
                .beginTransaction()
                .add(R.id.content_frame, ConnectedUserFragment.newInstance(userId))
                .commit()
    }


    companion object {

        private const val USER_ID_KEY = "user_id_key"

        fun startActivity(context: Context, userId: UUID) {
            context.startActivity(Intent(context, ConnectedUserActivity::class.java).apply {
                putExtra(USER_ID_KEY, userId.toString())
            })
        }
    }
}