package com.example.chatapp.ui.home

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.chatapp.R
import com.example.chatapp.database.models.Room
import com.example.chatapp.databinding.ActivityAddRoomBinding
import com.example.chatapp.databinding.ItemRoomBinding

class RoomAdapter(var items:List<Room>? = null): RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {


    class RoomViewHolder(var binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {

        val viewBinding = DataBindingUtil.inflate<ItemRoomBinding>(LayoutInflater.from(parent.context) ,
            R.layout.item_room ,
            parent,
            false)
        return RoomViewHolder(viewBinding)

    }

    override fun getItemCount(): Int  = items?.size?:0

    interface OnItemClickListener{
        fun onItemClick(postion:Int , item:Room)
    }

    var onItemClickListener:OnItemClickListener? = null

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {

        val item = items!![position]


        with(holder){
            with(binding){
                roomImg.setImageBitmap(stringToBitmap(item.image))
                roomTitle.text = item.title
                roomDesc.text = item.description

            }
        }
        onItemClickListener?.let {clicklistener ->
            holder.binding.root.setOnClickListener {
                clicklistener.onItemClick(position , item)
            }
        }



    }
    fun stringToBitmap(imageString: String?): Bitmap? {
        val decodedString = Base64.decode(imageString, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    }

    fun bindList(newList:List<Room>?) {
        items = newList
        notifyDataSetChanged()
    }

}