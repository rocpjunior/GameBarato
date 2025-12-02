package com.example.gamebarato.ux.ofertas

import android.content.Intent
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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gamebarato.Adapters.JogoVitrineAdapter
import com.example.gamebarato.Adapters.OfertasVitrineAdapter
import com.example.gamebarato.ux.DetalhesJogo
import com.example.gamebarato.Models.JogoViewModel
import com.example.gamebarato.Models.JogoViewModelFactory
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.R
import com.example.gamebarato.Repository.jogoRepositoryImplementacao
import com.example.gamebarato.Source.AppDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class OfertasFragment : Fragment(), JogoVitrineAdapter.AdapterList {

    companion object {
        fun newInstance() = OfertasFragment()
    }

    private lateinit var jogoViewModel: JogoViewModel

    private lateinit var adapater: OfertasVitrineAdapter

    private lateinit var jogoRepositoryImplementacao: jogoRepositoryImplementacao

    private lateinit var database: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ofertas, container, false)
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

        val fab = view?.findViewById<FloatingActionButton>(R.id.fabOfertas)
        setList()
        observador()
        fab?.setOnClickListener { adicionarJogo() }
    }

    private fun runDatabase(){
        database = context.let {
            AppDatabase.Companion.invoke(requireContext())
        }
    }

    private fun setList(){
        adapater = OfertasVitrineAdapter(mutableListOf(), { jogoDetalhes ->
            val nome = jogoDetalhes.nomeJogo
            val preco = jogoDetalhes.precoJogo
            val imagem = jogoDetalhes.imgJogo
            val intent = Intent(context, DetalhesJogo::class.java)

            intent.putExtra("nomeJogo", nome)
            intent.putExtra("precoJogo", preco)
            intent.putExtra("imagemJogo", imagem)

            startActivity(intent)
        })
        val recyclerViewList = view?.findViewById<RecyclerView>(R.id.rvCartoesOfertas)
        recyclerViewList?.layoutManager = GridLayoutManager(context, 2)
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
                val fab = view?.findViewById<FloatingActionButton>(R.id.fabOfertas)
                fab?.visibility = View.GONE
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
        nome.hint = "Digite o nome do jogo"
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
            val precoJogo = preco.text.toString()
            jogoViewModel.inserirJogo(nomeJogo, precoJogo, imagemJogo)
        }
        dialogo.setNegativeButton("Cancelar", null)
        dialogo.show()
    }
}