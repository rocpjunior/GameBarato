package com.example.gamebarato.ux.notificacoes

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Adapters.NotificacoesAdapter
import com.example.gamebarato.Models.JogoViewModel
import com.example.gamebarato.Models.JogoViewModelFactory
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R
import com.example.gamebarato.Repository.jogoRepositoryImplementacao
import com.example.gamebarato.Source.AppDatabase
import com.example.gamebarato.ux.DetalhesJogo

class NotificacoesFragment : Fragment(), NotificacoesAdapter.AdapterList {

    companion object {
        fun newInstance() = NotificacoesFragment()
    }

    private lateinit var jogoViewModel: JogoViewModel

    private lateinit var adapater: NotificacoesAdapter

    private lateinit var jogoRepositoryImplementacao: jogoRepositoryImplementacao

    private lateinit var database: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_notificacoes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        runDatabase()
        jogoRepositoryImplementacao =
            jogoRepositoryImplementacao(
                database.JogoDao()
            )
        val factory = JogoViewModelFactory(jogoRepositoryImplementacao)
        jogoViewModel = ViewModelProvider(this, factory).get(JogoViewModel::class.java)

        setList()
        observador()
    }

    private fun runDatabase(){
        database = context.let {
            AppDatabase.invoke(requireContext())
        }
    }

    private fun setList(){
        adapater = NotificacoesAdapter (mutableListOf(), { jogoDetalhes ->
            val nome = jogoDetalhes.nomeJogo
            val preco = jogoDetalhes.precoJogo
            val imagem = jogoDetalhes.imgJogo
            val intent = Intent(context, DetalhesJogo::class.java)

            intent.putExtra("nomeJogo",nome )
            intent.putExtra("precoJogo",preco )
            intent.putExtra("imagemJogo", imagem)

            startActivity(intent)
        })
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoesNotificacoes)
        recyclerViewList?.layoutManager = GridLayoutManager(context, 1)
        recyclerViewList?.adapter = adapater
    }

    private fun observador(){
        jogoViewModel.mostrarJogos()
        jogoViewModel.watcherAll().observe(viewLifecycleOwner, Observer {
            adapater.adicionarLista(it as MutableList<JogoVitrine>)
        })
        jogoViewModel.watcherErrado().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val recyclerView = view?.findViewById<RecyclerView>(R.id.rvCartoesNotificacoes)
                recyclerView?.visibility =  GONE
            }
        })
    }
}