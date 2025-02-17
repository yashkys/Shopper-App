package com.example.data.network

import com.example.data.model.request.AddToCartDataRequest
import com.example.data.model.response.CartListResponse
import com.example.data.model.response.CategoriesListResponse
import com.example.data.model.response.ProductListResponse
import com.example.domain.model.request.AddToCartRequest
import com.example.domain.model.response.CartResponse
import com.example.domain.model.response.CategoryResponse
import com.example.domain.model.response.ProductResponse
import com.example.domain.network.NetworkService
import com.example.domain.network.ResultWrapper
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.Parameters
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.ktor.utils.io.errors.IOException

class NetworkServiceImpl (
    val client: HttpClient
) :NetworkService {
    private val baseUrl = "https://ecommerce-ktor-4641e7ff1b63.herokuapp.com"

    override suspend fun getProducts(category: Int?): ResultWrapper<ProductResponse> {
        val url = if(category != null) {
            "$baseUrl/products/category/$category"
        } else {
            "$baseUrl/products"
        }
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { dataModels: ProductListResponse ->
                dataModels.toProductList()
            }
        )
    }

    override suspend fun getCategories(): ResultWrapper<CategoryResponse> {
        val url = "$baseUrl/categories"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Get,
            mapper = { dataModels: CategoriesListResponse ->
                dataModels.toCategoryList()
            }
        )
    }

    override suspend fun addProductToCart(
        request: AddToCartRequest
    ): ResultWrapper<CartResponse> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(
            url = url,
            method = HttpMethod.Post,
            body = AddToCartDataRequest.fromCartRequest(request),
            mapper = { cartItem: CartListResponse ->
                cartItem.toCart()
            }
        )
    }

    override suspend fun getCart(): ResultWrapper<CartResponse> {
        val url = "$baseUrl/cart/1"
        return makeWebRequest(url = url,
            method = HttpMethod.Get,
            mapper = { cartItem: CartListResponse ->
                cartItem.toCart()
            })
    }

    @OptIn(InternalAPI::class)
    suspend inline fun<reified T,R> makeWebRequest(
        url: String,
        method: HttpMethod,
        body: Any? = null,
        headers: Map<String, String> = emptyMap(),
        parameters: Map<String, String> = emptyMap(),
        noinline mapper: ((T) -> R)? = null
    ): ResultWrapper<R> {
        return try {
            val response = client.request(url) {
                this.method = method

                url {
                    this.parameters.appendAll(Parameters.build {
                        parameters.forEach { (key, value) ->
                            append(key, value)
                        }
                    })
                }

                // apply headers
                headers.forEach { (key, value) ->
                    header(key,value)
                }

                if(body!=null) {
                    setBody(body)
                }

                // set content type
                contentType(ContentType.Application.Json)
            }.body<T>()
            val result: R = mapper?.invoke(response) ?: response as R
            ResultWrapper.Success(result)
        }  catch (e: ClientRequestException) {
            ResultWrapper.Failure(e)
        } catch (e: ServerResponseException) {
            ResultWrapper.Failure(e)
        } catch (e: IOException) {
            ResultWrapper.Failure(e)
        } catch (e: Exception) {
            ResultWrapper.Failure(e)
        }

    }

}