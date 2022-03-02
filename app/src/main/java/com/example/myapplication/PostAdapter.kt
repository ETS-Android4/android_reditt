package com.example.myapplication

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class PostAdapter (private val listadem : ArrayList<Post>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {
    private  lateinit var listnerAdem : OnItemClickListner

    interface OnItemClickListner {
        fun OnClickListener ( position :Int)
    }
    fun SetOnclickListner (lst :OnItemClickListner) {
        listnerAdem = lst
    }
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PostAdapter.MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_post,parent ,false)
        return  MyViewHolder(itemView,listnerAdem)

    }

    override fun onBindViewHolder(holder: PostAdapter.MyViewHolder, position: Int) {
        val currnetItem = listadem[position]

        holder.title.text=currnetItem.title
        holder.author.text=currnetItem.author
        holder.date.text=currnetItem.date
    }

    override fun getItemCount(): Int {
        return listadem.size
    }

    class MyViewHolder (itemView : View , listner :OnItemClickListner) : RecyclerView.ViewHolder(itemView) {


        val title: TextView= itemView.findViewById(R.id.titlePub)
        val author : TextView = itemView.findViewById((R.id.author))
        val date : TextView = itemView.findViewById((R.id.date))
        init {
            itemView.setOnClickListener{
                listner.OnClickListener(adapterPosition)
            }

        }

    }
}

