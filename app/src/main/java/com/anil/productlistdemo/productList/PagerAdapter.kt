package com.anil.productlistdemo.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anil.productlistdemo.R
import com.anil.productlistdemo.databinding.ItemPagerBinding
import com.anil.productlistdemo.model.Product
import com.bumptech.glide.Glide

class PagerAdapter(
    private val context: Context,
    private val values: List<Product>,
    private val onProductClick: (String) -> Unit
) : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemPagerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.title
        holder.category.text = item.category
        holder.price.text = "${item.price} $"
        Glide.with(context)
            .load(item.image)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)
        holder.itemView.setOnClickListener {
            onProductClick(item.id.toString())
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemPagerBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        val category: TextView = binding.category
        val price: TextView = binding.price
        val image: ImageView = binding.image
    }
}