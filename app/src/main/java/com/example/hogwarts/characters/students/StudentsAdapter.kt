package com.example.hogwarts.characters.students

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

class StudentsAdapter (
    private val context: Context,
    private val students: ArrayList<Character>
) : RecyclerView.Adapter<StudentsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = getLayoutInflater(parent.context).inflate(R.layout.characters_row, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return students.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val student = students[position]

        holder.expandingView.setTitleText(student.name)
        holder.speciesTextView.text = context.getString(R.string.species_prefix, student.species)
        holder.bloodStatusTextView.text =context.getString(R.string.blood_status_prefix, student.bloodStatus)
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