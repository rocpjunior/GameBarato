package com.example.gamebarato.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R
import com.squareup.picasso.Picasso

class FavoritosVitrineAdapter (
    private val listaFavoritosVitrine: MutableList<JogoVitrine>,
    val onClick: (JogoVitrine) -> Unit
): RecyclerView.Adapter<FavoritosVitrineAdapter.FavoritosVitrineViewHolder>() {

    override fun getItemCount() = listaFavoritosVitrine.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): FavoritosVitrineViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cartao_favoritos, parent, false)
        return FavoritosVitrineViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: FavoritosVitrineViewHolder,
        position: Int) {
        holder.bind(listaFavoritosVitrine[position])
    }

    fun adicionarLista(listaJogo: MutableList<JogoVitrine>) {
        this.listaFavoritosVitrine.addAll(listaJogo)
        notifyDataSetChanged()
    }

    interface AdapterList{}

    inner class FavoritosVitrineViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

        private lateinit var item: JogoVitrine
        fun bind(item: JogoVitrine) {
            this.item = item
            val txtNomeJogo = itemView.findViewById<TextView>(R.id.txtJogo)
            txtNomeJogo.text = item.nomeJogo
            val txtPrecoJogo = itemView.findViewById<TextView>(R.id.txtPrecoJogo)
            txtPrecoJogo.text = item.precoJogo
            val imagemJogo = itemView.findViewById<ImageView>(R.id.imgJogo)
            Glide.with(itemView).load(item.imgJogo).into(imagemJogo)

            val clControlador = itemView.findViewById<ConstraintLayout>(R.id.clFavoritos)

            Picasso.get()
            clControlador.setOnClickListener { onClick(item) }
        }
    }
}