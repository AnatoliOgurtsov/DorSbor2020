package by.a_ogurtsov.AutoTaxes.repositories

import by.a_ogurtsov.AutoTaxes.Utils
import by.a_ogurtsov.MyClass

class RepositoryDorSbor21 {

    fun totalAmount21(
        fizYur: String,
        kindAuto: String,
        weightAuto: String,
        veteran: Int
    ): String {
        when (fizYur) {
            "fiz" -> {
                when (kindAuto) {
                    "legk_car" -> {
                        when (weightAuto) {
                            "less_1_5t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(61.00)
                                    2 -> return MyClass.check_ruble(61.00 / 2)
                                }
                            }
                            "1_5_1_75t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(81.00)
                                    2 -> return MyClass.check_ruble(81.00 / 2)
                                }
                            }
                            "1_75_2t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(102.00)
                                    2 -> return MyClass.check_ruble(102.00 / 2)
                                }
                            }
                            "2_2_25t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(122.00)
                                    2 -> return MyClass.check_ruble(122.00 / 2)
                                }
                            }
                            "2_25_2_5t" -> {
                                return MyClass.check_ruble(142.00)
                            }
                            "2_5_3t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(162.00)
                                    2 -> return MyClass.check_ruble(162.00 / 2)
                                }
                            }
                            "more_3t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(223.00)
                                    2 -> return MyClass.check_ruble(223.00 / 2)
                                }
                            }
                        }
                    }
                    "gruz_car" -> {
                        when (weightAuto) {
                            "less_2_5t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(162.00)
                                    2 -> return MyClass.check_ruble(162.00 / 2)
                                }
                            }
                            "2_5_3_5t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(345.00)
                                    2 -> return MyClass.check_ruble(345.00 / 2)
                                }
                            }
                            "3_5_12t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(447.00)
                                    2 -> return MyClass.check_ruble(447.00 / 2)
                                }
                            }
                            "more_12t" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(558.00)
                                    2 -> return MyClass.check_ruble(558.00 / 2)
                                }
                            }
                            "semi_trailer" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(406.00)
                                    2 -> return MyClass.check_ruble(406.00 / 2)
                                }
                            }
                        }
                    }
                    "bus" -> {
                        when (weightAuto) {
                            "less_20" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(244.00)
                                    2 -> return MyClass.check_ruble(244.00 / 2)
                                }
                            }
                            "20_40" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(345.00)
                                    2 -> return MyClass.check_ruble(345.00 / 2)
                                }
                            }
                            "more_40" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(447.00)
                                    2 -> return MyClass.check_ruble(447.00 / 2)
                                }
                            }
                        }
                    }
                    "pricep" -> {
                        when (weightAuto) {
                            "less_0_75" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(31.00)
                                    2 -> return MyClass.check_ruble(31.00 / 2)
                                }
                            }
                            "more_0_75" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(223.00)
                                    2 -> return MyClass.check_ruble(223.00 / 2)
                                }
                            }
                            "dacha" -> {
                                when (veteran) {
                                    1 -> return MyClass.check_ruble(41.00)
                                    2 -> return MyClass.check_ruble(41.00 / 2)
                                }
                            }
                        }
                    }
                    "motocycle" -> {
                        when (veteran) {
                            1 -> return MyClass.check_ruble(41.00)
                            2 -> return MyClass.check_ruble(41.00 / 2)
                        }
                    }
                }
            }
            //==============================================================================
            "yur" -> {
                when (kindAuto) {
                    "legk_car" -> {
                        when (weightAuto) {
                            "less_1t_yur" -> {
                                return MyClass.check_ruble(162.00)
                            }
                            "1_2t_yur" -> {
                                return MyClass.check_ruble(209.00)
                            }
                            "2_3t_yur" -> {
                                return MyClass.check_ruble(255.00)
                            }
                            "more_3t_yur" -> {
                                return MyClass.check_ruble(325.00)
                            }
                        }
                    }
                    "gruz_car" -> {
                        when (weightAuto) {
                            "less_2_5t" -> {
                                return MyClass.check_ruble(278.00)
                            }
                            "2_5_3_5t" -> {
                                return MyClass.check_ruble(394.00)
                            }
                            "3_5_12t" -> {
                                return MyClass.check_ruble(510.00)
                            }
                            "more_12t" -> {
                                return MyClass.check_ruble(580.00)
                            }
                            "semi_trailer" -> {
                                return MyClass.check_ruble(464.00)
                            }
                        }
                    }
                    "bus" -> {
                        when (weightAuto) {
                            "less_20" -> {
                                return MyClass.check_ruble(278.00)
                            }
                            "20_40" -> {
                                return MyClass.check_ruble(394.00)
                            }
                            "more_40" -> {
                                return MyClass.check_ruble(510.00)
                            }
                        }
                    }
                    "pricep" -> {
                        when (weightAuto) {
                            "less_0_75" -> {
                                return MyClass.check_ruble(116.00)
                            }
                            "more_0_75" -> {
                                return MyClass.check_ruble(278.00)
                            }
                            "dacha" -> {
                                return MyClass.check_ruble(116.00)
                            }
                        }
                    }
                    "motocycle" -> {
                        return MyClass.check_ruble(70.00)
                    }
                }
            }
        }
        return "ERROR"
    }
}