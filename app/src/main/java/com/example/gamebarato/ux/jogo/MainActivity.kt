package com.example.gamebarato.ux.jogo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.ux.favoritos.Favoritos
import com.example.gamebarato.ux.notificacoes.Notificacoes
import com.example.gamebarato.ux.ofertas.Ofertas
import com.example.gamebarato.ux.Perfil
import com.example.gamebarato.ux.pesquisar.Pesquisar
import com.example.gamebarato.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, JogoFragment.newInstance())
                .commitNow()
        }

        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoOfertas = findViewById<Button>(R.id.btnOfertas)
        val botaoFavoritos = findViewById<Button>(R.id.btnFavoritos)
        val botaoPerfil = findViewById<Button>(R.id.btnPerfil)
        val botaoNotificacoes = findViewById<FloatingActionButton>(R.id.fabNotificacoes)

        botaoPesquisar.setOnClickListener(this::telaPesquisar)
        botaoOfertas.setOnClickListener(this::telaOfertas)
        botaoFavoritos.setOnClickListener(this::telaFavoritos)
        botaoPerfil.setOnClickListener(this::telaPerfil)
        botaoNotificacoes.setOnClickListener(this::telaNotificacoes)

    }

    fun telaPesquisar (view: View) {
        val intent = Intent(this, Pesquisar::class.java)
        startActivity(intent)
    }
    fun telaOfertas (view: View) {
        val intent = Intent(this, Ofertas::class.java)
        startActivity(intent)
    }
    fun telaFavoritos (view: View) {
        val intent = Intent(this, Favoritos::class.java)
        startActivity(intent)
    }
    fun telaPerfil (view: View) {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }
    fun telaNotificacoes (view: View) {
        val intent = Intent(this, Notificacoes::class.java)
        startActivity(intent)
    }
}