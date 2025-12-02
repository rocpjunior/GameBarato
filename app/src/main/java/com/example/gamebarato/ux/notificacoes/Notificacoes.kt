package com.example.gamebarato.ux.notificacoes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.R
import com.example.gamebarato.ux.Perfil
import com.example.gamebarato.ux.Pesquisar
import com.example.gamebarato.ux.favoritos.Favoritos
import com.example.gamebarato.ux.jogo.MainActivity
import com.example.gamebarato.ux.ofertas.Ofertas
import com.example.gamebarato.ux.ofertas.OfertasFragment

class Notificacoes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificacoes)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, NotificacoesFragment.newInstance())
                .commitNow()
        }

        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoOfertas = findViewById<Button>(R.id.btnOfertas)
        val botaoFavoritos = findViewById<Button>(R.id.btnFavoritos)
        val botaoPerfil = findViewById<Button>(R.id.btnPerfil)

        botaoInicio.setOnClickListener(this::telaInicio)
        botaoPesquisar.setOnClickListener(this::telaPesquisar)
        botaoOfertas.setOnClickListener(this::telaOfertas)
        botaoFavoritos.setOnClickListener(this::telaFavoritos)
        botaoPerfil.setOnClickListener(this::telaPerfil)

    }

    fun telaInicio (view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
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
}