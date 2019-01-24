package id.milzamhb.finance.reqap.view

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.utils.StaticClass

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val pref= getSharedPreferences(StaticClass.FILE_NAME, Context.MODE_PRIVATE)
        val personalize= Intent(this,PreferenceActivity::class.java)
        val dashboard = Intent(this,MainActivity::class.java)
        val runnable= Runnable {
            if (pref.getString(StaticClass.PERSONALIZED,"") != "true"){
                startActivity(personalize)
                finish()
            }else{
                startActivity(dashboard)
                finish()
            }

        }
        Handler().postDelayed(runnable,4000)
    }
}
