package by.a_ogurtsov.AutoTaxes.strahovka.autoKind.details

import by.a_ogurtsov.AutoTaxes.R
import com.google.android.material.button.MaterialButton

fun MaterialButton.setIcon(name: String) {
    when (name) {
        resources.getString(R.string.title_button_less1200_strahovka) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_1)
        }
        resources.getString(R.string.title_button_in_1200_1800_strahovka) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_in_1800_2500_strahovka) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_3)
        }
        resources.getString(R.string.title_button_in_2500_3500_strahovka) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_more_3500_strahovka) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_5)
        }
        resources.getString(R.string.title_button_taxi) -> {
            this.setIconResource(R.drawable.ic_baseline_local_taxi_24)
        }
        //=============================================================

        resources.getString(R.string.title_button_electro_strahovka) -> {
            this.setIconResource(R.drawable.ic_hexagon_outline)
        }
        resources.getString(R.string.title_button_gibrid) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        //=============================================================
        resources.getString(R.string.title_button_legk_pricep_b1) -> {
            this.setIconResource(R.drawable.ic_hexagon_outline)
        }
        resources.getString(R.string.title_button_legk_pricep_b2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        //=============================================================
        resources.getString(R.string.title_button_gruz_less_1_t_С0) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_1)
        }
        resources.getString(R.string.title_button_gruz_in_1_t_2_t_С1) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_gruz_in_2_t_8_t_С2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_3)
        }
        resources.getString(R.string.title_button_gruz_in_8_t_15_t_С3) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_gruz_in_15_t_25_t_С4) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_5)
        }
        resources.getString(R.string.title_button_gruz_more_25_t_С5) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        resources.getString(R.string.title_button_gruz_trailer_С6) -> {
            this.setIconResource(R.drawable.ic_semi_trailer)
        }
        //=============================================================
        resources.getString(R.string.title_button_tractor_less_50_V1) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_tractor_шт_50_200_V2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_tractor_more_200_V3) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        resources.getString(R.string.title_button_tractor_crawler_M) -> {
            this.setIconResource(R.drawable.ic_baseline_tractor_24)
        }
        //=============================================================
        resources.getString(R.string.title_button_gruz_car_pricep_less_5_E1) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_gruz_car_pricep_in_5_10_E2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_gruz_car_pricep_more_20_E4) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        //===============================================================
        resources.getString(R.string.title_button_moto_less_150_F1) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_moto_in_150_750_F2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_moto_more_750_F3) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        //===============================================================
        resources.getString(R.string.title_button_bus_strahovka_less_20_L1) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_bus_strahovka_in_21_40_L2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_bus_strahovka_more_40_L3) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }
        resources.getString(R.string.title_button_bus_strahovka_express_L4) -> {
            this.setIconResource(R.drawable.ic_directions_bus_black_24dp)
        }
        resources.getString(R.string.title_button_bus_strahovka_trolleybus_W) -> {
            this.setIconResource(R.drawable.ic_baseline_tram_24)
        }
        //================================================================
        resources.getString(R.string.title_button_strahovka_vozrast_stazh_less25_less2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_1)
        }
        resources.getString(R.string.title_button_strahovka_vozrast_stazh_less25_more2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_2)
        }
        resources.getString(R.string.title_button_strahovka_vozrast_stazh_more25_less2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_4)
        }
        resources.getString(R.string.title_button_strahovka_vozrast_stazh_more25_more2) -> {
            this.setIconResource(R.drawable.ic_hexagon_slice_6)
        }

    }
}
