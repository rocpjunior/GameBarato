package com.example.gamebarato.ux.favoritos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.ux.ofertas.Ofertas
import com.example.gamebarato.ux.Perfil
import com.example.gamebarato.ux.Pesquisar
import com.example.gamebarato.R
import com.example.gamebarato.ux.jogo.MainActivity

class Favoritos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FavoritosFragment.Companion.newInstance())
                .commitNow()
        }

        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoOfertas = findViewById<Button>(R.id.btnOfertas)
        val botaoPerfil = findViewById<Button>(R.id.btnPerfil)

        botaoInicio.setOnClickListener(this::telaInicio)
        botaoPesquisar.setOnClickListener(this::telaPesquisar)
        botaoOfertas.setOnClickListener(this::telaOfertas)
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
    fun telaPerfil (view: View) {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }
}