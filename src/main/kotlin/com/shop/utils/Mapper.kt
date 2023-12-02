package com.shop.utils

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper

object Mapper {

    private val modelMapper = ModelMapper()
    private val mapper = ObjectMapper()

    /**
     *
     * @param entity entity class
     * @param dtoClass dto Class
     * @return dtoClass
     */
    fun <D, T> mapper(entity: T, dtoClass: Class<D>?): D {
        return modelMapper.map(entity, dtoClass)
    }

    /**
     *
     * @param json Json String
     * @param tClass Class
     * @param <T> Class
     * @return T class
    </T> */
    fun <T> fromJson(json: String?, tClass: Class<T>?): T? {
        try {
            return mapper.readValue(json, tClass)
        } catch (e: JsonProcessingException) {
            e.printStackTrace()
        }
        return null
    }
}