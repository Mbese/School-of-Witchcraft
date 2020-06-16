package com.example.hogwarts.houses.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.widgets.ExpandableView

class HousesAdapter(
    private val houses: ArrayList<House>
) : RecyclerView.Adapter<HousesAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = getLayoutInflater(parent.context).inflate(R.layout.list_row, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return houses.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val house = houses[position]

        holder.expandingView.setTitleText(house.name)
        holder.mascotTextView.text = house.mascot
        holder.headOfHouseTextView.text = house.headOfHouse
        holder.houseGhostTextView.text = house.houseGhost
        holder.founderTextView.text = house.founder
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expandingView: ExpandableView = itemView.findViewById(R.id.expandable_view)
        var mascotTextView: TextView = itemView.findViewById(R.id.mascot)
        var headOfHouseTextView: TextView = itemView.findViewById(R.id.head_of_house)
        var houseGhostTextView: TextView = itemView.findViewById(R.id.house_ghost)
        var founderTextView: TextView = itemView.findViewById(R.id.house_ghost)
    }
}