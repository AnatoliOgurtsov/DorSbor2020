package by.a_ogurtsov.AutoTaxes.repositories

class RepositoryUtilSbor {

    fun totalAmount(
        kindAuto: String,
        age: Int,
        legkCarGibridCapacity: String,
        usLegkCarWeight: String,
        usGruzCarWeight: String,
        usBusEngine: String,
        usDumptruckWeight: String,
        usPricepWeight: String
    ): String {
        when (kindAuto) {
            "legk_car" -> {
                when (usLegkCarWeight) {
                    "electro" -> when (age) {
                        1 -> return "544 рубля 50 копеек"
                        2 -> return "816 рублей 70 копеек"
                        3 -> return "816 рублей 70 копеек"
                    }
                    "gibrid" -> when (legkCarGibridCapacity) {
                        "less_1000" -> {
                            when (age) {
                                1 -> return "1652 рубля 20 копеек"
                                2 -> return "5771 рубль 70 копеек"
                                3 -> return "8657 рублей 60 копеек"
                            }
                        }
                        "in_1000_2000" -> {
                            when (age) {
                                1 -> return "6115 рублей 20 копеек"
                                2 -> return "8995 рублей 10 копеек"
                                3 -> return "13492 рубля 70 копеек"
                            }
                        }
                        "in_2000_3000" -> {
                            when (age) {
                                1 -> return "9652 рубля 70 копеек"
                                2 -> return "17554 рубля 70 копеек"
                                3 -> return "26332 рубля 10 копеек"
                            }
                        }
                        "in_3000_3500" -> {
                            when (age) {
                                1 -> return "8898 рублей 60 копеек"
                                2 -> return "31036 рублей 50 копеек"
                                3 -> return "46554 рубля 80 копеек"
                            }
                        }
                        "more_3500" -> {
                            when (age) {
                                1 -> return "15253 рубля 70 копеек"
                                2 -> return "38125 рублей 90 копеек"
                                3 -> return "57188 рублей 90 копеек"
                            }
                        }
                    }
                    "fiz", "ees" -> {
                        when (age) {
                            1 -> return "544 рубля 50 копеек"
                            2 -> return "816 рублей 70 копеек"
                            3 -> return "1225 рублей 10 копеек"
                        }
                    }

                }

            }
            "gruz_car" -> {
                when (age){
                    1 -> {
                        when (usGruzCarWeight) {
                            "less_2_5t" -> return "4083 рубля 70 копеек"
                            "2_5_3_5t" -> return "6534 рубля"
                            "3_5_5t" -> return "8167 рублей 50 копеек"
                            "5_8t" -> return "8984 рубля 20 копеек"
                            "8_12t" -> return "10944 рубля 40 копеек"
                            "12_20t" -> return "12006 рублей 30 копеек"
                            "20_30t" -> return "12600 рублей"
                            "30_50t" -> return "23685 рублей 70 копеек"

                        }
                    }
                    2 -> {
                        when (usGruzCarWeight) {
                            "less_2_5t" -> return "7187 рублей 40 копеек"
                            "2_5_3_5t" -> return "10209 рубля 40 копеек"
                            "3_5_5t" -> return "13068 рублей"
                            "5_8t" -> return "37243 рубля 80 копеек"
                            "8_12t" -> return "56437 рублей 50 копеек"
                            "12_20t" -> return "82165 рублей"
                            "20_30t" -> return "86300 рублей"
                            "30_50t" -> return "96376 рублей 50 копеек"
                        }
                    }
                    3 -> {
                        when (usGruzCarWeight) {
                            "less_2_5t" -> return "10781 рубль 10 копеек"
                            "2_5_3_5t" -> return "15314 рублей 10 копеек"
                            "3_5_5t" -> return "19602 рубля"
                            "5_8t" -> return "55865 рублей 70 копеек"
                            "8_12t" -> return "84656 рублей 30 копеек"
                            "12_20t" -> return "123247 рублей 50 копеек"
                            "20_30t" -> return "129450 рублей"
                            "30_50t" -> return "144564 рубля 80 копеек"
                        }
                    }
                }
            }
            "bus" -> {
                when (age){
                    1 -> {
                        when (usBusEngine) {
                            "electro" -> return "4900 рублей 50 копеек"
                            "less_2500" -> return "4900 рублей 50 копеек"
                            "2500_5000" -> return "9801 рубль"
                            "5000_10000" -> return "13068 рублей"
                            "more_10000" -> return "16335 рублей"
                        }
                    }
                    2 -> {
                        when (usBusEngine) {
                            "electro" -> return "8167 рублей 50 копеек"
                            "less_2500" -> return "8167 рублей 50 копеек"
                            "2500_5000" -> return "24502 рубля 50 копеек"
                            "5000_10000" -> return "35937 рублей"
                            "more_10000" -> return "42471 рубль"
                        }
                    }
                    3 -> {
                        when (usBusEngine) {
                            "electro" -> return "8167 рублей 50 копеек"
                            "less_2500" -> return "12251 рубль 30 копеек"
                            "2500_5000" -> return "36753 рубля 80 копеек"
                            "5000_10000" -> return "53905 рублей 50 копеек"
                            "more_10000" -> return "63706 рублей 50 копеек"
                        }
                    }
                }
            }
            "dumpTruck" -> {
                when (age){
                    1 -> {
                        when (usDumptruckWeight) {
                            "50_80t" -> return "111078 рублей"
                            "80_350t" -> return "204187 рублей 50 копеек"
                            "more_350t" -> return "302197 рублей 50 копеек"
                        }
                    }
                    2 -> {
                        when (usDumptruckWeight) {
                            "50_80t" -> return "253029 рублей 10 копеек"
                            "80_350t" -> return "261360 рублей"
                            "more_350t" -> return "326700 рублей"
                        }
                    }
                    3 -> {
                        when (usDumptruckWeight) {
                            "50_80t" -> return "379543 рубля 70 копеек"
                            "80_350t" -> return "392040 рублей"
                            "more_350t" -> return "490050 рублей"
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
                        when (usPricepWeight) {
                            "more_10t_pricep" -> return "38640 рублей"
                            "more_10t_halfpricep" -> return "38640 рублей"
                        }
                    }
                    3 -> {
                        when (usPricepWeight) {
                            "more_10t_pricep" -> return "57960 рублей"
                            "more_10t_halfpricep" -> return "57960 рублей"
                        }
                    }
                }
            }
        }
        return "ERROR"
    }

}