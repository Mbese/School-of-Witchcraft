package com.example.hogwarts.spells.view

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.hogwarts.R
import com.example.hogwarts.spells.data.Spell
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class SpellsAdapterTest {
    @Mock
    private lateinit var mockView: View
    @Mock
    private lateinit var mockSpellTextView: TextView
    @Mock
    private lateinit var mockSpellTypeTextView: TextView
    @Mock
    private lateinit var mockSpellEffectTextView: TextView
    @Mock private lateinit var mockActivity: Activity
    @Mock private lateinit var mockSpellModel: Spell

    private lateinit var adapter: SpellsAdapter
    private lateinit var viewHolder: SpellsAdapter.MyViewHolder

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        doReturn(mockSpellTextView).`when`(mockView).findViewById<TextView>(R.id.spell)
        doReturn(mockSpellTypeTextView).`when`(mockView).findViewById<TextView>(R.id.spell_type)
        doReturn(mockSpellEffectTextView).`when`(mockView).findViewById<TextView>(R.id.spell_effect)

        val spellList = ArrayList<Spell>()
        spellList.add(mockSpellModel)

        adapter = SpellsAdapter(mockActivity, spellList)
        viewHolder = SpellsAdapter.MyViewHolder(mockView)

        `when`(mockView.context).thenReturn(mockActivity)
    }

    @Test
    fun `test spell list is populated` () {
        val list = arrayListOf(
            Spell(
                "1",
                "spell",
                "type",
                "effect"
            )
        )

        adapter = SpellsAdapter(mockActivity, list)
        assertEquals(1, adapter.itemCount)
    }

    @Test
    fun `test onCreateViewHolder should inflate spells list` () {
        val mockViewGroup = mock(ViewGroup::class.java)
        val mockLayoutInflater = mock(LayoutInflater::class.java)
        adapter = spy(adapter)

        `when`(mockViewGroup.context).thenReturn(mock(Context::class.java))
        `when`(
            mockLayoutInflater.inflate(anyInt(),
                any(ViewGroup::class.java),
                anyBoolean()
            )
        ).thenReturn(mockView)

        doReturn(mockLayoutInflater).`when`(adapter).getLayoutInflater(mock(Context::class.java))

        adapter.onCreateViewHolder(mockViewGroup, 0)

        verify(mockLayoutInflater).inflate(R.layout.spell_item_row, mockViewGroup, false)
    }

    @Test
    fun `test onBindViewHolder views should be bound to spell` () {
        val viewHolder = SpellsAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            Spell(
                "1",
                "spell",
                "type",
                "effect"
            )
        )

        adapter = SpellsAdapter(mockActivity, list)
        adapter.onBindViewHolder(viewHolder, 0)
        verify(mockSpellTextView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to spell type` () {
        val viewHolder = SpellsAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            Spell(
                "1",
                "spell",
                "type",
                "effect"
            )
        )

        adapter = SpellsAdapter(mockActivity, list)
        adapter.onBindViewHolder(viewHolder, 0)
        verify(mockSpellTypeTextView)
    }

    @Test
    fun `test onBindViewHolder views should be bound to spell effect` () {
        val viewHolder = SpellsAdapter.MyViewHolder(mockView)

        val list = arrayListOf(
            Spell(
                "1",
                "spell",
                "type",
                "effect"
            )
        )

        adapter = SpellsAdapter(mockActivity, list)
        adapter.onBindViewHolder(viewHolder, 0)
        verify(mockSpellEffectTextView)
    }
}