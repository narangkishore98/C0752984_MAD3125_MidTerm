package com.bikloo.c0752984_mad3125_midterm.core

class Rocket
{
    var rocketID:String
    var rocketName:String
    var rocketType:String
    var nationality:String
    var manufacturer:String
    var orbit:String

    constructor(
        rocketID: String,
        rocketName: String,
        rocketType: String,
        nationality: String,
        manufacturer: String,
        orbit: String
    ) {
        this.rocketID = rocketID
        this.rocketName = rocketName
        this.rocketType = rocketType
        this.nationality = nationality
        this.manufacturer = manufacturer
        this.orbit = orbit
    }
}
