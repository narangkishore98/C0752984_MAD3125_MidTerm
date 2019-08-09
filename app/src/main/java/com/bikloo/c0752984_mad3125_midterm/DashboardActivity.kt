package com.bikloo.c0752984_mad3125_midterm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.beust.klaxon.*
import com.bikloo.c0752984_mad3125_midterm.adapters.DashboardAdapter
import com.bikloo.c0752984_mad3125_midterm.core.*
import com.bikloo.c0752984_mad3125_midterm.core.datastore.DataStore
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

import kotlin.text.StringBuilder

class DashboardActivity : AppCompatActivity() {

    private lateinit var linearLayotManager:LinearLayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        linearLayotManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayotManager
        recyclerView.adapter = DashboardAdapter(DataStore.flights.toTypedArray())


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == R.id.logout)
        {
            val i = Intent(this@DashboardActivity, LoginActivity::class.java)

            startActivity(i)
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_dashboard, menu)
        return super.onCreateOptionsMenu(menu)
    }


}
