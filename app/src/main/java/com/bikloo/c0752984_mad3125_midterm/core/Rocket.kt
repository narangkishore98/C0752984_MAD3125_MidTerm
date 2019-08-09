package com.bikloo.c0752984_mad3125_midterm.core

class Rocket
{
    var rocketID:String
    var rocketName:String
    var rocketType:String
    var payloads:Array<Payload>

    constructor(
        rocketID: String,
        rocketName: String,
        rocketType: String,
        payloads:Array<Payload>
    ) {
        this.rocketID = rocketID
        this.rocketName = rocketName
        this.rocketType = rocketType
        this.payloads = payloads
    }
}
