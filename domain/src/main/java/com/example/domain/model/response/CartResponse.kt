package com.example.domain.model.response

import com.example.domain.model.CartItem

class CartResponse(
    val data: List<CartItem>,
    val msg: String
) {
}