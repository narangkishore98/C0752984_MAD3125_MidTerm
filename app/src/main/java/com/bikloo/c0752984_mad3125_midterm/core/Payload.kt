package com.bikloo.c0752984_mad3125_midterm.core

class Payload
{
    var payloadID:String
    var nationality:String
    var manufacturer:String
    var mass:Int

    constructor(payloadID: String, nationality: String, manufacturer: String, mass: Int, orbit: String) {
        this.payloadID = payloadID
        this.nationality = nationality
        this.manufacturer = manufacturer
        this.mass = mass
        this.orbit = orbit
    }

    var orbit:String
}