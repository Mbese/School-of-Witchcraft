package com.example.hogwarts.houses.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.characters.view.HouseCharactersActivity
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.houses.viewmodel.HousesActivityViewModel
import org.koin.android.ext.android.inject

class HousesActivity : AppCompatActivity() {

    private val viewModel by inject<HousesActivityViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HousesAdapter
    private lateinit var houseList: ArrayList<House>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_houses)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        houseList = ArrayList()

        adapter = HousesAdapter(this, houseList, object : HousesAdapter.ItemClickListener {
            override fun onItemClicked(characterIds: ArrayList<String>) {
                showCharacterScreen(characterIds)
            }
        })

        recyclerView.adapter = adapter

        showHouses()
    }

    private fun showCharacterScreen(characterIds: java.util.ArrayList<String>) {
        val intent = Intent(this, HouseCharactersActivity::class.java)
        intent.putStringArrayListExtra("characters", characterIds)
        startActivity(intent)
    }

    private fun showHouses() {
        viewModel.houses.observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let { it1 -> houseList.addAll(it1) }
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