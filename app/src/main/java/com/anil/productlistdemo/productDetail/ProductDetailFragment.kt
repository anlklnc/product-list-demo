package com.anil.productlistdemo.productDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anil.productlistdemo.databinding.FragmentProductDetailBinding
import com.anil.productlistdemo.model.Product
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
            // Update UI
            showProduct(it)
        }

        return binding.root
    }

    private fun showProduct(productDetail: Product) {
        // Show product detail
        binding.title.text = productDetail.title
    }

    companion object {
        fun newInstance(id: String) = ProductDetailFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_PRODUCT_ID, id)
            }
        }
    }
}