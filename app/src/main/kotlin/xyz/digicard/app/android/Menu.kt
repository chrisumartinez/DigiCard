package xyz.digicard.app.android

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.jayrave.falkon.dao.findAll
import com.jayrave.falkon.dao.insert
import xyz.digicard.app.android.models.User
import xyz.digicard.app.android.models.UsersTable
import java.util.*
import java.util.UUID.randomUUID


class Menu : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userObjects = UsersTable.instance.dao.findAll()
        setContentView(R.layout.activity_menu)
        val rv:RecyclerView = findViewById(R.id.contact_list)
        rv.setHasFixedSize(true)
        val rvLayoutManager = LinearLayoutManager(this)
        rv.setLayoutManager(rvLayoutManager)
        val rvAdapter = NewAdapter(userObjects)
        rv.setAdapter(rvAdapter)
    }

    override fun onClick(v: View) {}
    fun openNewActivity(v: View) {}

}
