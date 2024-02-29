package com.example.WoltTechnicalAssignment.utils

import com.example.WoltTechnicalAssignment.constants.Constants
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.*
import kotlin.math.roundToInt

class Utils {
    companion object {
        fun calculateAdditionalFeeBasedOnDistance(delivery_distance : Int) : Int {

            var new_distance_with_minus_for_initial_distance = delivery_distance - Constants.BASE_DISTANCE;

            var delivery_fee = 1;

            while (new_distance_with_minus_for_initial_distance > Constants.BASE_DISTANCE_FOR_ADDITIONAL_FEE) {
                new_distance_with_minus_for_initial_distance -= Constants.BASE_DISTANCE_FOR_ADDITIONAL_FEE;
                delivery_fee++;
            }

            return Constants.BASE_DELIVERY_FEE + delivery_fee;
        }

        fun updateSurchageBaseOnNumberOfItems (number_of_items : Int, surcharge : Double) : Double {

            if (number_of_items <= Constants.BASE_NUMBER_OF_ITEMS_NOT_TO_BE_SURCHARGED) {
                return surcharge;
            }

            var additional_amount_of_items_from_above_fifth_item = 0;
            var base_fee_for_above_max_number_of_items = 0.0;

            for (i in Constants.MIN_NUMBER_OF_ITEMS_TO_BE_SURCHARGED..number_of_items) {
                additional_amount_of_items_from_above_fifth_item++;
            }

            if (number_of_items > Constants.MAX_NUMBER_OF_ITEMS_TO_BE_SURCHARGED) {
                base_fee_for_above_max_number_of_items = Constants.BASE_FEE_FOR_ABOVE_MAX_NUMBER_OF_ITEMS
            }

            val updated_surcharge = surcharge + ((additional_amount_of_items_from_above_fifth_item.toDouble() * Constants.SURCHARGE_VALUE/100) + base_fee_for_above_max_number_of_items);

            return updated_surcharge;
        }

        fun getDayOfWeek (utcTimestamp : String) : String {

            val instant = Instant.parse(utcTimestamp)

            val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

            val dayOfWeekEnum = localDateTime.dayOfWeek

            val dayOfWeekString = dayOfWeekEnum.getDisplayName(TextStyle.FULL, Locale.ENGLISH)

            return dayOfWeekString
        }

        fun getHour (utcTimestamp : String) : Int {

            val instant = Instant.parse(utcTimestamp)

            val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of("UTC"))

            val hour = localDateTime.hour

            return hour;
        }

        fun roundedDoubleToTwoDecimals (number: Double) : Double {
            return (number * 100.0).roundToInt() / 100.0
        }
    }
}