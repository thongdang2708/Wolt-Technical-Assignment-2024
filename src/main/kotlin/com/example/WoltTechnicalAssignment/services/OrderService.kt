package com.example.WoltTechnicalAssignment.services

import com.example.WoltTechnicalAssignment.constants.Constants
import com.example.WoltTechnicalAssignment.entity.domain.DeliveryResponse
import com.example.WoltTechnicalAssignment.entity.domain.OrderRequest
import com.example.WoltTechnicalAssignment.utils.Utils
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.*
import kotlin.math.roundToInt

@Service
class OrderService () {

    fun getDeliveryFeeFromOrder (orderRequest: OrderRequest) : DeliveryResponse{

        var surcharge = 0.0;

//      Calculation of surcharge based on cart values above 0 zero and below 10
        var cart_value_from_cents_to_euros = (orderRequest.cart_value.toDouble() / 100.0);

        if (cart_value_from_cents_to_euros < Constants.BASE_CART_VALUE) {
            surcharge = Utils.roundedDoubleToTwoDecimals(Constants.BASE_CART_VALUE.toDouble() - cart_value_from_cents_to_euros)
        }

//      Calculation of delivery fee based on distance
        var delivery_fee = if (orderRequest.delivery_distance <= Constants.BASE_DISTANCE) Constants.BASE_DELIVERY_FEE else Utils.calculateAdditionalFeeBasedOnDistance(orderRequest.delivery_distance)

//      Update of surcharge based on number of items
        var updated_surcharge_based_on_number_of_items = Utils.updateSurchageBaseOnNumberOfItems(orderRequest.number_of_items, surcharge)

//      Total of delivery fee and surcharge
        var total_delivery_including_surcharge = Utils.roundedDoubleToTwoDecimals(delivery_fee.toDouble() + updated_surcharge_based_on_number_of_items)

//      Check that total delivery fee including surcharge is zero when cart value is larger than 200 euros
        if ((orderRequest.cart_value / 100.0) >= (Constants.MAX_CART_VALUE_FOR_FREE_DELIVERY).toDouble()) {
            total_delivery_including_surcharge = 0.0;
        }

//      Re-calculation of total delivery fee including surcharge when it is a rush hour on Friday between 3 - 7 PM (15:00 - 19:00)
        if (Constants.RUSH_DAY_OF_A_WEEK == Utils.getDayOfWeek(orderRequest.time) && (Constants.MIN_RUSH_HOUR <= Utils.getHour(orderRequest.time) && Utils.getHour(orderRequest.time) <= Constants.MAX_RUSH_HOUR)) {
            total_delivery_including_surcharge =  Utils.roundedDoubleToTwoDecimals(total_delivery_including_surcharge * Constants.RUSH_RATE_FACTOR)
        }

//      Check that total delivery fee including surcharge cannot be more than 15 euros
        if (total_delivery_including_surcharge > Constants.MAX_DELIVERY_FEE) {
            total_delivery_including_surcharge = Constants.MAX_DELIVERY_FEE.toDouble()
        }

//      The total after all
        var final_delivery_fee = Utils.roundedDoubleToTwoDecimals(total_delivery_including_surcharge * 100.0).toInt();

        return DeliveryResponse(delivery_fee = final_delivery_fee)
    }

}