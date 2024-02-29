package com.example.WoltTechnicalAssignment.constants

class Constants {

    companion object {
        val BASE_CART_VALUE = 10;
        val BASE_DISTANCE = 1000;
        val BASE_DELIVERY_FEE = 2;
        val BASE_DISTANCE_FOR_ADDITIONAL_FEE = 500;
        val BASE_NUMBER_OF_ITEMS_NOT_TO_BE_SURCHARGED = 4;
        val MIN_NUMBER_OF_ITEMS_TO_BE_SURCHARGED = 5;
        val MAX_NUMBER_OF_ITEMS_TO_BE_SURCHARGED = 12;
        val BASE_FEE_FOR_ABOVE_MAX_NUMBER_OF_ITEMS = 1.2;
        val SURCHARGE_VALUE = 50;
        val MAX_CART_VALUE_FOR_FREE_DELIVERY = 200;
        val MAX_DELIVERY_FEE = 15;
        val RUSH_DAY_OF_A_WEEK = "Friday";
        val MIN_RUSH_HOUR = 15;
        val MAX_RUSH_HOUR = 19;
        val RUSH_RATE_FACTOR = 1.2;
    }
}