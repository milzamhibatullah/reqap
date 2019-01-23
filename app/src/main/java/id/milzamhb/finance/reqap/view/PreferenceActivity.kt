package id.milzamhb.finance.reqap.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintSet
import android.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.animation.AnticipateInterpolator
import id.milzamhb.finance.reqap.R
import kotlinx.android.synthetic.main.activity_preference.*
import kotlinx.android.synthetic.main.alt_welcome_text.*

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)
        supportActionBar!!.hide()


    }

}