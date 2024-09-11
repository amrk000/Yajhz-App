package com.amrk000.yajhaz.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amrk000.yajhaz.R
import com.amrk000.yajhaz.model.SellerModel
import com.bumptech.glide.Glide


class TrendingSellersAdapter (private val context: Context) : RecyclerView.Adapter<TrendingSellersAdapter.CardHolder>() {

    private val data = ArrayList<SellerModel>()

    class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.item_style2_image)
        val title = view.findViewById<TextView>(R.id.item_style2_Name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item_style_2, parent, false)
        return CardHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.title.text = data[position].name

        Glide.with(context)
            .load(data[position].image)
            .centerCrop()
            .into(holder.image);
    }

    fun addData(data : ArrayList<SellerModel>) {
        this.data.addAll(data)
        notifyItemRangeInserted(itemCount, data.size)
    }

}