package com.example.gamebarato.ux

import android.Manifest
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
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.gamebarato.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CadastroPerfil : AppCompatActivity() {

    private var enderecoCadastrado: String = ""
    protected val resultado = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){resultadoAtividade ->
        if(resultadoAtividade.resultCode == RESULT_OK){
            val data: Intent? = resultadoAtividade.data
            val resultadoFinal = data?.getStringExtra("EnderecoCadastrado")
            enderecoCadastrado = resultadoFinal!!
            Toast.makeText(this, enderecoCadastrado, Toast.LENGTH_LONG).show()
        }
    }

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
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro_perfil)
       //val cep = intent.getStringExtra("CEP")


        val botaoCadastrarNovoEnd = findViewById<Button>(R.id.btnCadastrarNovoEndereco)
        val botaoCriarCadstro = findViewById<Button>(R.id.btnCriarNovoCadastro)
        val botaoAlterarFoto = findViewById<ImageButton>(R.id.btnAlterarFoto)
        val botaoVoltar = findViewById<FloatingActionButton>(R.id.fabVoltar)


        botaoCadastrarNovoEnd.setOnClickListener(this::telaNovoEndereco)
        botaoCriarCadstro.setOnClickListener(this::telaCadastroCriado)
        botaoAlterarFoto.setOnClickListener(this::alterarFoto)
        botaoVoltar.setOnClickListener(this::voltar)
    }

    fun telaNovoEndereco (view: View) {
        val intent = Intent(this, AdicionarEndereco::class.java)
        startActivity(intent)
    }

    fun telaCadastroCriado (view: View) {
        finish()
    }

    fun alterarFoto (view: View) {
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

    fun voltar (view: View) {
        finish()
    }
}