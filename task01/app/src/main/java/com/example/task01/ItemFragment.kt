package com.example.task01

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView
import androidx.fragment.app.Fragment

class ItemFragment : Fragment() {
    private lateinit var gridView: GridView
    private lateinit var gridItemAdapter: ItemAdapter
    private lateinit var items: ArrayList<Int>
    private lateinit var addButton: Button
    private var numColumns: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.item_fragment, container, false)

        gridView = view.findViewById(R.id.grid_view)
        items = ArrayList()
        numColumns = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4
        gridView.numColumns = numColumns
        gridItemAdapter = ItemAdapter(requireContext(), items)
        gridView.adapter = gridItemAdapter

        addButton = view.findViewById(R.id.add_button)
        addButton.setOnClickListener {
            items.add(items.size)
            gridItemAdapter.notifyDataSetChanged()
        }

        return view
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        numColumns = if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) 3 else 4
        gridView.numColumns = numColumns
    }

}