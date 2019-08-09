package com.bikloo.c0752984_mad3125_midterm.core

import java.time.LocalDate
import java.util.*

class Flight {
    var flightNo:Int
    var missionName:String

    var launchYear:Short
    var launchDate:LocalDate
    var rocket:Rocket
    var site:Site
    var isLaunchSucceed:Boolean
    var launchFailDetails:Failure?
    var smallImageLink:String
    var imgLink:String
    var readMoreLink:String
    var details:String

    constructor(
        flightNo: Int,
        missionName: String,

        launchYear: Short,
        launchDate: LocalDate,
        rocket: Rocket,
        site: Site,
        isLaunchSucceed: Boolean,
        launchFailDetails: Failure?,
        smallImageLink:String,
        imgLink: String,
        readMoreLink: String,
        details: String
    ) {
        this.flightNo = flightNo
        this.missionName = missionName

        this.launchYear = launchYear
        this.launchDate = launchDate
        this.rocket = rocket
        this.site = site
        this.isLaunchSucceed = isLaunchSucceed
        this.launchFailDetails = launchFailDetails
        this.smallImageLink = smallImageLink
        this.imgLink = imgLink
        this.readMoreLink = readMoreLink
        this.details = details
    }
}