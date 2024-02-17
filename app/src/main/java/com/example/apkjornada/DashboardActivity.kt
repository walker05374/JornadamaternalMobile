package com.example.apkjornada

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    fun abrirAtividade(view: View) {
        // Identifique o botão clicado usando a tag
        val tag = view.tag?.toString()

        // Abra a atividade correspondente
        when (tag) {
            "inicio" -> login()
            "perfil0" -> Perfil()
            "noticias0" -> Perfil()
            "teste0" -> Perfil()
            "configuracoes" -> Perfil()
            "chat0" -> cadastro()
        }
    }

    private fun login() {
        val intent = Intent(this, login::class.java)
        startActivity(intent)
    }

    private fun Perfil() {
        val intent = Intent(this, Perfil::class.java)
        startActivity(intent)
    }
    //private fun noticias0() {
        //val intent = Intent(this, noticias0::class.java)
       // startActivity(intent)
    //}
    //private fun teste0() {
       // val intent = Intent(this, teste0::class.java)
       // startActivity(intent)
    //}
    //private fun configuracoes() {
       // val intent = Intent(this, configuracoes::class.java)
       // startActivity(intent)
    //}
    //private fun chatbot() {
       // val intent = Intent(this, cadastro::class.java)
       // startActivity(intent)
    //}
    private fun cadastro() {
       val intent = Intent(this, cadastro::class.java)
       startActivity(intent)
    }
}
