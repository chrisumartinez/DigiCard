package xyz.digicard.app.android

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.jayrave.falkon.dao.insert
import xyz.digicard.app.android.models.User
import xyz.digicard.app.android.models.UsersTable
import java.util.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<View>(R.id.nfc).setOnClickListener {

            // Until we have a login/signup screen I am creating a new
            // dummy user every time!
            val randomInt = Random().nextInt()
            val user = User(
                    id = UUID.randomUUID(),
                    firstName = "First_$randomInt",
                    lastName = "Last_$randomInt",
                    email = "Email_$randomInt"
            )

            // Put the user in the db & start beaming
            UsersTable.instance.dao.insert(user)
            NfcSendActivity.start(this, user.id)
        }
        findViewById<View>(R.id.register).setOnClickListener {
            startActivity(Intent(this, NewActivity::class.java))
        }
    }
}