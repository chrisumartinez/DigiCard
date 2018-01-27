package xyz.digicard.app.android

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class NewActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rename)

    }

    override fun onClick(v: View) {}
    fun openNewActivity(v: View) {}

}
