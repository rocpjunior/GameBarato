package com.example.gamebarato.ui.main

import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
//import androidx.appcompat.app.AlertDialog
import android.app.AlertDialog
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

class MainFragment : Fragment(), JogoVitrineAdapter.AdapterList {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapater: JogoVitrineAdapter
    private lateinit var jogoRepositoryImplementacao: jogoRepositoryImplementacao

    lateinit var database: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        runDatabase()
        jogoRepositoryImplementacao =
            jogoRepositoryImplementacao(
                database.JogoDao()
            )
        val factory = JogoViewModelFactory(jogoRepositoryImplementacao)
        mainViewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        val fabTeste = view?.findViewById<FloatingActionButton>(R.id.fabTeste)
        setList()
        observador()
        fabTeste?.setOnClickListener { adicionarJogo() }
    }

    private fun runDatabase(){
        database = context.let {
            AppDatabase.invoke(requireContext())
        }
    }

    private fun setList(){
        adapater = JogoVitrineAdapter(mutableListOf()) //{ jogoVitrine -> }
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoesJogos)
        recyclerViewList?.layoutManager = LinearLayoutManager(context)
        recyclerViewList?.adapter = adapater
    }

    private fun observador() {
        mainViewModel.mostrarJogos()
        mainViewModel.watcherAll().observe(viewLifecycleOwner, Observer {
            adapater.adicionarLista(it as MutableList<JogoVitrine>)
        })
        mainViewModel.watcherErrado().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                val recyclerView = view?.findViewById<RecyclerView>(R.id.rvCartoesJogos)
                recyclerView?.visibility = GONE
                val fabADD = view?.findViewById<FloatingActionButton>(R.id.fabTeste)
                fabADD?.visibility = GONE
            }
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
            val nomeJogo = nome.text.toString()
            val imagemJogo = imagem.text.toString()
            val precoJogo = preco.text.toString().toDoubleOrNull() ?: 0.0
            mainViewModel.inserirJogo(nomeJogo, precoJogo, imagemJogo)
        }
        dialogo.setNegativeButton("Cancelar", null)
        dialogo.show()
    }
}