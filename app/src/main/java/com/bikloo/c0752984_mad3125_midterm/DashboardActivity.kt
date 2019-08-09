package com.bikloo.c0752984_mad3125_midterm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.beust.klaxon.Klaxon
import com.beust.klaxon.Parser
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
            //val parser = Parser.default()
            //val jsonArray = parser.parse(jsonString)
            //println(jsonObject)
            //val jsonObject = JSONObject(buffer.readText())


            //println(jsonArray)
            var obj = JSONObject(buffer.readText())
            println(obj)

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
