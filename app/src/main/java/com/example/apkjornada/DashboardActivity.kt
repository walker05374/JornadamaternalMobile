package com.example.apkjornada

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val notiti = Notiti(this)
        notiti.showNotification()
    }


    fun abrirAtividade(view: View) {

        val tag = view.tag?.toString()


        when (tag) {
            "inicio" -> login()
            "perfil0" -> Perfil()
            "Construcao" -> Construcao()
            "CameraActivity" -> CameraActivity()
            "configuracoes" -> Construcao()
            "chat0" -> Construcao()
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
    private fun Construcao() {
        val intent = Intent(this, Construcao::class.java)
       startActivity(intent)
    }
    private fun CameraActivity() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }
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
