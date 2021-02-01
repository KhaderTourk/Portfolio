package com.example.volleyapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.case_item.view.*

class CaseAdapter (val context: Context, val list: ArrayList<Case>, val click: onClick) :
    RecyclerView.Adapter<CaseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflate = LayoutInflater.from(context).inflate(R.layout.case_item, parent, false)
        return ViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.number?.text = list[position].case_number
        holder.age?.text = list[position].case_age
        holder.gender?.text = list[position].case_gender
        holder.location?.text = list[position].case_location
        holder.date?.text = list[position].case_diagnose_date
        holder.infection?.text = list[position].case_source_of_infection
        holder.condition?.text = list[position].case_condition
        holder.quarantine?.text = list[position].case_quarantine
        holder.community?.text = list[position].case_community


    }


    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val number = item.tv_number
        val age = item.tv_age
        val gender = item.tv_gender
        val location = item.tv_locatin
        val date = item.tv_diagnose_date
        val infection = item.tv_sourse_of_infection
        val condition = item.tv_condition
        val quarantine = item.tv_quarantine
        val community = item.tv_community


    }

    interface onClick {
        fun onClickItem(position: Int)
    }
}