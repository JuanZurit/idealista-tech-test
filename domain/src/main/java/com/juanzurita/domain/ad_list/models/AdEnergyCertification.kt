package com.juanzurita.domain.ad_list.models

data class AdEnergyCertification(
    val title:String?=null,
    val energyConsumption:AdEnergyCertificationType?=null,
    val emissions:AdEnergyCertificationType?=null
)