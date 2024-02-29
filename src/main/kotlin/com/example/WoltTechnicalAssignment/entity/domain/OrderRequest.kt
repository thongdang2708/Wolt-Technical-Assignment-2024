package com.example.WoltTechnicalAssignment.entity.domain

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank

data class OrderRequest(@field:Min(value = 0, message = "Cart value must not be below 0") val cart_value : Int, @field:Min(value = 0, message = "Delivery distance must not be below 0") val delivery_distance : Int, @field:Min(value = 0, message = "Number of items must not be below 0") val number_of_items : Int, @field:NotBlank(message = "Time must not be blank!") val time : String)
