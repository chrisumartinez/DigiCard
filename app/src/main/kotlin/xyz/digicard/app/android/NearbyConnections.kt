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


class NearbyConnections : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nearby_connections_screen)
    }

    override fun onClick(v: View) {}
    fun openNewActivity(v: View) {}

}
