package com.bikloo.c0752984_mad3125_midterm

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
        val sp = getSharedPreferences("kishore", Context.MODE_PRIVATE)
        var editor = sp.edit()

        if(sp.contains("Email"))
        {
            edtEmail.setText(sp.getString("Email",""))
            edtPassword.setText(sp.getString("Password",""))


        }
        if(!checkBox.isChecked)
        {
            editor.clear()
            editor.commit()
        }

        db.readUsers()
        btnLogin.setOnClickListener(View.OnClickListener {
            if(db.validate(edtEmail.text.toString(), edtPassword.text.toString()))
            {
                var i = Intent(this@LoginActivity,DashboardActivity::class.java)
                startActivity(i)
                if(checkBox.isChecked)
                {

                    editor.putString("Email",edtEmail.text.toString())
                    editor.putString("Password",edtPassword.text.toString())
                    editor.commit()
                }
            }
            else
            {
                Toast.makeText(this@LoginActivity, "Invalid Username & Password Combination", Toast.LENGTH_LONG).show()
            }
        })
    }
}
