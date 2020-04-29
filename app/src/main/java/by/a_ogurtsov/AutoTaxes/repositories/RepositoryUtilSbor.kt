package by.a_ogurtsov.AutoTaxes.repositories

class RepositoryUtilSbor {

    fun totalAmount(
        kind_auto: String,
        age: Int,
        legk_car_gibrid_switch: Boolean,
        legk_car_gibrid_capacity: String,
        us_gruz_car_weight: String,
        us_bus_engine: String,
        us_dumpTruck_weight: String,
        us_pricep_weight: String
    ): String {
        when (kind_auto) {
            "legk_car" -> {
                if (!legk_car_gibrid_switch) {
                    when (age) {
                        1 -> return "544 рубля 50 копеек"
                        2 -> return "816 рублей 70 копеек"
                    }
                } else {

                    when (age) {
                        1 -> {
                            when (legk_car_gibrid_capacity) {
                                "less_1000" -> return "936 рублей 50 копеек"
                                "in_1000_2000" -> return "1459 рублей 30 копеек"
                                "in_2000_3000" -> return "2787 рублей 80 копеек"
                                "in_3000_3500" -> return "3778 рублей 80 копеек"
                                "more_3500" -> return "5989 рублей 50 копеек"
                            }
                        }
                        2 -> {
                            when (legk_car_gibrid_capacity) {
                                "less_1000" -> return "5771 рубль 70 копеек"
                                "in_1000_2000" -> return "8995 рублей 10 копеек"
                                "in_2000_3000" -> return "17554 рубля 70 копеек"
                                "in_3000_3500" -> return "31036 рублей 50 копеек"
                                "more_3500" -> return "38125 рублей 90 копеек"
                            }
                        }
                    }
                }
            }
            "gruz_car" -> {
                when (age){
                    1 -> {
                        when (us_gruz_car_weight) {
                            "less_2_5t" -> return "4083 рубля 70 копеек"
                            "2_5_3_5t" -> return "6534 рубля"
                            "3_5_5t" -> return "8167 рублей 50 копеек"
                            "5_8t" -> return "8984 рубля 20 копеек"
                            "8_12t" -> return "10944 рубля 40 копеек"
                            "12_20t" -> return "12006 рублей 30 копеек"
                            "20_50t" -> return "23685 рублей 70 копеек"
                        }
                    }
                    2 -> {
                        when (us_gruz_car_weight) {
                            "less_2_5t" -> return "7187 рублей 40 копеек"
                            "2_5_3_5t" -> return "10209 рубля 40 копеек"
                            "3_5_5t" -> return "13068 рублей"
                            "5_8t" -> return "37243 рубля 80 копеек"
                            "8_12t" -> return "56437 рублей 50 копеек"
                            "12_20t" -> return "82165 рублей"
                            "20_50t" -> return "96376 рублей 50 копеек"
                        }
                    }
                }
            }
            "bus" -> {
                when (age){
                    1 -> {
                        when (us_bus_engine) {
                            "electro" -> return "4900 рублей 50 копеек"
                            "less_2500" -> return "4900 рублей 50 копеек"
                            "2500_5000" -> return "9801 рубль"
                            "5000_10000" -> return "13068 рублей"
                            "more_10000" -> return "16335 рублей"
                        }
                    }
                    2 -> {
                        when (us_bus_engine) {
                            "electro" -> return "8167 рублей 50 копеек"
                            "less_2500" -> return "8167 рублей 50 копеек"
                            "2500_5000" -> return "24502 рубля 50 копеек"
                            "5000_10000" -> return "35937 рублей"
                            "more_10000" -> return "42471 рубль"
                        }
                    }
                }
            }
            "dumpTruck" -> {
                when (age){
                    1 -> {
                        when (us_dumpTruck_weight) {
                            "50_80t" -> return "111078 рублей"
                            "80_350t" -> return "204187 рублей 50 копеек"
                            "more_350t" -> return "302197 рублей 50 копеек"
                        }
                    }
                    2 -> {
                        when (us_dumpTruck_weight) {
                            "50_80t" -> return "253029 рублей 10 копеек"
                            "80_350t" -> return "261360 рублей"
                            "more_350t" -> return "326700 рублей"
                        }
                    }
                }
            }
            "pricep" -> {
                when (age){
                    1 -> {
                        return "до трех лет сбор не взимается"
                    }
                    2 -> {
                        when (us_pricep_weight) {
                            "more_10t_pricep" -> return "38640 рублей"
                            "more_10t_halfpricep" -> return "38640 рублей"
                        }
                    }
                }
            }
        }
        return "ERROR"
    }

}