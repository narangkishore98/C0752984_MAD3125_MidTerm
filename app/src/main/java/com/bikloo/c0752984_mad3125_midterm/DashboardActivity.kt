package com.bikloo.c0752984_mad3125_midterm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
import com.bikloo.c0752984_mad3125_midterm.core.Failure
import com.bikloo.c0752984_mad3125_midterm.core.Flight
import com.bikloo.c0752984_mad3125_midterm.core.Rocket
import com.bikloo.c0752984_mad3125_midterm.core.Site
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.File
import java.io.InputStream
import java.io.InputStreamReader

import kotlin.text.StringBuilder

class DashboardActivity : AppCompatActivity() {

    companion object ReadJson
    {


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        readFromJson()

    }

    fun readFromJson()
    {
        val FILE_NAME = "launches.json"
        val result:InputStream?  = assets.open(FILE_NAME)
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
                val siteObject:Site = Site(dummySite?.string("site_id")!!, dummySite?.string("site_name")!!, dummySite?.string("site_name_long")!!)
                //println(siteObject.siteCompleteName)
                val dummyRocket = jsonObject.obj("rocket")
                val rocketObject:Rocket = Rocket(dummyRocket?.string("rocket_id")!!,
                    dummyRocket?.string("rocket_name")!!,
                    dummyRocket?.string("rocket_type")!!,
                    dummyRocket?.obj("second_stage")?.string("nationality")!!,
                    dummyRocket?.obj("second_stage")?.string("manufacturer")!!,
                    dummyRocket?.obj("second_stage")?.string("orbit")!! )

                if(!jsonObject?.boolean("launch_success")!!)
                {
                    val dummyFailure = jsonObject.obj("launch_failure_details")
                    val failureObject:Failure = Failure(dummyFailure?.int("time")!!,
                        dummyFailure?.string("reason")!!,
                        dummyFailure?.int("altitude"))

                    //val dummyMissionID = jsonObject.array<Any>("mission_id")



                    val flight = Flight(jsonObject?.int("flight_no")!!,
                        )
                }

            }


            //val jsonObject = JSONObject(buffer.readText())




        }
    }


    fun readJSONFromAsset(): String? {
        var json: String? = null
        try {
            val  inputStream:InputStream = assets.open("yourFile.json")
            json = inputStream.bufferedReader().use{it.readText()}
        } catch (ex: Exception) {
            ex.printStackTrace()
            return null
        }
        return json
    }


}
