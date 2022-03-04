package com.alperen.rickandmortykotlinfragment.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alperen.rickandmortykotlinfragment.Model.Result
import com.alperen.rickandmortykotlinfragment.Model.rickModel
import com.alperen.rickandmortykotlinfragment.R
import com.alperen.rickandmortykotlinfragment.View.FirstFragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(private val rickList:rickModel, private val listener: FirstFragment):RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>(){
    interface Listener{
        fun onItemClick(rickList: Result)
    }

    class RowHolder (view: View):RecyclerView.ViewHolder(view) {

        fun bind(rickList: Result, position: Int, listener: FirstFragment){
            itemView.setOnClickListener {
                listener.onItemClick(rickList,it)
            }
            println("ricklist: ${rickList}")
            Picasso.get().load(rickList.image)
                .resize(120,120)
                .centerCrop()
                .into(itemView.imageView)

            itemView.textView2.text=rickList.name
            itemView.textView3.text=rickList.status
            itemView.textView4.text= rickList.species

            println("name: ${rickList.name}")
            println("status ${rickList.status}")
            println("species: ${rickList.species}")

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
       val view=LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return RowHolder(view)
    }
    override fun getItemCount(): Int {
        return rickList.results.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(rickList.results[position],position,listener)
    }


}