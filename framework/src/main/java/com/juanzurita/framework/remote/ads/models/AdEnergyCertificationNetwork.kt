package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.AdEnergyCertification
import com.juanzurita.framework.remote.NetworkDTO

data class AdEnergyCertificationNetwork(
    val title:String?=null,
    val energyConsumption:AdEnergyCertificationTypeNetwork?=null,
    val emissions:AdEnergyCertificationTypeNetwork?=null
):NetworkDTO<AdEnergyCertification>() {
    override fun convert(): AdEnergyCertification = AdEnergyCertification(
        title = title,
        energyConsumption = energyConsumption?.convert(),
        emissions = emissions?.convert()
    )
}