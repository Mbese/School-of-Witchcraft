package com.example.hogwarts.characters.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.characters.data.Character
import com.example.hogwarts.widgets.ExpandableView

class HouseCharactersAdapter (
    private val context: Context,
    private val characters: ArrayList<Character>
) : RecyclerView.Adapter<HouseCharactersAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = getLayoutInflater(parent.context).inflate(R.layout.characters_row, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return characters.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val character = characters[position]

        holder.expandingView.setTitleText(character.name)
        holder.expandingView.setSubTitleText("Role : " +character.role)
        holder.expandingView.setLeftIcon(R.drawable.ic_baseline_person_outline_24)
        holder.speciesTextView.text = context.getString(R.string.species_prefix, character.species)
        holder.bloodStatusTextView.text = context.getString(R.string.blood_status_prefix, character.bloodStatus)
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var expandingView: ExpandableView = itemView.findViewById(R.id.characters_expandable_view)
        var speciesTextView: TextView = itemView.findViewById(R.id.species)
        var bloodStatusTextView: TextView = itemView.findViewById(R.id.blood_status)
    }
}