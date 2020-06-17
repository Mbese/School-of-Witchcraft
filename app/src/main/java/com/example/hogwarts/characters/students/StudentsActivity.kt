package com.example.hogwarts.characters.students

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

class StudentsActivity : AppCompatActivity() {

    private val viewModel by inject<HouseCharactersViewModel>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var studentAdapter: StudentsAdapter
    private lateinit var studentList: ArrayList<Character>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        progressBar = findViewById(R.id.characters_progress_bar)
        recyclerView = findViewById(R.id.students_recyclerView)

        recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()
        studentList = ArrayList()

        studentAdapter = StudentsAdapter(this, studentList)

        recyclerView.adapter = studentAdapter

        showStudents()
    }

    override fun onResume() {
        super.onResume()
        supportActionBar?.title = getString(R.string.students_screen_title)
    }

    private fun showStudents() {
        viewModel.characters.observe(this, Observer {
            progressBar.visibility = View.GONE
            it.let {
                for (character in it) {
                    if (character.role != null && character.role.toLowerCase() == getString(R.string.keyword_student)) {
                        studentList.add(character)
                    }
                }
            }
            studentAdapter.notifyDataSetChanged()
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}