package com.example.marvel_app_clone.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marvel_app_clone.data.model.comic.ComicModel
import com.example.marvel_app_clone.databinding.ItemComicBinding

class ComicAdapter : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {

    inner class ComicViewHolder(val binding: ItemComicBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<ComicModel>() {
        override fun areItemsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
        //Se os itens antigos e novos são iguais

        override fun areContentsTheSame(oldItem: ComicModel, newItem: ComicModel): Boolean {
            return oldItem.id == newItem.id && oldItem.title == newItem.title && oldItem.description == newItem.description &&
                    oldItem.thumbnailModel.path == newItem.thumbnailModel.path && oldItem.thumbnailModel.extension == newItem.thumbnailModel.extension
        }
        //Vai ser chamado para saber se os itens antigos e os itens novos representam o mesmo item visualmente falando, faz uma comparação

    }

    private val differ = AsyncListDiffer(this, differCallback)

    var comics: List<ComicModel>
    get() = differ.currentList
    set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        return ComicViewHolder(
                ItemComicBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                )
        )
    }
    override fun getItemCount(): Int = comics.size


    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.binding.apply {
            tvNameComic.text = comic.title
            tvDescriptionComic.text = comic.description

            Glide.with(holder.itemView.context)
                    .load(comic.thumbnailModel.path + "." + comic.thumbnailModel.extension)
                    .into(imgComic)
        }
    }


}

//F5 clicar dentro do projeto, e no package adapters, clicar no CharacterAdapter e logo após apertar F5 do teclado que vai copiar toda classe, para que seja refatorada.