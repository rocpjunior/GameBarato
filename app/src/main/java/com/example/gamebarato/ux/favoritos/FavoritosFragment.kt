package com.example.gamebarato.ux.favoritos

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Adapters.FavoritosVitrineAdapter
import com.example.gamebarato.ux.DetalhesJogo
import com.example.gamebarato.Models.JogoViewModel
import com.example.gamebarato.Models.JogoViewModelFactory
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R
import com.example.gamebarato.Repository.jogoRepositoryImplementacao
import com.example.gamebarato.Source.AppDatabase

class FavoritosFragment : Fragment(), FavoritosVitrineAdapter.AdapterList {

    companion object {
        fun newInstance() = FavoritosFragment()
    }

    private lateinit var jogoViewModel: JogoViewModel

    private lateinit var adapater: FavoritosVitrineAdapter

    private lateinit var jogoRepositoryImplementacao: jogoRepositoryImplementacao

    private lateinit var database: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favoritos, container, false)
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
        adapater = FavoritosVitrineAdapter(mutableListOf(), { jogoDetalhes ->
            val nome = jogoDetalhes.nomeJogo
            val preco = jogoDetalhes.precoJogo
            val imagem = jogoDetalhes.imgJogo
            val intent = Intent(context, DetalhesJogo::class.java)

            intent.putExtra("nomeJogo", nome)
            intent.putExtra("precoJogo", preco)
            intent.putExtra("imagemJogo", imagem)

            startActivity(intent)
        })
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoesFavoritos)
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
                val recyclerView = view?.findViewById<RecyclerView>(R.id.rvCartoesOfertas)
                recyclerView?.visibility = View.GONE
            }
        })
    }
}