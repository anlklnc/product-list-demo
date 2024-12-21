package com.anil.productlistdemo.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.anil.productlistdemo.R
import com.anil.productlistdemo.databinding.FragmentProductListBinding
import com.anil.productlistdemo.productDetail.ProductDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment : Fragment() {

    private val viewModel: ProductListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentProductListBinding.inflate(inflater, container, false)
        viewModel.init()

        viewModel.pagerList.observe(viewLifecycleOwner) {
            binding.pager.adapter = PagerAdapter(requireContext(), it) { id ->
                openProductDetailsPage(id)
            }
            binding.indicator.attachTo(binding.pager)
        }

        viewModel.productList.observe(viewLifecycleOwner) {
            binding.list.adapter = ProductListAdapter(requireContext(), it) { id ->
                openProductDetailsPage(id)
            }
        }

        return binding.root
    }

    private fun openProductDetailsPage(id: String) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .replace(R.id.fragment_container_view, ProductDetailFragment.newInstance(id))
                .setReorderingAllowed(true)
                .addToBackStack("ProductDetailFragment")
                .commit()
        }
    }
}