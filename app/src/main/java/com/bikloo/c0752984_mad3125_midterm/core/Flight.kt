package com.bikloo.c0752984_mad3125_midterm.core

import java.util.*

class Flight {
    var flightNo:Int
    var missionName:String
    var missionID:Array<Int>
    var launchYear:Short
    var launchDate:Date
    var rocket:Rocket
    var site:Site
    var isLaunchSucceed:Boolean
    var launchFailDetails:Failure
    var imgLink:String
    var readMoreLink:String
    var details:String

    constructor(
        flightNo: Int,
        missionName: String,
        missionID: Array<Int>,
        launchYear: Short,
        launchDate: Date,
        rocket: Rocket,
        site: Site,
        isLaunchSucceed: Boolean,
        launchFailDetails: Failure,
        imgLink: String,
        readMoreLink: String,
        details: String
    ) {
        this.flightNo = flightNo
        this.missionName = missionName
        this.missionID = missionID
        this.launchYear = launchYear
        this.launchDate = launchDate
        this.rocket = rocket
        this.site = site
        this.isLaunchSucceed = isLaunchSucceed
        this.launchFailDetails = launchFailDetails
        this.imgLink = imgLink
        this.readMoreLink = readMoreLink
        this.details = details
    }
}