package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.AdEnergyCertificationType
import com.juanzurita.framework.remote.NetworkDTO

data class AdEnergyCertificationTypeNetwork(
    val type:String?=null
): NetworkDTO<AdEnergyCertificationType>() {
    override fun convert(): AdEnergyCertificationType =AdEnergyCertificationType(type = type)
}
