package by.a_ogurtsov.AutoTaxes.strahovka.repository

class RepositoryStrahovka {

    fun totalSumValue(
        k1: Float,
        autoKind: String,
        autoKindLegkDetails: String,
        autoKindElectroGibridDetails: String
    ): String {
        val total = k1.toString() + autoKind + autoKindLegkDetails +  autoKindElectroGibridDetails
        return total
    }


}