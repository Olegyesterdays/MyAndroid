package com.example.task01

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ItemAdapter public constructor(
    private var context: Context?,
    private var items: ArrayList<Int>?
) : BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var textView: TextView


    override fun getCount(): Int {
        return items!!.size
    }

    override fun getItem(position: Int): Any? {
        return items!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView

        if (view == null) {
            val inflater =
                context!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item, parent, false)
        }

        val textView = view!!.findViewById<TextView>(R.id.text_view)
        textView.text = items?.get(position).toString()

        if (position % 2 == 0){
            view.setBackgroundResource(R.color.red)
        } else{
            view.setBackgroundResource(R.color.blue)
        }

        return view
    }
}