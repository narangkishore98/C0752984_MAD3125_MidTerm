package com.bikloo.c0752984_mad3125_midterm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bikloo.c0752984_mad3125_midterm.core.User
import com.bikloo.c0752984_mad3125_midterm.core.database.DBHelper
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val db = DBHelper(this@LoginActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        db.readUsers()
        btnLogin.setOnClickListener(View.OnClickListener {
            if(db.validate(edtEmail.text.toString(), edtPassword.text.toString()))
            {
                var i = Intent(this@LoginActivity,DashboardActivity::class.java)
                startActivity(i)
            }
            else
            {
                Toast.makeText(this@LoginActivity, "Invalid Username & Password Combination", Toast.LENGTH_LONG).show()
            }
        })
    }
}
