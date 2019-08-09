package com.bikloo.c0752984_mad3125_midterm

import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.*
import com.bikloo.c0752984_mad3125_midterm.core.datastore.DataStore
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import java.time.format.DateTimeFormatter
import java.util.*

class DetailActivity : AppCompatActivity() {


     companion object
     {
         var DEFAULT:Int = 0
     }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        DEFAULT = intent.getIntExtra("itemPosition",0)
        val itemPosition = DEFAULT
        var ifIsSucceed = ""
        if(DataStore.flights[itemPosition].isLaunchSucceed)
        {
            ifIsSucceed = "Yes"
        }
        else
        {
            ifIsSucceed = "No (Click For Failure Details)"
        }
        val date =   DataStore.flights[itemPosition].launchDate
        val newFormat = DateTimeFormatter.ofPattern("E, dd-MMM-yyyy", Locale.ENGLISH)

        val array = arrayOf("Flight Number: ${DataStore.flights[itemPosition].flightNo}",
            "Launch Year: ${DataStore.flights[itemPosition].launchYear}",
            "Rocket Used: ${DataStore.flights[itemPosition].rocket.rocketName}",
            "Succeed: $ifIsSucceed",
            "Detail: ${DataStore.flights[itemPosition].details}",
            "Read More at : ${DataStore.flights[itemPosition].readMoreLink}",
            "Site: ${DataStore.flights[itemPosition].site.siteCompleteName}",
            "Launch Date: ${date.format(newFormat)}"
            )

        Glide.with(this).load(DataStore.flights[itemPosition].imgLink).into(detailImageView)
        detailMissionName.text = DataStore.flights[itemPosition].missionName + " (${DataStore.flights[itemPosition].launchYear}) "
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array)
        detailListView.adapter = adapter
        detailListView.setOnItemClickListener { parent, view, position, l ->
            when(position)
            {
                5 ->
                {
                    var i = Intent(this@DetailActivity,WebActivity::class.java)
                    i.putExtra("weblink",array[5])
                    i.putExtra("itemPosition", intent.getIntExtra("itemPosition",0))
                    startActivity(i)
                }
                2->
                {
                    val dialogBuilder = AlertDialog.Builder(this@DetailActivity)
                    var string = "Rocket ID: ${DataStore.flights[itemPosition].rocket.rocketID}\nRocket Name: ${DataStore.flights[itemPosition].rocket.rocketName}\nRocket Type: ${DataStore.flights[itemPosition].rocket.rocketType}"
                    if(DataStore.flights[itemPosition].rocket.payloads.size==0)
                    {
                        string += "\n\nThis Rocket has No Payloads."
                    }
                    else
                    {
                        string += "\n\nThis Rocket Has Following Payloads"
                        for(payload in DataStore.flights[itemPosition].rocket.payloads )
                        {
                            string += "\nPayload ID: ${payload.payloadID}\nManufacturer: ${payload.manufacturer}\nNationality: ${payload.nationality}\nMass: ${payload.mass}\nOrbit: ${payload.orbit}\n\n"
                        }
                    }
                    dialogBuilder.setMessage(string).setCancelable(false)
                        .setPositiveButton("OK", DialogInterface.OnClickListener{dialogInterface, i -> dialogInterface.cancel() })
                    val alert = dialogBuilder.create()
                    alert.setTitle("Rocket Details For ${DataStore.flights[itemPosition].missionName}")
                    alert.show()
                }
                3->
                {
                    if(!DataStore.flights[itemPosition].isLaunchSucceed)
                    {
                        val dialogBuilder = AlertDialog.Builder(this@DetailActivity)
                        var string = "Time: ${DataStore.flights[itemPosition].launchFailDetails!!.timeSeconds}\nAltitude: ${DataStore.flights[itemPosition].launchFailDetails!!.altitude}\nReason: ${DataStore.flights[itemPosition].launchFailDetails!!.reason}"
                        dialogBuilder.setMessage(string).setCancelable(false)
                            .setPositiveButton("OK", DialogInterface.OnClickListener{dialogInterface, i -> dialogInterface.cancel() })
                        val alert = dialogBuilder.create()
                        alert.setTitle("Flight Failure Details For ${DataStore.flights[itemPosition].missionName}")
                        alert.show()
                    }

                }
                else ->
                {
                    Toast.makeText(this@DetailActivity, "Nothing", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
