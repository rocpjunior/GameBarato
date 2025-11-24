package com.example.gamebarato.ui.main

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
        return inflater.inflate(R.layout.fragment_jogo, container, false)
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

        val fabTeste = view?.findViewById<FloatingActionButton>(R.id.fabNotificacoes)
        setList()
        observador()
        fabTeste?.setOnClickListener { adicionarJogo() }
    }

    private fun runDatabase(){
        database = context.let {
            AppDatabase.Companion.invoke(requireContext())
        }
    }

    private fun setList(){
        adapater = JogoVitrineAdapter(mutableListOf(), this) { jogoVitrine -> }
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoesJogos)
        recyclerViewList?.layoutManager = LinearLayoutManager(context)
        recyclerViewList?.adapter = adapater
    }

    private fun observador(){
        mainViewModel.mostrarJogos()
        mainViewModel.watcherAll().observe(viewLifecycleOwner, Observer {
            adapater.adicionarLista(it as MutableList<JogoVitrine>)
        })
    }

    private fun adicionarJogo(){
        val dialogo = AlertDialog.Builder(requireContext())
        dialogo.setTitle("Adicionar novo Jogo")

        val novoLayout = LinearLayout(context)
        novoLayout.orientation = LinearLayout.VERTICAL
        val lp = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp.setMargins(32, 24, 32, 0)

        val nome = EditText(context)
        nome.hint = "Digite o nome do produto"
        nome.layoutParams = lp
        novoLayout.addView(nome)

        val imagem = EditText(context)
        imagem.hint = "Coloque uma imagem"
        imagem.layoutParams = lp
        novoLayout.addView(imagem)

        val preco = EditText(context)
        preco.hint = "Digite o preço do jogo"
        preco.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        preco.layoutParams = lp
        novoLayout.addView(preco)

        dialogo.setView(novoLayout)

        dialogo.setPositiveButton("Salvar"){_,_ ->
            val nomeProduto = nome.text.toString()
            val imagemProduto = imagem.text.toString()
            val precoProduto = preco.text.toString().toDoubleOrNull() ?: 0.0
            mainViewModel.inserirJogo(nomeProduto, precoProduto, imagemProduto)
        }
        dialogo.setNegativeButton("Cancelar", null)
        dialogo.show()
    }
}