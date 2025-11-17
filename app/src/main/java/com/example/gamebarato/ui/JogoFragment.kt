package com.example.gamebarato.ui

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Adapters.JogoVitrineAdapater
import com.example.gamebarato.JogoViewModelFactory
import com.example.gamebarato.R
import com.example.gamebarato.Repository.jogoRepositoryImplementacao
import com.example.gamebarato.Source.AppDatabase

class JogoFragment: Fragment(), JogoVitrineAdapater.AdapterList {

    companion object {
        fun newInstance() = JogoFragment()
    }

 private lateinit var mainViewModel: JogoViewModel

 private lateinit var adapater: JogoVitrineAdapater

 private lateinit var jogoRepositoryImplementacao: jogoRepositoryImplementacao

 lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cartao_dos_jogos, container, false)
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
    }

    private fun runDatabase(){
        database = context.let {
            AppDatabase.invoke(it!!)
        }
    }

    private fun setList(){
        adapater = JogoVitrineAdapater(mutableListOf(), this, requireContext())
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoes)
        recyclerViewList?.layoutManager = LinearLayoutManager(context)
        recyclerViewList?.adapter = adapater
    }

}