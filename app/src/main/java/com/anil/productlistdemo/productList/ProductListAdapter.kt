package com.anil.productlistdemo.productList

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anil.productlistdemo.R
import com.anil.productlistdemo.databinding.ItemProductBinding
import com.anil.productlistdemo.model.Product
import com.bumptech.glide.Glide

class ProductListAdapter(
    private val context: Context,
    private val values: List<Product>,
    private val onProductClick: (String) -> Unit
) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.title
        holder.contentView.text = item.category

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

    inner class ViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.itemNumber
        val contentView: TextView = binding.content
        val image: ImageView = binding.image
    }
}