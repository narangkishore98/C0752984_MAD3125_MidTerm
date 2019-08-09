package com.bikloo.c0752984_mad3125_midterm.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.bikloo.c0752984_mad3125_midterm.DetailActivity
import com.bikloo.c0752984_mad3125_midterm.R
import com.bikloo.c0752984_mad3125_midterm.core.Flight
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_dashboard.view.*
import org.w3c.dom.Text

class DashboardAdapter(val flights : Array<Flight>) : RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): DashboardAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_spacex, p0, false)
        view.setOnClickListener(View.OnClickListener {
            val itemPosition = p0.recyclerView.getChildLayoutPosition(view)
            val i = Intent(context, DetailActivity::class.java)
            i.putExtra("itemPosition", itemPosition)
            context.startActivity(i)
            Toast.makeText(context , "$itemPosition", Toast.LENGTH_SHORT).show()
        })
        context = p0.context
        return ViewHolder(view)
    }

    override fun getItemCount() = flights.size
    override fun onBindViewHolder(p0: DashboardAdapter.ViewHolder, p1: Int) {

        p0.vFlightNumber.text = "Flight Number: ${flights.get(p1).flightNo}"
        p0.vMissionName.text = "${flights.get(p1).missionName}"
        p0.vPayloadCount.text = "Number of Payloads: ${flights.get(p1).rocket.payloads.size}"
        if(!flights.get(p1).isLaunchSucceed)
        {
            p0.cell.setBackgroundColor(context.resources.getColor(R.color.light_red))


        }
        else
        {
            p0.cell.setBackgroundColor(context.resources.getColor(R.color.white))

        }
        Glide.with(context).load(flights.get(p1).smallImageLink).into(p0.vImageSmall)

    }
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        val vImageSmall = view.findViewById<ImageView>(R.id.imageSmall)
        val vMissionName = view.findViewById<TextView>(R.id.missionName)
        val vFlightNumber = view.findViewById<TextView>(R.id.flightNumber)
        val vPayloadCount = view.findViewById<TextView>(R.id.payloadCount)
        val cell = view.findViewById<LinearLayout>(R.id.cell)
    }


}