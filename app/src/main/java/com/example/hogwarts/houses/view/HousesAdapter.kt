package com.example.hogwarts.houses.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.widgets.ExpandableView

class HousesAdapter(
    private val context: Context,
    private val houses: ArrayList<House>,
    private val itemClickListener: ItemClickListener
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
        holder.expandingView.setSubTitleText("Founder : " + house.founder)
        holder.expandingView.setLeftIcon(R.drawable.ic_baseline_house_24)
        holder.mascotTextView.text = context.getString(R.string.mascot_prefix, house.mascot)
        holder.headOfHouseTextView.text = context.getString(R.string.head_of_house_prefix, house.headOfHouse)
        holder.houseGhostTextView.text = context.getString(R.string.house_ghost_prefix, house.houseGhost)

        holder.button.setOnClickListener { itemClickListener.onItemClicked(house.members) }
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    interface ItemClickListener {
        fun onItemClicked(characterIds: ArrayList<String>)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var expandingView: ExpandableView = itemView.findViewById(R.id.expandable_view)
        var mascotTextView: TextView = itemView.findViewById(R.id.mascot)
        var headOfHouseTextView: TextView = itemView.findViewById(R.id.head_of_house)
        var houseGhostTextView: TextView = itemView.findViewById(R.id.house_ghost)
        var button: Button = itemView.findViewById(R.id.btn)
    }
}