package com.example.starwars.detail.presentation

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starwars.R
import com.example.starwars.categories.presentation.ICallCategoryDetail
import com.example.starwars.detail.domain.model.Detail
import com.squareup.picasso.Picasso


class DetailAdapter(
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>(), Filterable {

    var items: MutableList<Detail> = mutableListOf()
    var itemsFiltered: MutableList<Detail> = mutableListOf()
    private var layoutManager: StaggeredGridLayoutManager? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }

    fun addData(list: List<Detail>) {
        items = list as ArrayList<Detail>
        itemsFiltered = items
        notifyDataSetChanged()
    }

    fun setLayoutManager(layoutManager: StaggeredGridLayoutManager) {
        this.layoutManager = layoutManager
    }

    override fun getItemCount(): Int = itemsFiltered.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            ViewType.DETAILED.ordinal -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.detail_list_grid, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.detail_list_line, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemsFiltered[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) ViewType.SMALL.ordinal
        else ViewType.DETAILED.ordinal
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val labelOne: TextView = view.findViewById(R.id.label_one)
        private val labelTwo: TextView = view.findViewById(R.id.label_two)
        private val labelThree: TextView = view.findViewById(R.id.label_three)
        private val labelFour: TextView = view.findViewById(R.id.label_four)
        private val labelFive: TextView = view.findViewById(R.id.label_five)

        private val textOne: TextView = view.findViewById(R.id.text_one)
        private val textTwo: TextView = view.findViewById(R.id.text_two)
        private val textThree: TextView = view.findViewById(R.id.text_three)
        private val textFour: TextView = view.findViewById(R.id.text_four)
        private val textFive: TextView = view.findViewById(R.id.text_five)

        private val cardView: CardView = view.findViewById(R.id.card_detail)
        private val imageView: ImageView = view.findViewById(R.id.image_detail)

        @SuppressLint("NotifyDataSetChanged")
        fun bind(item: Detail) {
            Picasso.with(itemView.context).load(item.imageUrl).into(imageView)
            labelOne.text = item.labelOne
            labelTwo.text = item.labelTwo
            labelThree.text = item.labelThree
            labelFour.text = item.labelFour
            labelFive.text = item.labelFive
            textOne.text = item.textOne
            textTwo.text = item.textTwo
            textThree.text = item.textThree
            textFour.text = item.textFour
            textFive.text = item.textFive
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) itemsFiltered = items else {
                    val filteredList = ArrayList<Detail>()
                    items
                        .filter {
                            (it.textOne.uppercase().contains(constraint!!))

                        }
                        .forEach { filteredList.add(it) }
                    itemsFiltered = filteredList

                    Log.e("performFiltering: t1: ", filteredList.size.toString())

                }
                return FilterResults().apply { values = itemsFiltered }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {

                itemsFiltered = if (results?.values == null)
                    ArrayList()
                else
                    results.values as ArrayList<Detail>
                notifyDataSetChanged()

                Log.e("performFiltering: t2 ", "called" + itemsFiltered.size)

            }
        }
    }
}