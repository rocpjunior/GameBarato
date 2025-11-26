package com.example.gamebarato

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.Models.JogoVitrine
import com.example.gamebarato.ui.main.JogoFragment

class DetalhesJogo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhes_jogo)

        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoOfertas = findViewById<Button>(R.id.btnOfertas)
        val botaoFavoritos = findViewById<Button>(R.id.btnFavoritos)
        val botaoPerfil = findViewById<Button>(R.id.btnPerfil)
        val nomeJogo = findViewById<TextView>(R.id.txtTituloJogo)
        val bundle = intent.extras

        if (bundle != null) {
            val jogo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable("jogo", JogoFragment::class.java)
            } else {
                bundle.getParcelable("jogo") as? JogoVitrine
            }

            if (jogo != null) {
                nomeJogo.text = jogo.toString()

            }

            botaoInicio.setOnClickListener(this::telaInicio)
            botaoPesquisar.setOnClickListener(this::telaPesquisar)
            botaoOfertas.setOnClickListener(this::telaOfertas)
            botaoFavoritos.setOnClickListener(this::telaFavoritos)
            botaoPerfil.setOnClickListener(this::telaPerfil)

        }
    }

        fun telaInicio(view: View) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        fun telaPesquisar(view: View) {
            val intent = Intent(this, Pesquisar::class.java)
            startActivity(intent)
        }

        fun telaOfertas(view: View) {
            val intent = Intent(this, Ofertas::class.java)
            startActivity(intent)
        }

        fun telaFavoritos(view: View) {
            val intent = Intent(this, Favoritos::class.java)
            startActivity(intent)
        }

        fun telaPerfil(view: View) {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }
    }