package com.bikloo.c0752984_mad3125_midterm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener(View.OnClickListener {
            var i = Intent(this@LoginActivity,DashboardActivity::class.java)
            startActivity(i)
        })
    }
}
