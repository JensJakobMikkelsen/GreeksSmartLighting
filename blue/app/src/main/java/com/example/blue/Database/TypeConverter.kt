package com.example.blue.Database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.webianks.bluechat.Message
import java.util.*

class DataConverter {
    @TypeConverter
    fun fromMessageList(messageList: List<Message?>?): String? {
        if (messageList == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Message?>?>() {}.type
        return gson.toJson(messageList, type)
    }

    @TypeConverter
    fun toMessageList(messageListString: String?): List<Message>? {
        if (messageListString == null) {
            return null
        }
        val gson = Gson()
        val type =
            object : TypeToken<List<Message?>?>() {}.type
        return gson.fromJson<List<Message>>(messageListString, type)
    }
}