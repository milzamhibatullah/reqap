package id.milzamhb.finance.reqap.view

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import id.milzamhb.finance.reqap.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val intent= Intent(this,PreferenceActivity::class.java)
        val runnable= Runnable {
            startActivity(intent)
            finish()
        }
        Handler().postDelayed(runnable,4000)
    }
}
