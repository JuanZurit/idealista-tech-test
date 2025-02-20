package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.Multimedia
import com.juanzurita.framework.remote.NetworkDTO
import com.juanzurita.framework.remote.convert

data class MultimediaNetwork(
    val images: List<ImageWrapperNetwork>,
) : NetworkDTO<Multimedia>() {

    override fun convert() = Multimedia(
        images = images.convert()
    )

}