package xyz.digicard.app.android

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import com.google.android.gms.samples.vision.barcodereader.BarcodeCapture
import com.google.android.gms.samples.vision.barcodereader.BarcodeGraphic
import xyz.belvi.mobilevisionbarcodescanner.BarcodeRetriever
import com.google.android.gms.vision.barcode.Barcode
import com.jayrave.falkon.dao.insertOrReplace
import timber.log.Timber
import xyz.digicard.app.android.models.UserMapper
import xyz.digicard.app.android.models.UsersTable

class QrReaderActivity : AppCompatActivity(), BarcodeRetriever {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_reader)

        val barcodeCapture = supportFragmentManager.findFragmentById(R.id.barcode) as BarcodeCapture
        barcodeCapture.setRetrieval(this)
    }


    // for one time scan
    override fun onRetrieved(barcode: Barcode) {
        val json = barcode.displayValue
        Timber.d("Barcode read: $json")
        runOnUiThread {
            try {
                val user = UserMapper.fromJson(json)
                UsersTable.instance.dao.insertOrReplace(user)
                ConnectedUserActivity.startActivity(this, user.id)

            } catch (e: Exception) {
                Timber.e(e, null)
            }
        }

        finish()
    }


    override fun onRetrievedMultiple(closetToClick: Barcode?, barcode: MutableList<BarcodeGraphic>?) {
        // No op
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
        // No op
    }

    override fun onRetrievedFailed(reason: String?) {
        // No op
    }

    override fun onPermissionRequestDenied() {
        // No op
    }


    companion object {
        fun startActivity(context: Context) {
            context.startActivity(Intent(context, QrReaderActivity::class.java))
        }
    }
}