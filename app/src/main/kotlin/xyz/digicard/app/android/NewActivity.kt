package xyz.digicard.app.android

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.jayrave.falkon.dao.insert
import xyz.digicard.app.android.models.User
import xyz.digicard.app.android.models.UsersTable
import java.util.*
import java.util.UUID.randomUUID

class NewActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rename)

        val submit_btn:Button = findViewById(R.id.submitButton)
        submit_btn.setOnClickListener(this);
    }

    override fun onClick(v: View) {
        val UUID = randomUUID()
        var view:EditText = findViewById(R.id.firstName)
        val firstName = view.getText().toString()
        view = findViewById(R.id.lastName)
        val lastName:String = view.getText().toString()
        view = findViewById(R.id.email)
        val email:String = view.getText().toString()
        view = findViewById(R.id.purpose)
        val purpose:String = view.getText().toString()
        val user = User(UUID, firstName, lastName, email, purpose)
        UsersTable.instance.dao.insert(user)
        Toast.makeText(v.context, "Register Complete!", Toast.LENGTH_LONG).show()
        val btn:Button = findViewById(R.id.submitButton)
        startActivity(Intent(this, Menu::class.java))
    }
}
