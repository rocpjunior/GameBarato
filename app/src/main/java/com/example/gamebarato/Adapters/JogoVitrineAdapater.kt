package com.example.gamebarato.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R

class JogoVitrineAdapater (
    private val listaJogosVitrine: MutableList<JogoVitrine>,
    private val listando: AdapterList,
    val onClick: (JogoVitrine) -> Unit
): RecyclerView.Adapter<JogoVitrineAdapter.ViewHolder>() {

    override fun getItemCount() = listaJogosVitrine.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int): JogoVitrineViewHolder {
        return JogoVitrineViewHolder(parent.inflate(R.layout.cartao_dos_jogos))
    }

    override fun onBindViewHolder(
        holder: JogoVitrineAdapater.JogoVitrineViewHolder,
        position: Int) {
        holder.bindViewHolder(listaJogosVitrine[position])
    }

    fun adicionarLista(listaJogo: MutableList<JogoVitrine>) {
        this.listaJogosVitrine.addAll(listaJogo)
        notifyDataSetChanged()
    }

    interface AdapterList{}

    inner class JogoVitrineViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView) {

            private lateinit var item: JogoVitrine
        fun bindViewHolder(item: JogoVitrine) {
            this.item = item
            val txtNomeJogo = itemView.findViewById<TextView>(R.id.txtNomeJogo)
            txtNomeJogo.text = item.nomeJogo
            val txtPrecoJogo = itemView.findViewById<TextView>(R.id.txtPrecoJogo)
            txtPrecoJogo.text = item.precoJogo
            val imagemJogo = itemView.findViewById<ImageView>(R.id.imgJogo)
            imagemJogo.setOnClickListener { listando }
        }
    }
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View{
    return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
}