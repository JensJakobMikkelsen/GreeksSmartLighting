package com.webianks.bluechat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomMasterTable.TABLE_NAME
import androidx.room.TypeConverters
import com.example.blue.Database.DataConverter

@Entity(tableName = TABLE_NAME)
data class DeviceData(val _deviceName: String?,val _deviceHardwareAddress: String){

    @ColumnInfo
    val deviceName = _deviceName

    @PrimaryKey
    val deviceHardwareAddress = _deviceHardwareAddress

    val messages: MutableList<Message>? = null

    @TypeConverters(DataConverter::class)
    fun getMessageList(): List<Message?>? {
        return messages
    }

    override fun equals(other: Any?): Boolean {
        val deviceData = other as DeviceData
        return deviceHardwareAddress == deviceData.deviceHardwareAddress
    }

    override fun hashCode(): Int {
        return deviceHardwareAddress.hashCode()
    }

}