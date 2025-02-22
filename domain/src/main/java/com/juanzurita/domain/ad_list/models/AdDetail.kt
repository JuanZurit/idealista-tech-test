package com.juanzurita.domain.ad_list.models

data class AdDetail(
    val id:Int?=null,
    val price:Float?=null,
    val priceInfo: Price?=null,
    val operation:String?=null,
    val propertyType:String?=null,
    val extendedPropertyType:String?=null,
    val homeType:String?=null,
    val state:String?=null,
    val multimedia: Multimedia?=null,
    val propertyComment:String?=null,
    val ubication:Map<String?,String?>?=null,
    val country:String?=null,
    val moreCharacteristics:AdMoreCharacteristics?=null,
    val energyCertification:AdEnergyCertification?=null
)
