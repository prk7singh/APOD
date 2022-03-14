package com.example.apod.ui.favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.apod.R
import com.example.apod.data.db.Media

class FavListAdapter(private val favList:List<Media>): RecyclerView.Adapter<FavListAdapter.FavViewHolder>() {

    class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleView:TextView = itemView.findViewById(R.id.fav_title)
        var dateView:TextView = itemView.findViewById(R.id.fav_date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        var layout = LayoutInflater.from(parent.context).inflate(R.layout.fav_view,parent,false)
        return FavViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.titleView.text = favList[position].title
        holder.dateView.text = favList[position].date
    }

    override fun getItemCount(): Int {
        return favList.size
    }

}