package com.juanzurita.presentation.ads.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.juanzurita.core.adapter.DelegateAdapter
import com.juanzurita.core.adapter.DelegateAdapterItem
import com.juanzurita.core.adapter.base.BaseViewHolder
import com.juanzurita.core.util.extensions.capitalizeFirstLetter
import com.juanzurita.core.util.extensions.formatWithSeparators
import com.juanzurita.core.util.extensions.gone
import com.juanzurita.core.util.extensions.load
import com.juanzurita.core.util.extensions.orValue
import com.juanzurita.core.util.extensions.visible
import com.juanzurita.presentation.R
import com.juanzurita.presentation.ads.adapter.models.AdItemPLO
import com.juanzurita.presentation.databinding.AdListItemBinding

class AdItemAdapter(val onAdClick: (String?) -> Unit,val onFavoriteClicked:(String?,Boolean)->Unit) :
    DelegateAdapter<AdItemPLO, AdItemAdapter.AdItemViewHolder>(AdItemPLO::class.java) {
    override fun createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        AdItemViewHolder(parent)

    override fun bindViewHolder(
        model: AdItemPLO,
        viewHolder: AdItemViewHolder,
        payloads: List<DelegateAdapterItem.Payloadable>,
    ) = viewHolder.bind(model)

    inner class AdItemViewHolder(parent: ViewGroup) : BaseViewHolder<AdItemPLO, AdListItemBinding>(
        parent, R.layout.ad_list_item, AdListItemBinding::bind
    ) {
        override fun bind(item: AdItemPLO) {
            drawItem(item)
        }

        private fun drawItem(item: AdItemPLO) {
            binding.btnFav.isSelected = item.isFavorite
            binding.btnFav.setOnClickListener {
                onFavoriteClicked(item.propertyCode,!item.isFavorite)
            }
            loadImages(item)
            binding.tvType.text = item.propertyType.orEmpty().uppercase()
            drawPrice(item)
            binding.tvAddress.text = formatLocation(
                item.address?.capitalizeFirstLetter(),
                item.municipality?.capitalizeFirstLetter(),
                item.district?.capitalizeFirstLetter()
            )
            binding.root.setOnClickListener {
                onAdClick(item.propertyCode)
            }
            drawRooms(item)
        }


        private fun loadImages(item: AdItemPLO) {
            binding.ivPrincipal.load(item.principalImage)
            binding.ivSecondary1.load(item.secondaryImage1)
            binding.ivSecondary2.load(item.secondaryImage2)
            binding.ivSecondary3.load(item.secondaryImage3)
        }

        private fun drawPrice(item: AdItemPLO) {
            item.priceInfo?.price.apply {
                binding.tvPrice.text = binding.root.context.getString(
                    R.string.price_format,
                    item.priceInfo?.price?.amount?.formatWithSeparators().orValue("-"),
                    item.priceInfo?.price?.currency.orEmpty()
                )
            }
        }

        private fun formatLocation(address: String?, city: String?, district: String?): String =
            listOfNotNull(address, city, district)
                .joinToString(" · ")


        private fun drawRooms(item: AdItemPLO) {
            item.bathrooms?.let {
                binding.tvBathrooms.text = "$it"
                binding.gBathroom.visible()
            } ?: run {
                binding.gBathroom.gone()
            }

            item.rooms?.let {
                binding.tvBedrooms.text = "$it"
                binding.gBedroom.visible()
            } ?: run {
                binding.gBedroom.gone()
            }
        }


    }
}