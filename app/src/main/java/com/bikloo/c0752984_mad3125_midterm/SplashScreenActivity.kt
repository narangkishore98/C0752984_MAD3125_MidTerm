package com.bikloo.c0752984_mad3125_midterm

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.bikloo.c0752984_mad3125_midterm.core.*
import com.bikloo.c0752984_mad3125_midterm.core.datastore.DataStore
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class SplashScreenActivity : AppCompatActivity() {


    private var delayHandler: Handler? = null
    private val SPLASH_DELAY:Long = 5000 // five seconds or five thousand milliseconds


    fun readFromJson()
    {
        val FILE_NAME = "launches.json"
        val result: InputStream?  = assets.open(FILE_NAME)
        if(result!=null)
        {
            val inputStreamReader = InputStreamReader(result)
            val buffer = BufferedReader(inputStreamReader)
            // println(buffer.readText())
            //var json = Klaxon().parse(buffer.readText())
            val jsonString = StringBuilder(buffer.readText())
            val parser = Parser.default()
            val jsonArray = parser.parse(jsonString) as JsonArray<JsonObject>
            println(jsonArray)

            for(jsonObject in jsonArray)
            {
                //println("THE RESULT FROM OBJECT IS "+jsonObject.int("flight_number"))
                val dummySite = jsonObject.obj("launch_site")
                //println(dummySite)
                val siteObject: Site = Site(dummySite?.string("site_id")!!, dummySite?.string("site_name")!!, dummySite?.string("site_name_long")!!)
                //println(siteObject.siteCompleteName)
                val dummyRocket = jsonObject.obj("rocket")

                var payloads = mutableListOf<Payload>()
                if(dummyRocket?.obj("second_stage")?.array<JsonObject>("payloads") == null)
                {
                    println("No Payload")
                }
                else
                {
                    println("We hve payloads on this")
                    for(payload in dummyRocket?.obj("second_stage")?.array<JsonObject>("payloads")!!) {
                        println(payload)
                        if (payload.string("payload_id") != null && payload.string("nationality") != null && payload.string(
                                "manufacturer"
                            ) != null && payload.int("payload_mass_kg") != null && payload.string("orbit") != null
                        ) {
                            val payloadObject = Payload(
                                payload.string("payload_id")!!,
                                payload.string("nationality")!!,
                                payload.string("manufacturer")!!,
                                payload.int("payload_mass_kg")!!,
                                payload.string("orbit")!!
                            )
                            payloads.add(payloadObject)
                        }
                    }
                }

                val rocketObject: Rocket = Rocket(dummyRocket?.string("rocket_id")!!,
                    dummyRocket?.string("rocket_name")!!,
                    dummyRocket?.string("rocket_type")!!,
                    payloads)

                if(jsonObject?.boolean("launch_success") != null) {
                    if (!jsonObject?.boolean("launch_success")!!) {
                        val dummyFailure = jsonObject.obj("launch_failure_details")
                        val failureObject: Failure = Failure(
                            dummyFailure?.int("time")!!,
                            dummyFailure?.string("reason")!!,
                            dummyFailure?.int("altitude")
                        )

                        //val dummyMissionID = jsonObject.array<Any>("mission_id")


                        val oldFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                        val newFormat = DateTimeFormatter.ofPattern("E, dd-MMM-yyyy", Locale.ENGLISH)

                        val date = LocalDate.parse(jsonObject?.string("launch_date_utc")!!, oldFormat)


                        val flight = Flight(
                            jsonObject?.int("flight_number")!!,
                            jsonObject?.string("mission_name")!!,
                            jsonObject?.string("launch_year")!!.toShort(),
                            date,
                            rocketObject,
                            siteObject,
                            jsonObject?.boolean("launch_success")!!,
                            failureObject,
                            jsonObject?.obj("links")?.string("mission_patch_small")!!,
                            jsonObject?.obj("links")?.string("mission_patch")!!,
                            jsonObject?.obj("links")?.string("article_link")!!,
                            jsonObject?.string("details")!!
                        )
                        DataStore.addFlight(flight)
                    } else {
                        val oldFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
                        val newFormat = DateTimeFormatter.ofPattern("E, dd-MMM-yyyy", Locale.ENGLISH)

                        val date = LocalDate.parse(jsonObject?.string("launch_date_utc")!!, oldFormat)


                        val flight = Flight(
                            jsonObject?.int("flight_number")!!,
                            jsonObject?.string("mission_name")!!,
                            jsonObject?.string("launch_year")!!.toShort(),
                            date,
                            rocketObject,
                            siteObject,
                            jsonObject?.boolean("launch_success")!!,
                            null,
                            jsonObject?.obj("links")?.string("mission_patch_small")!!,
                            jsonObject?.obj("links")?.string("mission_patch")!!,
                            jsonObject?.obj("links")?.string("article_link")!!,
                            "ABC"
                        )
                        DataStore.addFlight(flight)
                    }
                }

            }

            println(DataStore.flights.size)

            //val jsonObject = JSONObject(buffer.readText())




        }
    }




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

        readFromJson()

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
