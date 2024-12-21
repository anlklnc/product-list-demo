package com.anil.productlistdemo.network

import com.anil.productlistdemo.model.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductApi {

    @GET("products")
    suspend fun getProductList(): List<Product>

    @GET("products?limit=5")
    suspend fun getPagerList(): List<Product>

    @GET("products/{id}")
    suspend fun getProductDetails(@Path("id")id: String): Product
}