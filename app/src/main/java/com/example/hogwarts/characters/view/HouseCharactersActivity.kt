package com.example.hogwarts.characters.view

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
import com.example.hogwarts.characters.data.Character
import com.example.hogwarts.characters.viewmodel.HouseCharactersViewModel
import org.koin.android.ext.android.inject

class HouseCharactersActivity : AppCompatActivity() {

    private val viewModel by inject<HouseCharactersViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterHouse: HouseCharactersAdapter
    private lateinit var characterList: ArrayList<Character>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_actvity)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        progressBar = findViewById(R.id.characters_progress_bar)
        recyclerView = findViewById(R.id.characters_recyclerView)

        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        characterList = ArrayList()

        adapterHouse = HouseCharactersAdapter(this, characterList)

        recyclerView.adapter = adapterHouse

        val characters = intent.getStringArrayListExtra("characters")
        showCharacters(characters)
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = getString(R.string.characters_screen_title)
    }

    private fun showCharacters(characters: java.util.ArrayList<String>?) {
        if (!characters.isNullOrEmpty()) {
            viewModel.characters.observe(this, Observer {
                progressBar.visibility = View.GONE
                it?.let {
                    for (characterId in characters) {
                        for (character in it) {
                            if (characterId == character._id) {
                                characterList.add(character)
                            }
                        }
                    }
                }
                adapterHouse.notifyDataSetChanged()
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}