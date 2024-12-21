package com.anil.productlistdemo.productList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anil.productlistdemo.data.AppContainer
import com.anil.productlistdemo.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val appContainer: AppContainer
) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _pagerList = MutableLiveData<List<Product>>()
    val pagerList: LiveData<List<Product>> = _pagerList

    fun init() {
        viewModelScope.launch {
            _pagerList.value = appContainer.productRepository.getPagerList()
        }

        viewModelScope.launch {
            _productList.value = appContainer.productRepository.getProductList()
        }
    }
}