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

class NotificacoesAdapter(
    private val listaJogosVitrine: MutableList<JogoVitrine>,
    val onClick: (JogoVitrine) -> Unit
): RecyclerView.Adapter<NotificacoesAdapter.NotificacoesViewHolder>() {

    override fun getItemCount() = listaJogosVitrine.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): NotificacoesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cartao_notificacoes, parent, false)
        return NotificacoesViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: NotificacoesViewHolder,
        position: Int) {
        holder.bind(listaJogosVitrine[position])
    }

    fun adicionarLista(listaJogo: MutableList<JogoVitrine>) {
        this.listaJogosVitrine.addAll(listaJogo)
        notifyDataSetChanged()
    }

    interface AdapterList{}

    inner class NotificacoesViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

            private lateinit var item: JogoVitrine
        fun bind(item: JogoVitrine) {
            this.item = item
            val imagemJogo = itemView.findViewById<ImageView>(R.id.imgJogo)
            Glide.with(itemView).load(item.imgJogo).into(imagemJogo)

            val clControlador = itemView.findViewById<ConstraintLayout>(R.id.clNotificacoes)

            Picasso.get()
            clControlador.setOnClickListener { onClick(item) }
        }
    }
}