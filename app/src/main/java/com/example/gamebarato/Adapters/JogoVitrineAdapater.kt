package com.example.gamebarato.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R
import com.squareup.picasso.Picasso
import kotlinx.coroutines.withContext

class JogoVitrineAdapter (
    private val listaJogosVitrine: MutableList<JogoVitrine>,
    val onClick: (JogoVitrine) -> Unit
): RecyclerView.Adapter<JogoVitrineAdapter.JogoVitrineViewHolder>() {

    override fun getItemCount() = listaJogosVitrine.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): JogoVitrineViewHolder {
        return JogoVitrineViewHolder(parent.inflate(R.layout.cartao_dos_jogos))
    }

    override fun onBindViewHolder(
        holder: JogoVitrineViewHolder,
        position: Int) {
        holder.bind(listaJogosVitrine[position])
    }

    fun adicionarLista(listaJogo: MutableList<JogoVitrine>) {
        this.listaJogosVitrine.addAll(listaJogo)
        notifyDataSetChanged()
    }

    interface AdapterList{}

    inner class JogoVitrineViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

            private lateinit var item: JogoVitrine
        fun bind(item: JogoVitrine) {
            this.item = item
            val txtNomeJogo = itemView.findViewById<TextView>(R.id.txtNomeJogo)
            txtNomeJogo.text = item.nomeJogo
            val txtPrecoJogo = itemView.findViewById<TextView>(R.id.txtPrecoJogo)
            txtPrecoJogo.text = item.precoJogo
            val imagemJogo = itemView.findViewById<ImageView>(R.id.imgJogo)
            Glide.with(itemView).load(item.img).into(imagemJogo)

            val clControlador = itemView.findViewById<ConstraintLayout>(R.id.clControlador)

            Picasso.get()
            clControlador.setOnClickListener { onClick(item) }
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}