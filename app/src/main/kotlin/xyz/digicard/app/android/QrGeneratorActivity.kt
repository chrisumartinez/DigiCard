package xyz.digicard.app.android

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import com.jayrave.falkon.dao.findById
import xyz.digicard.app.android.models.UserMapper
import xyz.digicard.app.android.models.UsersTable
import java.util.*

class QrGeneratorActivity : AppCompatActivity() {

    private lateinit var userId: UUID
    private lateinit var qrCodeView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        userId = UUID.fromString(intent.extras.getString(USER_ID_KEY))

        setContentView(R.layout.activity_qr_generator)
        qrCodeView = findViewById(R.id.qr_code)
        qrCodeView.setImageBitmap(generateQrCode())
    }


    private fun generateQrCode(): Bitmap {
        val userJson = UserMapper.toJson(UsersTable.instance.dao.findById(userId)!!)
        return QRGEncoder(userJson, null, QRGContents.Type.TEXT, 600).encodeAsBitmap()
    }



    companion object {
        private const val USER_ID_KEY = "user_id_key"

        fun startActivity(userId: UUID, context: Context) {
            context.startActivity(Intent().apply {
                extras.putString(USER_ID_KEY, userId.toString())
            })
        }
    }
}