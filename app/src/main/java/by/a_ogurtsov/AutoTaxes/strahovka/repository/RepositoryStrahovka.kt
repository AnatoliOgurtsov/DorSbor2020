package by.a_ogurtsov.AutoTaxes.strahovka.repository

import android.content.Context
import android.util.Log
import by.a_ogurtsov.AutoTaxes.LOG_TAG
import by.a_ogurtsov.AutoTaxes.R
import by.a_ogurtsov.MyClass
import java.math.BigDecimal
import java.math.RoundingMode

class RepositoryStrahovka {

    private var total: Float = 0.0F

    fun totalEuroValue(
        context: Context?,
        k1: Float,
        autoKind: String,
        autoKindLegkRusDetails: String,
        autoKindLegkDetails: String,
        autoKindElectroGibridDetails: String,
        autoKindLegkPricepDetails: String,
        autoKindGruzDetails: String,
        autoKindTractorDetails: String,
        autoKindGruzPricepDetails: String,
        autoKindMotoDetails: String,
        autoKindBusDetails: String,
        vozrastStazh: String,
        term: String
    ): String {


        //==============================================calculate k3
        val k3: Float = when (vozrastStazh) {
            context?.resources?.getString(R.string.title_button_strahovka_vozrast_stazh_less25_less2) -> 1.3F
            context?.resources?.getString(R.string.title_button_strahovka_vozrast_stazh_less25_more2) -> 1.1F
            context?.resources?.getString(R.string.title_button_strahovka_vozrast_stazh_more25_less2) -> 1.2F
            context?.resources?.getString(R.string.title_button_strahovka_vozrast_stazh_more25_more2) -> 1.0F
            else -> Log.d(LOG_TAG, "somethings wrong with vozrastStazh").toFloat()
        }

        //==============================================calculate insurance rate "t"

        val t: Float = when (autoKind) {
            context?.resources?.getString(R.string.title_button_legk_car_russia) -> {
                when (autoKindLegkRusDetails) {
                    context.resources?.getString(R.string.title_button_less1200_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.1F
                            context.resources?.getString(R.string.title_button_month1) -> 2.1F
                            context.resources?.getString(R.string.title_button_month2) -> 4.0F
                            context.resources?.getString(R.string.title_button_month3) -> 5.5F
                            context.resources?.getString(R.string.title_button_month4) -> 6.8F
                            context.resources?.getString(R.string.title_button_month5) -> 7.9F
                            context.resources?.getString(R.string.title_button_month6) -> 8.9F
                            context.resources?.getString(R.string.title_button_month7) -> 9.7F
                            context.resources?.getString(R.string.title_button_month8) -> 10.3F
                            context.resources?.getString(R.string.title_button_month9) -> 10.9F
                            context.resources?.getString(R.string.title_button_month10) -> 11.4F
                            context.resources?.getString(R.string.title_button_month11) -> 11.8F
                            context.resources?.getString(R.string.title_button_month12) -> 12.2F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_in_1200_1800_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.4F
                            context.resources?.getString(R.string.title_button_month1) -> 2.7F
                            context.resources?.getString(R.string.title_button_month2) -> 5.0F
                            context.resources?.getString(R.string.title_button_month3) -> 6.9F
                            context.resources?.getString(R.string.title_button_month4) -> 8.5F
                            context.resources?.getString(R.string.title_button_month5) -> 9.9F
                            context.resources?.getString(R.string.title_button_month6) -> 11.1F
                            context.resources?.getString(R.string.title_button_month7) -> 12.1F
                            context.resources?.getString(R.string.title_button_month8) -> 13.0F
                            context.resources?.getString(R.string.title_button_month9) -> 13.7F
                            context.resources?.getString(R.string.title_button_month10) -> 14.3F
                            context.resources?.getString(R.string.title_button_month11) -> 14.8F
                            context.resources?.getString(R.string.title_button_month12) -> 15.3F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_in_1800_2500_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.7F
                            context.resources?.getString(R.string.title_button_month1) -> 3.4F
                            context.resources?.getString(R.string.title_button_month2) -> 6.2F
                            context.resources?.getString(R.string.title_button_month3) -> 8.6F
                            context.resources?.getString(R.string.title_button_month4) -> 10.7F
                            context.resources?.getString(R.string.title_button_month5) -> 12.4F
                            context.resources?.getString(R.string.title_button_month6) -> 13.9F
                            context.resources?.getString(R.string.title_button_month7) -> 15.1F
                            context.resources?.getString(R.string.title_button_month8) -> 16.2F
                            context.resources?.getString(R.string.title_button_month9) -> 17.1F
                            context.resources?.getString(R.string.title_button_month10) -> 17.8F
                            context.resources?.getString(R.string.title_button_month11) -> 18.5F
                            context.resources?.getString(R.string.title_button_month12) -> 19.1F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_in_2500_3500_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 2.1F
                            context.resources?.getString(R.string.title_button_month1) -> 4.0F
                            context.resources?.getString(R.string.title_button_month2) -> 7.5F
                            context.resources?.getString(R.string.title_button_month3) -> 10.4F
                            context.resources?.getString(R.string.title_button_month4) -> 12.8F
                            context.resources?.getString(R.string.title_button_month5) -> 14.9F
                            context.resources?.getString(R.string.title_button_month6) -> 16.7F
                            context.resources?.getString(R.string.title_button_month7) -> 18.2F
                            context.resources?.getString(R.string.title_button_month8) -> 19.5F
                            context.resources?.getString(R.string.title_button_month9) -> 20.6F
                            context.resources?.getString(R.string.title_button_month10) -> 21.5F
                            context.resources?.getString(R.string.title_button_month11) -> 22.3F
                            context.resources?.getString(R.string.title_button_month12) -> 23.0F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_more_3500_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 2.5F
                            context.resources?.getString(R.string.title_button_month1) -> 4.9F
                            context.resources?.getString(R.string.title_button_month2) -> 9.0F
                            context.resources?.getString(R.string.title_button_month3) -> 12.5F
                            context.resources?.getString(R.string.title_button_month4) -> 15.4F
                            context.resources?.getString(R.string.title_button_month5) -> 17.9F
                            context.resources?.getString(R.string.title_button_month6) -> 20.0F
                            context.resources?.getString(R.string.title_button_month7) -> 21.9F
                            context.resources?.getString(R.string.title_button_month8) -> 23.4F
                            context.resources?.getString(R.string.title_button_month9) -> 24.7F
                            context.resources?.getString(R.string.title_button_month10) -> 25.8F
                            context.resources?.getString(R.string.title_button_month11) -> 26.7F
                            context.resources?.getString(R.string.title_button_month12) -> 27.6F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_taxi) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 9.7F
                            context.resources?.getString(R.string.title_button_month1) -> 18.7F
                            context.resources?.getString(R.string.title_button_month2) -> 34.5F
                            context.resources?.getString(R.string.title_button_month3) -> 47.9F
                            context.resources?.getString(R.string.title_button_month4) -> 59.2F
                            context.resources?.getString(R.string.title_button_month5) -> 68.9F
                            context.resources?.getString(R.string.title_button_month6) -> 77.1F
                            context.resources?.getString(R.string.title_button_month7) -> 87.0F
                            context.resources?.getString(R.string.title_button_month8) -> 89.9F
                            context.resources?.getString(R.string.title_button_month9) -> 94.9F
                            context.resources?.getString(R.string.title_button_month10) -> 99.1F
                            context.resources?.getString(R.string.title_button_month11) -> 102.7F
                            context.resources?.getString(R.string.title_button_month12) -> 106.4F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindLegkRusDetails").toFloat()
                }
            }
            //===============================================================
            // car not russia
            //===============================================================

            context?.resources?.getString(R.string.title_button_legk_car_not_russia) -> {
                when (autoKindLegkDetails) {
                    context.resources?.getString(R.string.title_button_less1200_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.7F
                            context.resources?.getString(R.string.title_button_month1) -> 3.4F
                            context.resources?.getString(R.string.title_button_month2) -> 6.0F
                            context.resources?.getString(R.string.title_button_month3) -> 8.5F
                            context.resources?.getString(R.string.title_button_month4) -> 10.5F
                            context.resources?.getString(R.string.title_button_month5) -> 12.2F
                            context.resources?.getString(R.string.title_button_month6) -> 13.7F
                            context.resources?.getString(R.string.title_button_month7) -> 14.9F
                            context.resources?.getString(R.string.title_button_month8) -> 16.0F
                            context.resources?.getString(R.string.title_button_month9) -> 16.7F
                            context.resources?.getString(R.string.title_button_month10) -> 17.5F
                            context.resources?.getString(R.string.title_button_month11) -> 18.2F
                            context.resources?.getString(R.string.title_button_month12) -> 18.8F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_in_1200_1800_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 2.1F
                            context.resources?.getString(R.string.title_button_month1) -> 4.2F
                            context.resources?.getString(R.string.title_button_month2) -> 7.6F
                            context.resources?.getString(R.string.title_button_month3) -> 10.6F
                            context.resources?.getString(R.string.title_button_month4) -> 13.2F
                            context.resources?.getString(R.string.title_button_month5) -> 15.3F
                            context.resources?.getString(R.string.title_button_month6) -> 17.2F
                            context.resources?.getString(R.string.title_button_month7) -> 18.6F
                            context.resources?.getString(R.string.title_button_month8) -> 20.1F
                            context.resources?.getString(R.string.title_button_month9) -> 21.0F
                            context.resources?.getString(R.string.title_button_month10) -> 21.9F
                            context.resources?.getString(R.string.title_button_month11) -> 22.9F
                            context.resources?.getString(R.string.title_button_month12) -> 23.6F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_in_1800_2500_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 2.6F
                            context.resources?.getString(R.string.title_button_month1) -> 5.3F
                            context.resources?.getString(R.string.title_button_month2) -> 9.4F
                            context.resources?.getString(R.string.title_button_month3) -> 13.2F
                            context.resources?.getString(R.string.title_button_month4) -> 16.5F
                            context.resources?.getString(R.string.title_button_month5) -> 19.1F
                            context.resources?.getString(R.string.title_button_month6) -> 21.5F
                            context.resources?.getString(R.string.title_button_month7) -> 23.2F
                            context.resources?.getString(R.string.title_button_month8) -> 25.0F
                            context.resources?.getString(R.string.title_button_month9) -> 26.2F
                            context.resources?.getString(R.string.title_button_month10) -> 27.3F
                            context.resources?.getString(R.string.title_button_month11) -> 28.5F
                            context.resources?.getString(R.string.title_button_month12) -> 29.4F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_in_2500_3500_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 3.9F
                            context.resources?.getString(R.string.title_button_month1) -> 7.6F
                            context.resources?.getString(R.string.title_button_month2) -> 14.0F
                            context.resources?.getString(R.string.title_button_month3) -> 19.4F
                            context.resources?.getString(R.string.title_button_month4) -> 24.1F
                            context.resources?.getString(R.string.title_button_month5) -> 28.0F
                            context.resources?.getString(R.string.title_button_month6) -> 31.3F
                            context.resources?.getString(R.string.title_button_month7) -> 34.1F
                            context.resources?.getString(R.string.title_button_month8) -> 36.5F
                            context.resources?.getString(R.string.title_button_month9) -> 38.5F
                            context.resources?.getString(R.string.title_button_month10) -> 40.3F
                            context.resources?.getString(R.string.title_button_month11) -> 41.7F
                            context.resources?.getString(R.string.title_button_month12) -> 43.1F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_more_3500_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.6F
                            context.resources?.getString(R.string.title_button_month1) -> 8.9F
                            context.resources?.getString(R.string.title_button_month2) -> 16.5F
                            context.resources?.getString(R.string.title_button_month3) -> 23.0F
                            context.resources?.getString(R.string.title_button_month4) -> 28.6F
                            context.resources?.getString(R.string.title_button_month5) -> 33.0F
                            context.resources?.getString(R.string.title_button_month6) -> 37.0F
                            context.resources?.getString(R.string.title_button_month7) -> 40.3F
                            context.resources?.getString(R.string.title_button_month8) -> 43.1F
                            context.resources?.getString(R.string.title_button_month9) -> 45.5F
                            context.resources?.getString(R.string.title_button_month10) -> 47.5F
                            context.resources?.getString(R.string.title_button_month11) -> 49.3F
                            context.resources?.getString(R.string.title_button_month12) -> 50.9F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_taxi) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 9.7F
                            context.resources?.getString(R.string.title_button_month1) -> 18.7F
                            context.resources?.getString(R.string.title_button_month2) -> 34.5F
                            context.resources?.getString(R.string.title_button_month3) -> 47.9F
                            context.resources?.getString(R.string.title_button_month4) -> 59.2F
                            context.resources?.getString(R.string.title_button_month5) -> 68.9F
                            context.resources?.getString(R.string.title_button_month6) -> 77.1F
                            context.resources?.getString(R.string.title_button_month7) -> 87.0F
                            context.resources?.getString(R.string.title_button_month8) -> 89.9F
                            context.resources?.getString(R.string.title_button_month9) -> 94.9F
                            context.resources?.getString(R.string.title_button_month10) -> 99.1F
                            context.resources?.getString(R.string.title_button_month11) -> 102.7F
                            context.resources?.getString(R.string.title_button_month12) -> 106.4F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindLegkDetails").toFloat()
                }
            }
            //===============================================================
            // electro and gibrid
            //===============================================================

            context?.resources?.getString(R.string.title_button_electro_gibrid_strahovka) -> {
                when (autoKindElectroGibridDetails) {
                    context.resources?.getString(R.string.title_button_gibrid) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 3.0F
                            context.resources?.getString(R.string.title_button_month1) -> 5.9F
                            context.resources?.getString(R.string.title_button_month2) -> 10.7F
                            context.resources?.getString(R.string.title_button_month3) -> 14.9F
                            context.resources?.getString(R.string.title_button_month4) -> 18.5F
                            context.resources?.getString(R.string.title_button_month5) -> 21.5F
                            context.resources?.getString(R.string.title_button_month6) -> 24.1F
                            context.resources?.getString(R.string.title_button_month7) -> 26.2F
                            context.resources?.getString(R.string.title_button_month8) -> 28.1F
                            context.resources?.getString(R.string.title_button_month9) -> 29.6F
                            context.resources?.getString(R.string.title_button_month10) -> 30.9F
                            context.resources?.getString(R.string.title_button_month11) -> 32.1F
                            context.resources?.getString(R.string.title_button_month12) -> 33.2F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_electro_strahovka) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 2.1F
                            context.resources?.getString(R.string.title_button_month1) -> 4.3F
                            context.resources?.getString(R.string.title_button_month2) -> 7.7F
                            context.resources?.getString(R.string.title_button_month3) -> 10.8F
                            context.resources?.getString(R.string.title_button_month4) -> 13.4F
                            context.resources?.getString(R.string.title_button_month5) -> 15.5F
                            context.resources?.getString(R.string.title_button_month6) -> 17.5F
                            context.resources?.getString(R.string.title_button_month7) -> 18.9F
                            context.resources?.getString(R.string.title_button_month8) -> 20.4F
                            context.resources?.getString(R.string.title_button_month9) -> 21.3F
                            context.resources?.getString(R.string.title_button_month10) -> 22.2F
                            context.resources?.getString(R.string.title_button_month11) -> 23.2F
                            context.resources?.getString(R.string.title_button_month12) -> 23.9F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindElectroGibridDetails").toFloat()
                }
            }
            //===============================================================
            // legk pricep
            //===============================================================

            context?.resources?.getString(R.string.title_button_legk_car_pricep) -> {
                when (autoKindLegkPricepDetails) {
                    context.resources?.getString(R.string.title_button_legk_pricep_b1) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.3F
                            context.resources?.getString(R.string.title_button_month1) -> 0.5F
                            context.resources?.getString(R.string.title_button_month2) -> 0.9F
                            context.resources?.getString(R.string.title_button_month3) -> 1.3F
                            context.resources?.getString(R.string.title_button_month4) -> 1.6F
                            context.resources?.getString(R.string.title_button_month5) -> 1.9F
                            context.resources?.getString(R.string.title_button_month6) -> 2.1F
                            context.resources?.getString(R.string.title_button_month7) -> 2.3F
                            context.resources?.getString(R.string.title_button_month8) -> 2.5F
                            context.resources?.getString(R.string.title_button_month9) -> 2.6F
                            context.resources?.getString(R.string.title_button_month10) -> 2.7F
                            context.resources?.getString(R.string.title_button_month11) -> 2.8F
                            context.resources?.getString(R.string.title_button_month12) -> 2.9F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_legk_pricep_b2) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.5F
                            context.resources?.getString(R.string.title_button_month1) -> 1.0F
                            context.resources?.getString(R.string.title_button_month2) -> 1.9F
                            context.resources?.getString(R.string.title_button_month3) -> 2.6F
                            context.resources?.getString(R.string.title_button_month4) -> 3.2F
                            context.resources?.getString(R.string.title_button_month5) -> 3.7F
                            context.resources?.getString(R.string.title_button_month6) -> 4.1F
                            context.resources?.getString(R.string.title_button_month7) -> 4.5F
                            context.resources?.getString(R.string.title_button_month8) -> 4.8F
                            context.resources?.getString(R.string.title_button_month9) -> 5.1F
                            context.resources?.getString(R.string.title_button_month10) -> 5.3F
                            context.resources?.getString(R.string.title_button_month11) -> 5.5F
                            context.resources?.getString(R.string.title_button_month12) -> 5.7F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindLegkPricepDetails").toFloat()
                }

            }

            //===============================================================
            // gruz car
            //===============================================================

            context?.resources?.getString(R.string.title_button_gruz_car_strahovka) -> {

                when (autoKindGruzDetails) {
                    context.resources?.getString(R.string.title_button_gruz_less_1_t_С0) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 2.4F
                            context.resources?.getString(R.string.title_button_month1) -> 4.6F
                            context.resources?.getString(R.string.title_button_month2) -> 8.5F
                            context.resources?.getString(R.string.title_button_month3) -> 11.9F
                            context.resources?.getString(R.string.title_button_month4) -> 14.7F
                            context.resources?.getString(R.string.title_button_month5) -> 17.1F
                            context.resources?.getString(R.string.title_button_month6) -> 19.1F
                            context.resources?.getString(R.string.title_button_month7) -> 20.8F
                            context.resources?.getString(R.string.title_button_month8) -> 22.3F
                            context.resources?.getString(R.string.title_button_month9) -> 23.5F
                            context.resources?.getString(R.string.title_button_month10) -> 24.6F
                            context.resources?.getString(R.string.title_button_month11) -> 25.5F
                            context.resources?.getString(R.string.title_button_month12) -> 26.3F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_in_1_t_2_t_С1) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 3.7F
                            context.resources?.getString(R.string.title_button_month1) -> 7.2F
                            context.resources?.getString(R.string.title_button_month2) -> 13.3F
                            context.resources?.getString(R.string.title_button_month3) -> 18.5F
                            context.resources?.getString(R.string.title_button_month4) -> 22.9F
                            context.resources?.getString(R.string.title_button_month5) -> 26.6F
                            context.resources?.getString(R.string.title_button_month6) -> 29.8F
                            context.resources?.getString(R.string.title_button_month7) -> 32.5F
                            context.resources?.getString(R.string.title_button_month8) -> 34.7F
                            context.resources?.getString(R.string.title_button_month9) -> 36.7F
                            context.resources?.getString(R.string.title_button_month10) -> 38.3F
                            context.resources?.getString(R.string.title_button_month11) -> 39.7F
                            context.resources?.getString(R.string.title_button_month12) -> 41.0F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_in_2_t_8_t_С2) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.0F
                            context.resources?.getString(R.string.title_button_month1) -> 7.7F
                            context.resources?.getString(R.string.title_button_month2) -> 14.3F
                            context.resources?.getString(R.string.title_button_month3) -> 19.9F
                            context.resources?.getString(R.string.title_button_month4) -> 24.6F
                            context.resources?.getString(R.string.title_button_month5) -> 28.6F
                            context.resources?.getString(R.string.title_button_month6) -> 32.0F
                            context.resources?.getString(R.string.title_button_month7) -> 34.8F
                            context.resources?.getString(R.string.title_button_month8) -> 37.3F
                            context.resources?.getString(R.string.title_button_month9) -> 39.3F
                            context.resources?.getString(R.string.title_button_month10) -> 41.1F
                            context.resources?.getString(R.string.title_button_month11) -> 42.6F
                            context.resources?.getString(R.string.title_button_month12) -> 44.0F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_in_8_t_15_t_С3) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.3F
                            context.resources?.getString(R.string.title_button_month1) -> 8.3F
                            context.resources?.getString(R.string.title_button_month2) -> 15.3F
                            context.resources?.getString(R.string.title_button_month3) -> 21.2F
                            context.resources?.getString(R.string.title_button_month4) -> 26.2F
                            context.resources?.getString(R.string.title_button_month5) -> 30.5F
                            context.resources?.getString(R.string.title_button_month6) -> 34.1F
                            context.resources?.getString(R.string.title_button_month7) -> 37.2F
                            context.resources?.getString(R.string.title_button_month8) -> 39.8F
                            context.resources?.getString(R.string.title_button_month9) -> 42.0F
                            context.resources?.getString(R.string.title_button_month10) -> 43.9F
                            context.resources?.getString(R.string.title_button_month11) -> 45.5F
                            context.resources?.getString(R.string.title_button_month12) -> 47.0F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_in_15_t_25_t_С4) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.5F
                            context.resources?.getString(R.string.title_button_month1) -> 8.7F
                            context.resources?.getString(R.string.title_button_month2) -> 16.0F
                            context.resources?.getString(R.string.title_button_month3) -> 22.3F
                            context.resources?.getString(R.string.title_button_month4) -> 27.6F
                            context.resources?.getString(R.string.title_button_month5) -> 32.1F
                            context.resources?.getString(R.string.title_button_month6) -> 35.9F
                            context.resources?.getString(R.string.title_button_month7) -> 39.1F
                            context.resources?.getString(R.string.title_button_month8) -> 41.9F
                            context.resources?.getString(R.string.title_button_month9) -> 44.2F
                            context.resources?.getString(R.string.title_button_month10) -> 46.1F
                            context.resources?.getString(R.string.title_button_month11) -> 47.8F
                            context.resources?.getString(R.string.title_button_month12) -> 49.4F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_more_25_t_С5) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.7F
                            context.resources?.getString(R.string.title_button_month1) -> 9.1F
                            context.resources?.getString(R.string.title_button_month2) -> 16.8F
                            context.resources?.getString(R.string.title_button_month3) -> 23.4F
                            context.resources?.getString(R.string.title_button_month4) -> 28.9F
                            context.resources?.getString(R.string.title_button_month5) -> 33.6F
                            context.resources?.getString(R.string.title_button_month6) -> 37.6F
                            context.resources?.getString(R.string.title_button_month7) -> 41.0F
                            context.resources?.getString(R.string.title_button_month8) -> 43.9F
                            context.resources?.getString(R.string.title_button_month9) -> 46.3F
                            context.resources?.getString(R.string.title_button_month10) -> 48.4F
                            context.resources?.getString(R.string.title_button_month11) -> 50.1F
                            context.resources?.getString(R.string.title_button_month12) -> 51.8F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_trailer_С6) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.6F
                            context.resources?.getString(R.string.title_button_month1) -> 9.0F
                            context.resources?.getString(R.string.title_button_month2) -> 16.6F
                            context.resources?.getString(R.string.title_button_month3) -> 23.0F
                            context.resources?.getString(R.string.title_button_month4) -> 28.5F
                            context.resources?.getString(R.string.title_button_month5) -> 33.1F
                            context.resources?.getString(R.string.title_button_month6) -> 37.0F
                            context.resources?.getString(R.string.title_button_month7) -> 40.4F
                            context.resources?.getString(R.string.title_button_month8) -> 43.2F
                            context.resources?.getString(R.string.title_button_month9) -> 45.6F
                            context.resources?.getString(R.string.title_button_month10) -> 47.6F
                            context.resources?.getString(R.string.title_button_month11) -> 49.4F
                            context.resources?.getString(R.string.title_button_month12) -> 51.0F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindGruzDetails").toFloat()
                }
            }

            //===============================================================
            // tractor
            //===============================================================

            context?.resources?.getString(R.string.title_button_tractor) -> {
                when (autoKindTractorDetails) {
                    context.resources?.getString(R.string.title_button_tractor_less_50_V1) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.5F
                            context.resources?.getString(R.string.title_button_month1) -> 0.9F
                            context.resources?.getString(R.string.title_button_month2) -> 1.7F
                            context.resources?.getString(R.string.title_button_month3) -> 2.3F
                            context.resources?.getString(R.string.title_button_month4) -> 2.9F
                            context.resources?.getString(R.string.title_button_month5) -> 3.4F
                            context.resources?.getString(R.string.title_button_month6) -> 3.8F
                            context.resources?.getString(R.string.title_button_month7) -> 4.1F
                            context.resources?.getString(R.string.title_button_month8) -> 4.4F
                            context.resources?.getString(R.string.title_button_month9) -> 4.6F
                            context.resources?.getString(R.string.title_button_month10) -> 4.9F
                            context.resources?.getString(R.string.title_button_month11) -> 5.0F
                            context.resources?.getString(R.string.title_button_month12) -> 5.2F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_tractor_шт_50_200_V2) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.0F
                            context.resources?.getString(R.string.title_button_month1) -> 1.9F
                            context.resources?.getString(R.string.title_button_month2) -> 3.6F
                            context.resources?.getString(R.string.title_button_month3) -> 5.0F
                            context.resources?.getString(R.string.title_button_month4) -> 6.1F
                            context.resources?.getString(R.string.title_button_month5) -> 7.1F
                            context.resources?.getString(R.string.title_button_month6) -> 8.0F
                            context.resources?.getString(R.string.title_button_month7) -> 8.7F
                            context.resources?.getString(R.string.title_button_month8) -> 9.3F
                            context.resources?.getString(R.string.title_button_month9) -> 9.8F
                            context.resources?.getString(R.string.title_button_month10) -> 10.3F
                            context.resources?.getString(R.string.title_button_month11) -> 10.6F
                            context.resources?.getString(R.string.title_button_month12) -> 11.0F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_tractor_more_200_V3) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.5F
                            context.resources?.getString(R.string.title_button_month1) -> 2.9F
                            context.resources?.getString(R.string.title_button_month2) -> 5.4F
                            context.resources?.getString(R.string.title_button_month3) -> 7.5F
                            context.resources?.getString(R.string.title_button_month4) -> 9.3F
                            context.resources?.getString(R.string.title_button_month5) -> 10.8F
                            context.resources?.getString(R.string.title_button_month6) -> 12.1F
                            context.resources?.getString(R.string.title_button_month7) -> 13.2F
                            context.resources?.getString(R.string.title_button_month8) -> 14.1F
                            context.resources?.getString(R.string.title_button_month9) -> 14.9F
                            context.resources?.getString(R.string.title_button_month10) -> 15.6F
                            context.resources?.getString(R.string.title_button_month11) -> 16.2F
                            context.resources?.getString(R.string.title_button_month12) -> 16.7F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_tractor_crawler_M) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.5F
                            context.resources?.getString(R.string.title_button_month1) -> 1.0F
                            context.resources?.getString(R.string.title_button_month2) -> 1.9F
                            context.resources?.getString(R.string.title_button_month3) -> 2.6F
                            context.resources?.getString(R.string.title_button_month4) -> 3.2F
                            context.resources?.getString(R.string.title_button_month5) -> 3.7F
                            context.resources?.getString(R.string.title_button_month6) -> 4.1F
                            context.resources?.getString(R.string.title_button_month7) -> 4.5F
                            context.resources?.getString(R.string.title_button_month8) -> 4.8F
                            context.resources?.getString(R.string.title_button_month9) -> 5.1F
                            context.resources?.getString(R.string.title_button_month10) -> 5.3F
                            context.resources?.getString(R.string.title_button_month11) -> 5.5F
                            context.resources?.getString(R.string.title_button_month12) -> 5.7F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindTractorDetails").toFloat()
                }
            }

            //===============================================================
            // gruz pricep
            //===============================================================

            context?.resources?.getString(R.string.title_button_gruz_car_pricep) -> {
                when (autoKindGruzPricepDetails) {
                    context.resources?.getString(R.string.title_button_gruz_car_pricep_less_5_E1) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.3F
                            context.resources?.getString(R.string.title_button_month1) -> 0.6F
                            context.resources?.getString(R.string.title_button_month2) -> 1.0F
                            context.resources?.getString(R.string.title_button_month3) -> 1.4F
                            context.resources?.getString(R.string.title_button_month4) -> 1.8F
                            context.resources?.getString(R.string.title_button_month5) -> 2.1F
                            context.resources?.getString(R.string.title_button_month6) -> 2.3F
                            context.resources?.getString(R.string.title_button_month7) -> 2.5F
                            context.resources?.getString(R.string.title_button_month8) -> 2.7F
                            context.resources?.getString(R.string.title_button_month9) -> 2.9F
                            context.resources?.getString(R.string.title_button_month10) -> 3.0F
                            context.resources?.getString(R.string.title_button_month11) -> 3.1F
                            context.resources?.getString(R.string.title_button_month12) -> 3.2F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_car_pricep_in_5_10_E2) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.4F
                            context.resources?.getString(R.string.title_button_month1) -> 0.8F
                            context.resources?.getString(R.string.title_button_month2) -> 1.4F
                            context.resources?.getString(R.string.title_button_month3) -> 1.9F
                            context.resources?.getString(R.string.title_button_month4) -> 2.4F
                            context.resources?.getString(R.string.title_button_month5) -> 2.8F
                            context.resources?.getString(R.string.title_button_month6) -> 3.1F
                            context.resources?.getString(R.string.title_button_month7) -> 3.4F
                            context.resources?.getString(R.string.title_button_month8) -> 3.6F
                            context.resources?.getString(R.string.title_button_month9) -> 3.8F
                            context.resources?.getString(R.string.title_button_month10) -> 4.0F
                            context.resources?.getString(R.string.title_button_month11) -> 4.2F
                            context.resources?.getString(R.string.title_button_month12) -> 4.3F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_car_pricep_in_10_20_E3) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.5F
                            context.resources?.getString(R.string.title_button_month1) -> 1.0F
                            context.resources?.getString(R.string.title_button_month2) -> 1.8F
                            context.resources?.getString(R.string.title_button_month3) -> 2.5F
                            context.resources?.getString(R.string.title_button_month4) -> 3.1F
                            context.resources?.getString(R.string.title_button_month5) -> 3.6F
                            context.resources?.getString(R.string.title_button_month6) -> 4.1F
                            context.resources?.getString(R.string.title_button_month7) -> 4.4F
                            context.resources?.getString(R.string.title_button_month8) -> 4.7F
                            context.resources?.getString(R.string.title_button_month9) -> 5.0F
                            context.resources?.getString(R.string.title_button_month10) -> 5.2F
                            context.resources?.getString(R.string.title_button_month11) -> 5.4F
                            context.resources?.getString(R.string.title_button_month12) -> 5.6F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_gruz_car_pricep_more_20_E4) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 1.1F
                            context.resources?.getString(R.string.title_button_month1) -> 2.2F
                            context.resources?.getString(R.string.title_button_month2) -> 4.1F
                            context.resources?.getString(R.string.title_button_month3) -> 5.6F
                            context.resources?.getString(R.string.title_button_month4) -> 7.0F
                            context.resources?.getString(R.string.title_button_month5) -> 8.1F
                            context.resources?.getString(R.string.title_button_month6) -> 9.1F
                            context.resources?.getString(R.string.title_button_month7) -> 9.9F
                            context.resources?.getString(R.string.title_button_month8) -> 10.6F
                            context.resources?.getString(R.string.title_button_month9) -> 11.2F
                            context.resources?.getString(R.string.title_button_month10) -> 11.7F
                            context.resources?.getString(R.string.title_button_month11) -> 12.1F
                            context.resources?.getString(R.string.title_button_month12) -> 12.5F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindGruzPricepDetails").toFloat()
                }
            }

            //===============================================================
            // moto
            // ===============================================================

            context?.resources?.getString(R.string.title_button_moto) -> {

                when (autoKindMotoDetails) {
                    context.resources?.getString(R.string.title_button_moto_less_150_F1) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.4F
                            context.resources?.getString(R.string.title_button_month1) -> 0.7F
                            context.resources?.getString(R.string.title_button_month2) -> 1.4F
                            context.resources?.getString(R.string.title_button_month3) -> 1.9F
                            context.resources?.getString(R.string.title_button_month4) -> 2.3F
                            context.resources?.getString(R.string.title_button_month5) -> 2.7F
                            context.resources?.getString(R.string.title_button_month6) -> 3.1F
                            context.resources?.getString(R.string.title_button_month7) -> 3.3F
                            context.resources?.getString(R.string.title_button_month8) -> 3.6F
                            context.resources?.getString(R.string.title_button_month9) -> 3.8F
                            context.resources?.getString(R.string.title_button_month10) -> 3.9F
                            context.resources?.getString(R.string.title_button_month11) -> 4.1F
                            context.resources?.getString(R.string.title_button_month12) -> 4.2F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_moto_in_150_750_F2) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 0.6F
                            context.resources?.getString(R.string.title_button_month1) -> 1.1F
                            context.resources?.getString(R.string.title_button_month2) -> 2.1F
                            context.resources?.getString(R.string.title_button_month3) -> 2.9F
                            context.resources?.getString(R.string.title_button_month4) -> 3.6F
                            context.resources?.getString(R.string.title_button_month5) -> 4.2F
                            context.resources?.getString(R.string.title_button_month6) -> 4.6F
                            context.resources?.getString(R.string.title_button_month7) -> 5.1F
                            context.resources?.getString(R.string.title_button_month8) -> 5.4F
                            context.resources?.getString(R.string.title_button_month9) -> 5.7F
                            context.resources?.getString(R.string.title_button_month10) -> 6.0F
                            context.resources?.getString(R.string.title_button_month11) -> 6.2F
                            context.resources?.getString(R.string.title_button_month12) -> 6.4F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_moto_more_750_F3) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 3.1F
                            context.resources?.getString(R.string.title_button_month1) -> 5.9F
                            context.resources?.getString(R.string.title_button_month2) -> 10.2F
                            context.resources?.getString(R.string.title_button_month3) -> 15.2F
                            context.resources?.getString(R.string.title_button_month4) -> 18.8F
                            context.resources?.getString(R.string.title_button_month5) -> 21.8F
                            context.resources?.getString(R.string.title_button_month6) -> 24.4F
                            context.resources?.getString(R.string.title_button_month7) -> 26.6F
                            context.resources?.getString(R.string.title_button_month8) -> 28.5F
                            context.resources?.getString(R.string.title_button_month9) -> 30.0F
                            context.resources?.getString(R.string.title_button_month10) -> 31.4F
                            context.resources?.getString(R.string.title_button_month11) -> 32.5F
                            context.resources?.getString(R.string.title_button_month12) -> 33.6F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindMotoDetails").toFloat()
                }
            }

            //===============================================================
            // bus
            // ===============================================================

            context?.resources?.getString(R.string.title_button_bus_strahovka) -> {
                when (autoKindBusDetails) {
                    context.resources?.getString(R.string.title_button_bus_strahovka_less_20_L1) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 4.7F
                            context.resources?.getString(R.string.title_button_month1) -> 9.0F
                            context.resources?.getString(R.string.title_button_month2) -> 16.7F
                            context.resources?.getString(R.string.title_button_month3) -> 23.2F
                            context.resources?.getString(R.string.title_button_month4) -> 28.7F
                            context.resources?.getString(R.string.title_button_month5) -> 33.4F
                            context.resources?.getString(R.string.title_button_month6) -> 37.3F
                            context.resources?.getString(R.string.title_button_month7) -> 40.7F
                            context.resources?.getString(R.string.title_button_month8) -> 43.5F
                            context.resources?.getString(R.string.title_button_month9) -> 46.0F
                            context.resources?.getString(R.string.title_button_month10) -> 48.0F
                            context.resources?.getString(R.string.title_button_month11) -> 49.8F
                            context.resources?.getString(R.string.title_button_month12) -> 51.4F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_bus_strahovka_in_21_40_L2) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 6.8F
                            context.resources?.getString(R.string.title_button_month1) -> 13.0F
                            context.resources?.getString(R.string.title_button_month2) -> 24.1F
                            context.resources?.getString(R.string.title_button_month3) -> 33.5F
                            context.resources?.getString(R.string.title_button_month4) -> 41.4F
                            context.resources?.getString(R.string.title_button_month5) -> 48.2F
                            context.resources?.getString(R.string.title_button_month6) -> 53.9F
                            context.resources?.getString(R.string.title_button_month7) -> 58.7F
                            context.resources?.getString(R.string.title_button_month8) -> 62.9F
                            context.resources?.getString(R.string.title_button_month9) -> 66.3F
                            context.resources?.getString(R.string.title_button_month10) -> 69.3F
                            context.resources?.getString(R.string.title_button_month11) -> 71.8F
                            context.resources?.getString(R.string.title_button_month12) -> 74.2F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_bus_strahovka_more_40_L3) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 9.4F
                            context.resources?.getString(R.string.title_button_month1) -> 18.1F
                            context.resources?.getString(R.string.title_button_month2) -> 33.4F
                            context.resources?.getString(R.string.title_button_month3) -> 46.4F
                            context.resources?.getString(R.string.title_button_month4) -> 57.4F
                            context.resources?.getString(R.string.title_button_month5) -> 66.7F
                            context.resources?.getString(R.string.title_button_month6) -> 74.7F
                            context.resources?.getString(R.string.title_button_month7) -> 81.4F
                            context.resources?.getString(R.string.title_button_month8) -> 87.1F
                            context.resources?.getString(R.string.title_button_month9) -> 91.9F
                            context.resources?.getString(R.string.title_button_month10) -> 96.0F
                            context.resources?.getString(R.string.title_button_month11) -> 99.5F
                            context.resources?.getString(R.string.title_button_month12) -> 102.8F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_bus_strahovka_express_L4) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 13.9F
                            context.resources?.getString(R.string.title_button_month1) -> 26.9F
                            context.resources?.getString(R.string.title_button_month2) -> 49.7F
                            context.resources?.getString(R.string.title_button_month3) -> 69.0F
                            context.resources?.getString(R.string.title_button_month4) -> 85.4F
                            context.resources?.getString(R.string.title_button_month5) -> 99.3F
                            context.resources?.getString(R.string.title_button_month6) -> 111.1F
                            context.resources?.getString(R.string.title_button_month7) -> 121.1F
                            context.resources?.getString(R.string.title_button_month8) -> 129.5F
                            context.resources?.getString(R.string.title_button_month9) -> 136.7F
                            context.resources?.getString(R.string.title_button_month10) -> 142.8F
                            context.resources?.getString(R.string.title_button_month11) -> 148.0F
                            context.resources?.getString(R.string.title_button_month12) -> 152.9F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    context.resources?.getString(R.string.title_button_bus_strahovka_trolleybus_W) -> {
                        when (term) {
                            context.resources?.getString(R.string.title_button_days15) -> 7.1F
                            context.resources?.getString(R.string.title_button_month1) -> 13.7F
                            context.resources?.getString(R.string.title_button_month2) -> 25.4F
                            context.resources?.getString(R.string.title_button_month3) -> 35.2F
                            context.resources?.getString(R.string.title_button_month4) -> 43.6F
                            context.resources?.getString(R.string.title_button_month5) -> 50.7F
                            context.resources?.getString(R.string.title_button_month6) -> 56.7F
                            context.resources?.getString(R.string.title_button_month7) -> 61.8F
                            context.resources?.getString(R.string.title_button_month8) -> 66.2F
                            context.resources?.getString(R.string.title_button_month9) -> 69.8F
                            context.resources?.getString(R.string.title_button_month10) -> 72.9F
                            context.resources?.getString(R.string.title_button_month11) -> 75.6F
                            context.resources?.getString(R.string.title_button_month12) -> 78.1F
                            else -> Log.d(LOG_TAG, "somethings wrong with term").toFloat()
                        }
                    }
                    else -> Log.d(LOG_TAG, "somethings wrong with autoKindBusDetails").toFloat()
                }
            }
            else -> Log.d(LOG_TAG, "somethings wrong with rate").toFloat()
        }

        //==============================================================
        // end calculate insurance rate (return t)
        //=============================================================

        val totalTemp = (k1 + k3 - 1.0F) * t
        val total  = BigDecimal(totalTemp.toDouble())
            .setScale(2, RoundingMode.HALF_DOWN).toFloat()
        this.total = total

        return "$total евро"
    }

    fun totalRubbleValue(euroRate: String) : String {
        var rubble = 0.0
        if (euroRate != "")
        rubble = BigDecimal((euroRate.toFloat()*this.total).toDouble()).setScale(2, RoundingMode.HALF_DOWN).toDouble()
        else return ""

        return  MyClass.check_ruble(rubble)
    }


}