package id.milzamhb.finance.reqap.view

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.milzamhb.finance.reqap.R
import id.milzamhb.finance.reqap.utils.StaticClass

class PreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        supportActionBar!!.hide()
    }
}