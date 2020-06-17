package com.example.hogwarts

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hogwarts.characters.students.StudentsActivity
import com.example.hogwarts.houses.view.HousesActivity
import com.example.hogwarts.spells.view.SpellsActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent: Intent
        show_houses_btn.setOnClickListener {
            intent = Intent(this, HousesActivity::class.java)
            startActivity(intent)
        }

        show_students_btn.setOnClickListener {
            intent = Intent(this, StudentsActivity::class.java)
            startActivity(intent)
        }

        show_spells_btn.setOnClickListener {
            intent = Intent(this, SpellsActivity::class.java)
            startActivity(intent)
        }
    }
}