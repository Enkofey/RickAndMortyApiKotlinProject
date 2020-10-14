package com.example.myfirstprograminkotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.myfirstprograminkotlin.R
import com.example.myfirstprograminkotlin.models.Episode

class EpisodeAdapter (var context : Context, var list : Episode) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        lateinit var layoutItem : LinearLayout
        var mInflater = LayoutInflater.from(context)

        if(convertView == null){
            layoutItem = mInflater.inflate(R.layout.episode_adapter_view,parent,false) as LinearLayout
        }
        else{
            layoutItem = convertView as LinearLayout
        }

        var nom : TextView = layoutItem.findViewById(R.id.nomContact)
        nom.setText(list.results?.get(position)?.name)

        var number : TextView = layoutItem.findViewById(R.id.numberEpisode)
        number.setText(list.results?.get(position)?.episode)

        return layoutItem
    }

    override fun getItem(position: Int): Any {
        if(list.results != null){
            return list.results!!.get(position)
        }
        else{
            return -1
        }
    }

    override fun getItemId(position: Int): Long {
        if(list.results != null){
            return position.toLong()
        }
        else{
            return -1
        }
    }

    override fun getCount(): Int {
        if(list.results != null){
            return list.results!!.size
        }
        else{
            return -1
        }
    }


}