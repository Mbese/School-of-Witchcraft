package com.example.hogwarts.spells.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.spells.data.Spell
import com.example.hogwarts.spells.viewmodel.SpellsActivityViewModel
import org.koin.android.ext.android.inject

class SpellsActivity : AppCompatActivity() {

    private val viewModel by inject<SpellsActivityViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SpellsAdapter
    private lateinit var spellList: ArrayList<Spell>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spells)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        spellList = ArrayList()

        adapter = SpellsAdapter(this, spellList)

        recyclerView.adapter = adapter

        showSpells()
    }

    override fun onResume() {
        super.onResume()

        supportActionBar?.title = getString(R.string.spells_activity_title)
    }

    private fun showSpells() {
        viewModel.spells.observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let { it1 -> spellList.addAll(it1) }
            adapter.notifyDataSetChanged()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}