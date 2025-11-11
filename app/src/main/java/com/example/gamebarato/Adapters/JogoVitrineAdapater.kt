package com.example.gamebarato.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Models.JogoVitrine

class JogoVitrineAdapater (
    val onClick: (JogoVitrine) -> Unit
) : RecyclerView.Adapter<JogoVitrineAdapter.JogoViewHolder>() {

    private var listaJogosVitrine = mutableListOf<JogoVitrine>()

    fun adicionarLista(lista: List<JogoVitrine>) {
        this.listaJogosVitrine.addAll(lista)
        notifyDataSetChanged()
    }

    inner class JogoVitrineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(jogo: JogoVitrine) {
            val nomeJogo = jogo.backdrop_pathz
            val tamanhoJogo = "w780"

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JogoVitrineViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = .inflate(
        layoutInflater, parent, false
    )
    return JogoVitrineViewHolder (itemView = )
    }

    override fun onBindViewHolder(holder: JogoVitrineViewHolder, position: Int) {
        val filme = listaJogosVitrine[position]
        holder.bind(filme)
    }

    override fun getItemCount(): Int {
        return listaJogosVitrine.size
    }
}