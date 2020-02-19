package by.a_ogurtsov.AutoTaxes.repositories

import by.a_ogurtsov.AutoTaxes.Utils
import by.a_ogurtsov.MyClass

class RepositoryDorSbor {
    private val baseValue: Double = Utils().baseValue
    var numberOfBaseValue: Int = 0

    fun totalAmount(
        period: String,
        fizYur: String,
        age: Int,
        kindAuto: String,
        weightAuto: String,
        veteran: Int
    ): MutableList<String> {
        when (fizYur) {
            "fiz" -> {
                when (kindAuto) {
                    "legk_car" -> {
                        when (weightAuto) {
                            "less_1_5t" -> {
                                numberOfBaseValue = 3
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "1_5_2t" -> {
                                numberOfBaseValue = 6
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "2_3t" -> {
                                numberOfBaseValue = 8
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "more_3t" -> {
                                numberOfBaseValue = 11
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                        } // end weightAuto Legk fiz
                    }
                    "gruz_car" -> {
                        val age_gruz = 1
                        when (weightAuto) {
                            "less_2_5t" -> {
                                numberOfBaseValue = 8
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "2_5_3_5t" -> {
                                numberOfBaseValue = 17
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "3_5_12t" -> {
                                numberOfBaseValue = 22
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "more_12t" -> {
                                numberOfBaseValue = 25
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "semi_trailer" -> {
                                numberOfBaseValue = 20
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                        } // end weightAuto Gruz fiz

                    }
                    "bus" -> {
                        val age_bus = 1
                        when (weightAuto) {
                            "less_20" -> {
                                numberOfBaseValue = 12
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_bus * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_bus, veteran)
                                )
                            }
                            "20_40" -> {
                                numberOfBaseValue = 17
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_bus * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_bus, veteran)
                                )
                            }
                            "more_40" -> {
                                numberOfBaseValue = 22
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_bus * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_bus, veteran)
                                )
                            }
                        }  // end weightAuto Bus fiz
                    }
                    "pricep" -> {
                        when (weightAuto) {
                            "less_0_75" -> {
                                numberOfBaseValue = 2
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "more_0_75" -> {
                                numberOfBaseValue = 11
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "dacha" -> {
                                numberOfBaseValue = 5
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                        }  // end weightAuto Pricep fiz
                    }
                    "motocycle" -> {
                        numberOfBaseValue = 2
                        return mutableListOf(
                            "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                            countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                        )
                    }

                } // end kindAuto
            } // end fiz
            //==================================================================================
            //==================================================================================
            "yur" -> {
                when (kindAuto) {
                    "legk_car" -> {
                        when (weightAuto) {
                            "less_1_5t" -> {
                                numberOfBaseValue = 7
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "1_5_2t" -> {
                                numberOfBaseValue = 9
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "2_3t" -> {
                                numberOfBaseValue = 11
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "more_3t" -> {
                                numberOfBaseValue = 14
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                        } // end weightAuto Legk yur
                    }
                    "gruz_car" -> {
                        val age_gruz = 1
                        when (weightAuto) {
                            "less_2_5t" -> {
                                numberOfBaseValue = 12
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "2_5_3_5t" -> {
                                numberOfBaseValue = 17
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "3_5_12t" -> {
                                numberOfBaseValue = 22
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "more_12t" -> {
                                numberOfBaseValue = 25
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                            "semi_trailer" -> {
                                numberOfBaseValue = 20
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_gruz * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_gruz, veteran)
                                )
                            }
                        } // end weightAuto Gruz yur

                    }
                    "bus" -> {
                        val age_bus = 1
                        when (weightAuto) {
                            "less_20" -> {
                                numberOfBaseValue = 12
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_bus * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_bus, veteran)
                                )
                            }
                            "20_40" -> {
                                numberOfBaseValue = 17
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_bus * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_bus, veteran)
                                )
                            }
                            "more_40" -> {
                                numberOfBaseValue = 22
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age_bus * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age_bus, veteran)
                                )
                            }
                        }  // end weightAuto Bus yur
                    }
                    "pricep" -> {
                        when (weightAuto) {
                            "less_0_75" -> {
                                numberOfBaseValue = 5
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "more_0_75" -> {
                                numberOfBaseValue = 12
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                            "dacha" -> {
                                numberOfBaseValue = 7
                                return mutableListOf(
                                    "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                                    countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                                )
                            }
                        }  // end weightAuto Pricep yur
                    }
                    "motocycle" -> {
                        numberOfBaseValue = 3
                        return mutableListOf(
                            "${MyClass.check_ruble((numberOfBaseValue * age * baseValue) / veteran)}",
                            countNumberOfBaseValue(numberOfBaseValue, age, veteran)
                        )
                    }
                } // end kindAuto
            } // end yur
        } // end fizYur


        return mutableListOf(
            "${"$period + $fizYur + $age + $kindAuto + $weightAuto + $veteran"}",
            "${3 * age}  ${sclonenie(3 * age)}"
        )

    }


    private fun sclonenie(n: Int): String {
        var s = ""
        when (n) {
            1 -> s = " базовая величина"
            2, 3, 4, 22, 23, 24, 34, 44 -> s = " базовых величины"
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 25, 28, 50, 45, 55 -> s =
                " базовых величин"
            else -> s = "б.в."
        }
        return s
    }


    private fun countNumberOfBaseValue(numberOfBaseValue: Int?, age: Int?, veteran: Int?): String {
        val temp = numberOfBaseValue!! * age!!
        val temp1: Int?
        val temp2: Float?
        if (temp % veteran!! == 0) {
            temp1 = temp / veteran
            return temp1.toString() + " " + sclonenie(temp1)
        } else {
            temp2 = temp.toFloat() / veteran
            return "$temp2  базовой величины"
        }
    }

}