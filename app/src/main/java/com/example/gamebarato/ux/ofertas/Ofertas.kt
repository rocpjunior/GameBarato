package com.example.gamebarato.ux.ofertas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.ux.Perfil
import com.example.gamebarato.ux.pesquisar.Pesquisar
import com.example.gamebarato.R
import com.example.gamebarato.ux.favoritos.Favoritos
import com.example.gamebarato.ux.jogo.MainActivity

class Ofertas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ofertas)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, OfertasFragment.newInstance())
                .commitNow()
        }

        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoFavoritos = findViewById<Button>(R.id.btnFavoritos)
        val botaoPerfil = findViewById<Button>(R.id.btnPerfil)

        botaoInicio.setOnClickListener(this::telaInicio)
        botaoPesquisar.setOnClickListener(this::telaPesquisar)
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
    fun telaFavoritos (view: View) {
        val intent = Intent(this, Favoritos::class.java)
        startActivity(intent)
    }
    fun telaPerfil (view: View) {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }
}