package com.example.starwars.detail.presentation

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
import com.example.starwars.categories.presentation.ICallCategoryDetail
import com.example.starwars.detail.domain.model.Detail
import com.squareup.picasso.Picasso


class DetailAdapter(
) : RecyclerView.Adapter<DetailAdapter.ViewHolder>() {

    private val items: MutableList<Detail> = mutableListOf()
    private lateinit var call: ICallDetail
    private var layoutManager: StaggeredGridLayoutManager? = null

    enum class ViewType {
        SMALL,
        DETAILED
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newItems: List<Detail>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    fun setCallDetail(call: ICallDetail) {
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
                    .inflate(R.layout.detail_list_grid, parent, false)
            )
            else -> ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.detail_list_line, parent, false)
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
            cardView.setOnClickListener {
                call.callDetail(item)
            }
        }
    }
}