package by.a_ogurtsov.AutoTaxes.strahovka.location

import androidx.recyclerview.widget.RecyclerView
import by.a_ogurtsov.AutoTaxes.bindButtonLocationStrahovka
import by.a_ogurtsov.AutoTaxes.databinding.RecyclerviewStrahovkaLocationItemBinding
import by.a_ogurtsov.AutoTaxes.strahovka.City

class LocationViewHolder(
    private val binding: RecyclerviewStrahovkaLocationItemBinding,
    private val listener: LocationListener
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(city: City) {
        bindButtonLocationStrahovka(city.name, city.k, binding.buttonRecyclerViewStrahovkaItem)

        binding.buttonRecyclerViewStrahovkaItem.setOnClickListener {
            listener.getNameAndСoefficient(city.name, city.k)
        }
    }


}