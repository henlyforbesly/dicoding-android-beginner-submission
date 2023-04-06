package com.example.submission

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.submission.databinding.ItemRowDogBinding

class ListDogAdapter(private val listDogs: ArrayList<Dog>) : RecyclerView.Adapter<ListDogAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowDogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(listDogs[position])

    override fun getItemCount() = listDogs.size

    class ListViewHolder(var binding: ItemRowDogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(dog: Dog) {
            binding.imgItemPhoto.setImageResource(dog.photo)
            binding.tvItemName.text = dog.name
            binding.tvItemGender.text = dog.gender

            val descriptionText = "${dog.age} years old · ${dog.breed}"
            binding.tvItemDescription.text = descriptionText

            itemView.setOnClickListener {
                val intentToDetail = Intent(itemView.context, DetailActivity::class.java)
                intentToDetail.putExtra(DetailActivity.EXTRA_DOG, dog)
                itemView.context.startActivity(intentToDetail)
            }
        }
    }
}