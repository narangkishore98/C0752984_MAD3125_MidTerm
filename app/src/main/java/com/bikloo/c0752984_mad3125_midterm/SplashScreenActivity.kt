package com.bikloo.c0752984_mad3125_midterm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {


    private var delayHandler: Handler? = null
    private val SPLASH_DELAY:Long = 5000 // five seconds or five thousand milliseconds


    internal val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        delayHandler = Handler()
        
        supportActionBar?.hide()
        delayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

       // imageLogo.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.background))
        //imageLogo.setImageResource(R.drawable.background)
       // Glide.with(this@SplashScreenActivity).load(R.drawable.logo).into(imageLogo)

    }

    public override fun onDestroy() {

        if (delayHandler != null) {
            delayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
