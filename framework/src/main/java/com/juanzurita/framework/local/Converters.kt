package com.juanzurita.framework.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.juanzurita.domain.ad_list.models.ImageWrapper
import com.juanzurita.domain.ad_list.models.Multimedia
import com.juanzurita.domain.ad_list.models.Price
import com.juanzurita.domain.ad_list.models.PriceInfo
import java.lang.reflect.Type

class Converters {

    private val gson = Gson()

    // Converter for PriceInfo
    @TypeConverter
    fun fromPriceInfo(value: PriceInfo?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPriceInfo(value: String?): PriceInfo? {
        val type: Type = object : TypeToken<PriceInfo?>() {}.type
        return gson.fromJson(value, type)
    }

    // Converter for Price
    @TypeConverter
    fun fromPrice(value: Price?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toPrice(value: String?): Price? {
        val type: Type = object : TypeToken<Price?>() {}.type
        return gson.fromJson(value, type)
    }

    // Converter for Multimedia
    @TypeConverter
    fun fromMultimedia(value: Multimedia?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toMultimedia(value: String?): Multimedia? {
        val type: Type = object : TypeToken<Multimedia?>() {}.type
        return gson.fromJson(value, type)
    }

    // Converter for List<ImageWrapper>
    @TypeConverter
    fun fromImageWrapperList(value: List<ImageWrapper>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toImageWrapperList(value: String?): List<ImageWrapper>? {
        val type: Type = object : TypeToken<List<ImageWrapper>?>() {}.type
        return gson.fromJson(value, type)
    }

    // Converter for Map<String, Boolean>
    @TypeConverter
    fun fromFeatures(value: Map<String, Boolean>?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toFeatures(value: String?): Map<String, Boolean>? {
        val type: Type = object : TypeToken<Map<String, Boolean>?>() {}.type
        return gson.fromJson(value, type)
    }
}
