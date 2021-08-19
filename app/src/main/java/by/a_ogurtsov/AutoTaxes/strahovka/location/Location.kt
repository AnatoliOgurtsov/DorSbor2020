package by.a_ogurtsov.AutoTaxes.strahovka

data class Location(val cities: List<City>)

data class City(
    val name: String,
    val k: Float
)
