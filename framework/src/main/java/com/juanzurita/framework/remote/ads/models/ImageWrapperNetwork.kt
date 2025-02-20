package com.juanzurita.framework.remote.ads.models

import com.juanzurita.domain.ad_list.models.ImageWrapper
import com.juanzurita.framework.remote.NetworkDTO

data class ImageWrapperNetwork(
    val url: String? = null,
    val tag: String? = null,
) : NetworkDTO<ImageWrapper>() {
    override fun convert() = ImageWrapper(
        url = url.orEmpty(),
        tag = tag.orEmpty()
    )
}
