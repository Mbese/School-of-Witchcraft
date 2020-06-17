package com.example.hogwarts.spells.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.VisibleForTesting
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.spells.data.Spell

class SpellsAdapter (
    private val context: Context,
    private val spells: ArrayList<Spell>
) : RecyclerView.Adapter<SpellsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView = getLayoutInflater(parent.context).inflate(R.layout.spell_item_row, parent, false)
        return MyViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return spells.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val spell = spells[position]

        holder.spellTextView.text = spell.spell
        holder.spellTypeTextView.text = context.getString(R.string.spell_type_prefix, spell.type)
        holder.spellEffectTextView.text = context.getString(R.string.spell_effect_prefix, spell.effect)
    }

    @VisibleForTesting
    internal fun getLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var spellTextView: TextView = itemView.findViewById(R.id.spell)
        var spellTypeTextView: TextView = itemView.findViewById(R.id.spell_type)
        var spellEffectTextView: TextView = itemView.findViewById(R.id.spell_effect)
    }

}