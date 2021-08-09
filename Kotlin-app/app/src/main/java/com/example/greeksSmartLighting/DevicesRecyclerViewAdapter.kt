package com.webianks.bluechat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.blue.R
import com.example.bluetoothchat.MainActivity

class DevicesRecyclerViewAdapter(val mDeviceList: List<DeviceData>, val context: Context) :
        RecyclerView.Adapter<DevicesRecyclerViewAdapter.VH>() {


    private var listener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(context).inflate(R.layout.recyclerview_single_item, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder?.label?.text = mDeviceList[position].deviceName ?: mDeviceList[position].deviceHardwareAddress
    }

    override fun getItemCount(): Int {
        return mDeviceList.size
    }

    inner class VH(itemView: View?) : RecyclerView.ViewHolder(itemView!!){

        var label: TextView? = itemView?.findViewById(R.id.largeLabel)

        init {
            itemView?.setOnClickListener{
                listener?.itemClicked(mDeviceList[adapterPosition])
            }

            itemView?.setOnLongClickListener{
                listener?.itemLongClicked(mDeviceList[adapterPosition])
                return@setOnLongClickListener true
            }

        }
    }

    fun setItemClickListener(listener: MainActivity){
        this.listener = listener
    }

    interface ItemClickListener{
        fun itemClicked(deviceData: DeviceData)
        fun itemLongClicked(deviceData: DeviceData)
    }
}