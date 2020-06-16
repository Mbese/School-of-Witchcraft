package com.example.hogwarts.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.model.House
import com.example.hogwarts.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HousesAdapter
    private lateinit var houseList: ArrayList<House>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        houseList = ArrayList()

        adapter = HousesAdapter(this, houseList)

        recyclerView.adapter = adapter

        showHouses()
    }

    private fun showHouses() {
        viewModel.houses?.observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let { it1 -> houseList.addAll(it1) }
            adapter.notifyDataSetChanged()
        })
    }

}