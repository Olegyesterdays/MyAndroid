package company.vk.lection05.presentationlayer.adapters

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import company.vk.lection05.R
import company.vk.lection05.objects.Item

open class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.image)

    private val imageLoader by lazy { Picasso.get() }

    fun bind(item: Item) {
        val outer = this
        imageLoader.load(item.imageValue()).into(image, object : Callback {
            override fun onSuccess() {
                itemView.findViewById<ProgressBar>(R.id.progress).visibility = INVISIBLE
                itemView.findViewById<Button>(R.id.reload_button).visibility = INVISIBLE
            }

            override fun onError(e: Exception?) {
                val progressBar_cyclic = itemView.findViewById<ProgressBar>(R.id.progress)
                progressBar_cyclic.visibility = INVISIBLE
                val button = itemView.findViewById<Button>(R.id.reload_button)
                button.visibility = VISIBLE
                button.setOnClickListener {
                    button.visibility = INVISIBLE
                    progressBar_cyclic.visibility = VISIBLE
                    outer.bind(item)
                }
            }
        })
    }
}