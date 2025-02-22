package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.AdMoreCharacteristics
import com.juanzurita.framework.remote.NetworkDTO

data class AdMoreCharacteristicsNetwork(
    val communityCosts:Float?=null,
    val roomNumber:Int?=null,
    val bathNumber:Int?=null,
    val exterior:Boolean?=null,
    val housingFurnitures:String?=null,
    val agencyIsABank:Boolean?=null,
    val energyCertificationType:String?=null,
    val flatLocation:String?=null,
    val modificationDate:Double?=null,
    val constructedArea:Float?=null,
    val lift:Boolean?=null,
    val boxroom:Boolean?=null,
    val isDuplex:Boolean?=null,
    val floor:Int?=null,
    val status:String?=null
):NetworkDTO<AdMoreCharacteristics>() {
    override fun convert(): AdMoreCharacteristics = AdMoreCharacteristics(
        communityCosts = communityCosts,
        roomNumber = roomNumber,
        bathNumber = bathNumber,
        exterior = exterior,
        housingFurnitures = housingFurnitures,
        agencyIsABank = agencyIsABank,
        energyCertificationType = energyCertificationType,
        flatLocation = flatLocation,
        modificationDate = modificationDate,
        constructedArea = constructedArea,
        lift = lift,
        boxroom = boxroom,
        isDuplex = isDuplex,
        floor = floor,
        status = status
    )
}
