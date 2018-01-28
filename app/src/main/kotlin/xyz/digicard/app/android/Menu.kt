package xyz.digicard.app.android

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.jayrave.falkon.dao.findAll
import com.mikepenz.community_material_typeface_library.CommunityMaterial
import com.mikepenz.iconics.IconicsDrawable
import xyz.digicard.app.android.models.UsersTable


class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userObjects = UsersTable.instance.dao.findAll()
        setContentView(R.layout.activity_menu)

        val recyclerView = findViewById<RecyclerView>(R.id.contact_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = NewAdapter(userObjects)

        val addViaNfc = findViewById<ImageView>(R.id.add_via_nfc)
        addViaNfc.setOnClickListener { NfcSendActivity.start(this, CurrentUser.getId(this)!!) }
        addViaNfc.setImageDrawable(
                IconicsDrawable(this, CommunityMaterial.Icon.cmd_nfc)
                        .actionBar()
                        .color(Color.WHITE)
        )

        val addViaQrcode = findViewById<ImageView>(R.id.add_via_qrcode)
        addViaQrcode.setOnClickListener {
            QrGeneratorActivity.startActivity(CurrentUser.getId(this)!!, this)
        }

        addViaQrcode.setImageDrawable(
                IconicsDrawable(this, CommunityMaterial.Icon.cmd_qrcode)
                        .actionBar()
                        .color(Color.WHITE)
        )
    }
}
