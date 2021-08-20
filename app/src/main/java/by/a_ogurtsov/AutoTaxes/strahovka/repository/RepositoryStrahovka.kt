package by.a_ogurtsov.AutoTaxes.strahovka.repository

class RepositoryStrahovka {

    fun totalSumValue(
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
        autoKindBusDetails: String
    ): String {
        val total =
            k1.toString() + autoKind + autoKindLegkDetails + autoKindElectroGibridDetails + autoKindLegkPricepDetails
        return total
    }


}