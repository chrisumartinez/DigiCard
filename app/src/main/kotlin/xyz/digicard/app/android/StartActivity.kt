package xyz.digicard.app.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import java.lang.ref.WeakReference

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        findViewById<View>(R.id.root).postDelayed(
                ActivityStarter(WeakReference(this)),
                2000L
        )
    }


    private class ActivityStarter(private val activityWr: WeakReference<Activity>): Runnable {
        override fun run() {
            val activity = activityWr.get()
            if (activity != null) {
                val currentUserId = CurrentUser.getId(activity)
                when (currentUserId) {
                    null -> activity.startActivity(Intent(activity, NewActivity::class.java))
                    else -> activity.startActivity(Intent(activity, Menu::class.java))
                }

                activity.finish()
            }
        }
    }
}