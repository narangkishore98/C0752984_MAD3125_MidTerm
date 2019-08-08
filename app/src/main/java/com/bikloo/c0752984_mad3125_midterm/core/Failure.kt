package com.bikloo.c0752984_mad3125_midterm.core

class Failure {
    var timeSeconds:Int
    var reason:String
    var altitude:Int

    constructor(timeSeconds: Int, reason: String, altitude: Int) {
        this.timeSeconds = timeSeconds
        this.reason = reason
        this.altitude = altitude
    }
}