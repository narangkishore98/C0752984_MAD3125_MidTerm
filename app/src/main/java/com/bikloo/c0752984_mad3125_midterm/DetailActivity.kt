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
        val array = arrayOf("Flight Number: ${DataStore.flights[itemPosition].flightNo}",
            "Launch Year: ${DataStore.flights[itemPosition].launchYear}",
            "Rocket Used: ${DataStore.flights[itemPosition].rocket.rocketName}",
            "Succeed: ${DataStore.flights[itemPosition].isLaunchSucceed}",
            "Detail: ${DataStore.flights[itemPosition].details}",
            "Read More at : ${DataStore.flights[itemPosition].readMoreLink}",
            "Site: ${DataStore.flights[itemPosition].site.siteCompleteName}"
            )

        Glide.with(this).load(DataStore.flights[itemPosition].imgLink).into(detailImageView)
        detailMissionName.text = DataStore.flights[itemPosition].missionName
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
                    dialogBuilder.setMessage("This is Dummy Text").setCancelable(false)
                        .setPositiveButton("OK", DialogInterface.OnClickListener{dialogInterface, i -> finish() })
                    val alert = dialogBuilder.create()
                    alert.setTitle("Rocket Details For XXX")
                    alert.show()
                }
                else ->
                {
                    Toast.makeText(this@DetailActivity, "Nothing", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}
