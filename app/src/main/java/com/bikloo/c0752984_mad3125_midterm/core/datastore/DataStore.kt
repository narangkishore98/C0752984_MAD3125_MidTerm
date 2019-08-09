package com.bikloo.c0752984_mad3125_midterm.core.datastore

import com.bikloo.c0752984_mad3125_midterm.core.Flight

class DataStore
{
    companion object
    {
        var flights = mutableListOf<Flight>()

        fun addFlight(flight: Flight):Boolean
        {
            flights.add(flight)
            return true
        }

        var DATABASE_NAME = "kishore"
        var ID_COLUMN = "userid"
        var PASSWORD_COLUMN = "password"
        var TABLE_NAME = "users"
    }
}