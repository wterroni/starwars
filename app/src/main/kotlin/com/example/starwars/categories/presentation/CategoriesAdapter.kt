package com.example.starwars.categories.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starwars.R
import com.example.starwars.categories.domain.data.Category
import com.squareup.picasso.Picasso

class CategoriesAdapter(
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    private val items: MutableList<Category> = mutableListOf()
    private lateinit var call: IcallDetail
    private var layoutManager: StaggeredGridLayoutManager? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newItems: Array<Category>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setCallDetail(call: IcallDetail) {
        this.call = call
    }

    fun setLayoutManager(layoutManager: StaggeredGridLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.DETAILED.ordinal -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.categories_list_grid, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.categories_list_line, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.SMALL.ordinal
        else ViewType.DETAILED.ordinal
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.name_categories)
        private val cardView: CardView = view.findViewById(R.id.categoriesCard)
        private val imageView: ImageView = view.findViewById(R.id.image_categories)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: Category) {
            Picasso.with(itemView.context).load(item.imageUrl).into(imageView)
            textView.text = item.name
            cardView.setOnClickListener {
                call.callDetail(item)
            }
            //notifyDataSetChanged()
        }
    }
}
