package com.naufalprakoso.researchworkmanager.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.naufalprakoso.researchworkmanager.database.entity.HeroEntity
import com.naufalprakoso.researchworkmanager.databinding.ItemHeroBinding

class HeroAdapter(context: Context) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    private val inflater = LayoutInflater.from(context)
    private lateinit var binding: ItemHeroBinding

    private val superheroes = arrayListOf<HeroEntity>()

    fun setHeroes(superheroes: List<HeroEntity>) {
        this.superheroes.clear()
        this.superheroes.addAll(superheroes)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHeroBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun getItemCount(): Int = superheroes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(binding, superheroes[position])
    }

    override fun getItemId(position: Int): Long {
        return superheroes[position].id.hashCode().toLong()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(binding: ItemHeroBinding, superhero: HeroEntity) {
            binding.tvName.text = superhero.name
            binding.tvSlug.text = superhero.slug
        }
    }
}