package by.a_ogurtsov.AutoTaxes.strahovka.location

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import by.a_ogurtsov.AutoTaxes.databinding.RecyclerviewStrahovkaLocationItemBinding
import by.a_ogurtsov.AutoTaxes.strahovka.City

class LocationAdapter(private val listener: LocationListener) :
    ListAdapter<City, LocationViewHolder>(itemComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewStrahovkaLocationItemBinding.inflate(layoutInflater, parent, false)
        return LocationViewHolder(binding, listener)

    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    private companion object {

        private val itemComparator = object : DiffUtil.ItemCallback<City>() {

            override fun areItemsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: City, newItem: City): Boolean {
                return oldItem.name == newItem.name
            }

            override fun getChangePayload(oldItem: City, newItem: City) = Any()
        }
    }


}