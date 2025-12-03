package com.example.gamebarato.ux

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.gamebarato.R
import com.example.gamebarato.ux.favoritos.Favoritos
import com.example.gamebarato.ux.jogo.MainActivity
import com.example.gamebarato.ux.ofertas.Ofertas
import com.example.gamebarato.ux.pesquisar.Pesquisar

class Perfil : AppCompatActivity() {
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 600 && resultCode == RESULT_OK) {
            val imagemPerfil = findViewById<ImageView>(R.id.imgPerfil)
            val imagemAlterada = data?.extras?.get("data") as? Bitmap
            imagemPerfil.setImageBitmap(imagemAlterada)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val botaoInicio = findViewById<Button>(R.id.btnInicio)
        val botaoPesquisar = findViewById<Button>(R.id.btnPesquisar)
        val botaoOfertas = findViewById<Button>(R.id.btnOfertas)
        val botaoFavoritos = findViewById<Button>(R.id.btnFavoritos)
        val botaoAlterarFoto = findViewById<ImageButton>(R.id.btnAlterarFoto)
        val botaoDeletarConta = findViewById<Button>(R.id.btnDeletarConta)

        botaoInicio.setOnClickListener(this::telaInicio)
        botaoPesquisar.setOnClickListener(this::telaPesquisar)
        botaoOfertas.setOnClickListener(this::telaOfertas)
        botaoFavoritos.setOnClickListener(this::telaFavoritos)
        botaoAlterarFoto.setOnClickListener(this::alterarFoto)
        botaoDeletarConta.setOnClickListener(this::deletarConta)
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
    fun alterarFoto(view: View) {
        var sim = false

        if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            sim = true
        } else {
            requestPermissions(arrayOf(Manifest.permission.CAMERA), 400)
        }
        if (sim) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 600)
        } else {
            Toast.makeText(this, "Você não autorizou o uso da câmera", Toast.LENGTH_LONG).show()
        }
    }
    fun deletarConta (view: View) {
            val dialogo = AlertDialog.Builder(this)
            dialogo.setTitle("Remover Conta")
            dialogo.setMessage("Você quer deletar sua conta?")
            dialogo.setPositiveButton("Deletar"){_,_ -> null}
            dialogo.setNegativeButton("Cancelar"){_,_ -> null}
            dialogo.show()
    }
}