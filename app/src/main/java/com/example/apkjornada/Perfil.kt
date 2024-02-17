package com.example.apkjornada

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class Perfil : AppCompatActivity() {

    private val CHANNEL_ID = "canal_perfil"
    private val NOTIFICATION_ID = 123

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil)

        val botaoEditar: Button = findViewById(R.id.editButton)
        botaoEditar.setOnClickListener {
            // Cria um intent para iniciar a EditProfileActivity
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)

            // Exibe uma notificação ao entrar na tela do perfil
            exibirNotificacao()
        }
    }

    private fun exibirNotificacao() {
        // Cria um canal de notificação para dispositivos Android Oreo e superior
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "canal_perfil"
            val channelName = "Canal de Notificações do Perfil"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)

            val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        val intent = Intent(this, Perfil::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Bem-vindo ao seu Perfil")
            .setContentText("Você entrou na tela de perfil.")
            .setSmallIcon(R.drawable.baseline_home_24)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, notification)




    }
}
