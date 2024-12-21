package com.anil.productlistdemo.productDetail

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
class ProductDetailViewModel @Inject constructor(
    private val appContainer: AppContainer
): ViewModel() {

    private val _productDetail = MutableLiveData<Product>()
    val productDetail: LiveData<Product> = _productDetail

    fun init(id: String?) {
        id?.let {
            viewModelScope.launch {
                _productDetail.value = appContainer.productRepository.getProductDetails(id)
            }
        }
    }
}