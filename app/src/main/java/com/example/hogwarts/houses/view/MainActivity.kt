package com.example.hogwarts.houses.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hogwarts.R
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.houses.viewmodel.MainActivityViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel by inject<MainActivityViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HousesAdapter
    private lateinit var houseList: ArrayList<House>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        houseList = ArrayList()

        adapter = HousesAdapter(houseList)

        recyclerView.adapter = adapter

        showHouses()
    }

    private fun showHouses() {
        viewModel.houses.observe(this, Observer {
            progressBar.visibility = View.GONE
            it?.let { it1 -> houseList.addAll(it1) }
            adapter.notifyDataSetChanged()
        })
    }

}