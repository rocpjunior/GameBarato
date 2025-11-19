package com.example.gamebarato.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Adapters.JogoVitrineAdapter
import com.example.gamebarato.JogoViewModelFactory
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R
import com.example.gamebarato.Repository.jogoRepositoryImplementacao
import com.example.gamebarato.Source.AppDatabase

class JogoFragment: Fragment(), JogoVitrineAdapter.AdapterList {

    companion object {
        fun newInstance() = JogoFragment()
    }

 private lateinit var mainViewModel: JogoViewModel
 private lateinit var adapater: JogoVitrineAdapter
 private lateinit var jogoRepositoryImplementacao: jogoRepositoryImplementacao
 lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.recycler_view_jogo_vitrine, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        runDatabase()
        jogoRepositoryImplementacao =
            jogoRepositoryImplementacao(
                database.JogoDao()
            )
        val factory = JogoViewModelFactory(jogoRepositoryImplementacao)
        mainViewModel = ViewModelProvider(this, factory).get(JogoViewModel::class.java)
        setList()
        observador()
    }

    private fun runDatabase(){
        database = context.let {
            AppDatabase.invoke(requireContext())
        }
    }

    private fun setList(){
        adapater = JogoVitrineAdapter(mutableListOf(), this) { jogoVitrine ->}
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoes)
        recyclerViewList?.layoutManager = LinearLayoutManager(context)
        recyclerViewList?.adapter = adapater
    }

    private fun observador(){
        mainViewModel.mostrarJogos()
        mainViewModel.watcherAll().observe(viewLifecycleOwner, Observer{
            adapater.adicionarLista(it as MutableList<JogoVitrine>)
        })
    }
}