package com.example.marvel_app_clone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel_app_clone.databinding.ItemCharacterBinding
import com.example.marvel_app_clone.data.model.character.CharacterModel
import com.example.marvel_app_clone.R
import com.example.marvel_app_clone.util.limitDescription


class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    inner class CharacterViewHolder(val binding: ItemCharacterBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<CharacterModel>() {
        override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
        //Se os itens antigos e novos são iguais

        override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
            return oldItem.id == newItem.id && oldItem.name == newItem.name && oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path && oldItem.thumbnail.extension == newItem.thumbnail.extension
        }
        //Vai ser chamado para saber se os itens antigos e os itens novos representam o mesmo item visualmente falando, faz uma comparação

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var characters: List<CharacterModel>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
                ItemCharacterBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }
    override fun getItemCount(): Int = characters.size


    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val chararter = characters[position]
        holder.binding.apply {
            tvNameCharacter.text = chararter.name
            if (chararter.description == "") {
                tvDescriptionCharacter.text =
                        holder.itemView.context.getString(R.string.text_description_empty)
            }else{
                tvDescriptionCharacter.text = chararter.description.limitDescription(100)
            }

            Glide.with(holder.itemView.context)
                    .load(chararter.thumbnail.path + "." + chararter.thumbnail.extension)
                    .into(imgCharacter)
        }
    }

    //Evento de click
    private var onItemClickListener: ((CharacterModel) -> Unit)? = null


}