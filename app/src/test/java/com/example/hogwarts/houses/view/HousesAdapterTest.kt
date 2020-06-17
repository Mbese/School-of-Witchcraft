package com.example.hogwarts.houses.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hogwarts.R
import com.example.hogwarts.houses.data.House
import com.example.hogwarts.widgets.ExpandableView
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HousesAdapterTest {
    @Mock
    private lateinit var mockView: View
    @Mock
    private lateinit var mockMascotTextView: TextView
    @Mock
    private lateinit var mockHeadOfHouseTextView: TextView
    @Mock
    private lateinit var mockHouseGhostTextView: TextView
    @Mock
    private lateinit var mockExpandingView: ExpandableView
    @Mock
    private lateinit var mockActivity: Activity
    @Mock
    private lateinit var mockSpellModel: House
    @Mock
    private lateinit var mockListener: HousesAdapter.ItemClickListener

    private lateinit var adapter: HousesAdapter
    private lateinit var viewHolder: HousesAdapter.MyViewHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        Mockito.doReturn(mockMascotTextView).`when`(mockView).findViewById<TextView>(R.id.spell)
        Mockito.doReturn(mockHeadOfHouseTextView).`when`(mockView).findViewById<TextView>(R.id.spell_type)
        Mockito.doReturn(mockHouseGhostTextView).`when`(mockView).findViewById<TextView>(R.id.spell_effect)
        Mockito.doReturn(mockExpandingView).`when`(mockView).findViewById<ExpandableView>(R.id.expandable_view)

        val spellList = ArrayList<House>()
        spellList.add(mockSpellModel)

        adapter = HousesAdapter(mockActivity, spellList, mockListener)
        viewHolder = HousesAdapter.MyViewHolder(mockView)

        Mockito.`when`(mockView.context).thenReturn(mockActivity)
    }

    @Test
    fun `test house list is populated` () {
        val list = arrayListOf(
            House(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                "fonder",
                0,
                "School",
                arrayListOf("members"),
                arrayListOf("values"),
                arrayListOf("colours")
            )
        )

        adapter = HousesAdapter(mockActivity, list, mockListener)
        Assert.assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `test onCreateViewHolder should inflate houses list` () {
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

        Mockito.verify(mockLayoutInflater).inflate(R.layout.spell_item_row, mockViewGroup, false)
    }

    @Test
    fun `test onBindViewHolder views should be bound to expandable view` () {
        val viewHolder = HousesAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            House(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                "fonder",
                0,
                "School",
                arrayListOf("members"),
                arrayListOf("values"),
                arrayListOf("colours")
            )
        )

        adapter = HousesAdapter(mockActivity, list, mockListener)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockExpandingView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to mascot` () {
        val viewHolder = HousesAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            House(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                "fonder",
                0,
                "School",
                arrayListOf("members"),
                arrayListOf("values"),
                arrayListOf("colours")
            )
        )

        adapter = HousesAdapter(mockActivity, list, mockListener)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockMascotTextView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to head of house` () {
        val viewHolder = HousesAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            House(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                "fonder",
                0,
                "School",
                arrayListOf("members"),
                arrayListOf("values"),
                arrayListOf("colours")
            )
        )

        adapter = HousesAdapter(mockActivity, list, mockListener)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockHeadOfHouseTextView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to house ghost` () {
        val viewHolder = HousesAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            House(
                "1",
                "house",
                "mascot",
                "head of House",
                "house Ghost",
                "fonder",
                0,
                "School",
                arrayListOf("members"),
                arrayListOf("values"),
                arrayListOf("colours")
            )
        )

        adapter = HousesAdapter(mockActivity, list, mockListener)
        adapter.onBindViewHolder(viewHolder, 0)
        Mockito.verify(mockHouseGhostTextView)
    }
}