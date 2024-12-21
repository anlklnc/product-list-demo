package com.anil.productlistdemo.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anil.productlistdemo.R
import com.anil.productlistdemo.databinding.FragmentProductDetailBinding
import com.anil.productlistdemo.model.Product
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PRODUCT_ID = "product_id"

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()
    private var productId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productId = it.getString(ARG_PRODUCT_ID)
        }
        viewModel.init(productId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        viewModel.productDetail.observe(viewLifecycleOwner) {
            showProduct(it)
        }

        return binding.root
    }

    private fun showProduct(productDetail: Product) {
        binding.title.text = productDetail.title
        binding.description.text = productDetail.description
        binding.price.text = "${productDetail.price} $"
        binding.category.text = productDetail.category
        Glide.with(requireContext())
            .load(productDetail.image)
            .fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.image)
    }

    companion object {
        fun newInstance(id: String) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PRODUCT_ID, id)
            }
        }
    }
}