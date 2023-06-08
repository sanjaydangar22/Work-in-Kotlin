package com.mytrip.myindiatrip.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mytrip.myindiatrip.R
import com.mytrip.myindiatrip.model.ModelClass

class TripAdapter(var context: Context, var placeList: ArrayList<ModelClass>, var click:(ModelClass)-> Unit) : RecyclerView.Adapter<TripAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPlace: ImageView = itemView.findViewById(R.id.imgPlace)
        var txtPlaceName: TextView = itemView.findViewById(R.id.txtPlaceName)
        var txtLocation: TextView = itemView.findViewById(R.id.txtLocation)
        var txtPlaceRating: TextView = itemView.findViewById(R.id.txtPlaceRating)
        var cdSearchView: CardView = itemView.findViewById(R.id.cdSearchView)
        var imgSave: ImageView = itemView.findViewById(R.id.imgSave)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.search_item_list, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        return  placeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(placeList[position].image)
            .placeholder(R.drawable.ic_image).into(holder.imgPlace)

        holder.txtPlaceName.text=placeList[position].name
        holder.txtLocation.text=placeList[position].location
        holder.txtPlaceRating.text=placeList[position].rating

        holder.cdSearchView.setOnClickListener {
            click.invoke(placeList[position])
        }




    }
}