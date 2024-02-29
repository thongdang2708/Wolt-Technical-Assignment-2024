package com.example.WoltTechnicalAssignment

import com.example.WoltTechnicalAssignment.controllers.OrderController
import com.example.WoltTechnicalAssignment.entity.domain.OrderRequest
import com.example.WoltTechnicalAssignment.services.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.http.HttpStatus

@SpringBootTest
class WoltTechnicalAssignmentApplicationTests {

	val orderService : OrderService;
	val orderController : OrderController;

	@Autowired
	constructor(orderService: OrderService, orderController: OrderController) {
		this.orderService = orderService;
		this.orderController = orderController;
	}

	@Test
	fun contextLoads() {
	}

	@Test
	fun checkOrderService_withCartValueBelow1000 () {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 2235, number_of_items = 4, time = "2024-01-15T13:00:00Z")
		Assertions.assertEquals(710, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_withCartValueEqualsOrAbove1000() {
		val orderRequest = OrderRequest(cart_value = 1000, delivery_distance = 2235, number_of_items = 4, time = "2024-01-15T13:00:00Z")
		Assertions.assertEquals(500, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_withDeliveryDistanceEqualsOrBelow1000() {
		val orderRequest = OrderRequest(cart_value = 1000, delivery_distance = 1000, number_of_items = 4, time = "2024-01-15T13:00:00Z")
		Assertions.assertEquals(200, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_withDeliveryDistanceAbove1000() {
		val orderRequest = OrderRequest(cart_value = 1000, delivery_distance = 2235, number_of_items = 4, time = "2024-01-15T13:00:00Z")
		Assertions.assertEquals(500, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_withNumberOfItemsBelow5() {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 2235, number_of_items = 4, time = "2024-01-15T13:00:00Z")
		Assertions.assertEquals(710, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_withNumberOfItemsEqualsOrAbove5() {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 2235, number_of_items = 10, time = "2024-01-15T13:00:00Z")
		Assertions.assertEquals(1010, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_inRushHour() {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 2235, number_of_items = 10, time = "2024-01-19T16:43:00Z")
		Assertions.assertEquals(1212, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_cartValueWith0() {
		val orderRequest = OrderRequest(cart_value = 0, delivery_distance = 1001, number_of_items = 4, time = "2024-01-19T12:43:00Z")
		Assertions.assertEquals(1300, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_deliveryDistanceWith0() {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 0, number_of_items = 4, time = "2024-01-19T12:43:00Z")
		Assertions.assertEquals(410, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_numberOfItemsWith0() {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 2235, number_of_items = 0, time = "2024-01-19T12:43:00Z")
		Assertions.assertEquals(710, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderService_cartValueExceeds200EurosForFreeDelivery() {
		val orderRequest = OrderRequest(cart_value = 20005, delivery_distance = 3001, number_of_items = 20, time = "2024-01-19T12:43:00Z")
		Assertions.assertEquals(0, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}


	@Test
	fun checkOrderService_deliveryFeeExceeds15Euros() {
		val orderRequest = OrderRequest(cart_value = 500, delivery_distance = 3001, number_of_items = 20, time = "2024-01-19T12:43:00Z")
		Assertions.assertEquals(1500, this.orderService.getDeliveryFeeFromOrder(orderRequest).delivery_fee)
	}

	@Test
	fun checkOrderController () {
		val orderRequest = OrderRequest(cart_value = 790, delivery_distance = 2235, number_of_items = 4, time = "2024-01-15T13:00:00Z")
		this.orderService.getDeliveryFeeFromOrder(orderRequest)
		Assertions.assertEquals(710, this.orderController.getDeliveryFeeFromOrder(orderRequest).body?.delivery_fee)
		Assertions.assertEquals(HttpStatus.CREATED, this.orderController.getDeliveryFeeFromOrder(orderRequest).statusCode)
	}

}
