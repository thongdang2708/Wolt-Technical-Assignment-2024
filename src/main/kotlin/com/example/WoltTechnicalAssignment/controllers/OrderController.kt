package com.example.WoltTechnicalAssignment.controllers

import com.example.WoltTechnicalAssignment.entity.domain.DeliveryResponse
import com.example.WoltTechnicalAssignment.entity.domain.OrderRequest
import com.example.WoltTechnicalAssignment.services.OrderService
import jakarta.validation.Valid
import jakarta.validation.ValidationException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.validation.BindingResult


@RestController
@RequestMapping
class OrderController {

    val orderService : OrderService;
    @Autowired
    constructor(orderService: OrderService) {
        this.orderService = orderService;
    }

    @PostMapping
    fun getDeliveryFeeFromOrder (@Valid @RequestBody orderRequest: OrderRequest) : ResponseEntity<DeliveryResponse> {

        return ResponseEntity(this.orderService.getDeliveryFeeFromOrder(orderRequest), HttpStatus.CREATED);
    }

}