package com.example.hogwarts.characters.students

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hogwarts.R
import com.example.hogwarts.characters.data.Character
import com.example.hogwarts.widgets.ExpandableView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StudentsAdapterTest {
    @Mock
    private lateinit var mockView: View
    @Mock
    private lateinit var mockSpeciesTextView: TextView
    @Mock
    private lateinit var mockBloodStatusTextView: TextView
    @Mock
    private lateinit var mockExpandingView: ExpandableView
    @Mock
    private lateinit var mockActivity: Activity
    @Mock
    private lateinit var mockCharacterModel: Character

    private lateinit var adapter: StudentsAdapter
    private lateinit var viewHolder: StudentsAdapter.MyViewHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.doReturn(mockSpeciesTextView).`when`(mockView).findViewById<TextView>(R.id.species)
        Mockito.doReturn(mockBloodStatusTextView).`when`(mockView).findViewById<TextView>(R.id.blood_status)
        Mockito.doReturn(mockExpandingView).`when`(mockView).findViewById<ExpandableView>(R.id.characters_expandable_view)

        val characterList = ArrayList<Character>()
        characterList.add(mockCharacterModel)

        adapter = StudentsAdapter(mockActivity, characterList)
        viewHolder = StudentsAdapter.MyViewHolder(mockView)

        Mockito.`when`(mockView.context).thenReturn(mockActivity)
    }

    @Test
    fun `test house list is populated` () {
        val list = arrayListOf(
            Character(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                ministryOfMagic = false,
                orderOfThePhoenix = false,
                dumbledoresArmy = false,
                deathEater = false,
                bloodStatus = "blood status",
                species = "Species"
            )
        )

        adapter = StudentsAdapter(mockActivity, list)
        Assert.assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `test onCreateViewHolder should inflate characters list` () {
        val mockViewGroup = Mockito.mock(ViewGroup::class.java)
        val mockLayoutInflater = Mockito.mock(LayoutInflater::class.java)
        adapter = Mockito.spy(adapter)

        Mockito.`when`(mockViewGroup.context).thenReturn(Mockito.mock(Context::class.java))
        Mockito.`when`(
            mockLayoutInflater.inflate(
                Mockito.anyInt(),
                Mockito.any(ViewGroup::class.java),
                Mockito.anyBoolean()
            )
        ).thenReturn(mockView)

        Mockito.doReturn(mockLayoutInflater)
            .`when`(adapter).getLayoutInflater(Mockito.mock(Context::class.java))

        adapter.onCreateViewHolder(mockViewGroup, 0)

        Mockito.verify(mockLayoutInflater).inflate(R.layout.characters_row, mockViewGroup, false)
    }

    @Test
    fun `test onBindViewHolder views should be bound to expandable view` () {
        val viewHolder = StudentsAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            Character(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                ministryOfMagic = false,
                orderOfThePhoenix = false,
                dumbledoresArmy = false,
                deathEater = false,
                bloodStatus = "blood status",
                species = "Species"
            )
        )

        adapter = StudentsAdapter(mockActivity, list)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockExpandingView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to species` () {
        val viewHolder = StudentsAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            Character(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                ministryOfMagic = false,
                orderOfThePhoenix = false,
                dumbledoresArmy = false,
                deathEater = false,
                bloodStatus = "blood status",
                species = "Species"
            )
        )

        adapter = StudentsAdapter(mockActivity, list)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockSpeciesTextView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to blood status` () {
        val viewHolder = StudentsAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            Character(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                ministryOfMagic = false,
                orderOfThePhoenix = false,
                dumbledoresArmy = false,
                deathEater = false,
                bloodStatus = "blood status",
                species = "Species"
            )
        )

        adapter = StudentsAdapter(mockActivity, list)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockBloodStatusTextView)
    }
}