package com.example.apkjornada

import android.location.Location
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices

class googlelocalizacao : AppCompatActivity(), GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener {

    private var googleApiClient: GoogleApiClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        googleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .build()

        // Não é necessário conectar aqui, pois o método onStop já trata disso
        // googleApiClient.connect()
    }

    override fun onStop() {
        super.onStop()
        pararConexaoComGoogleApi()
    }

    fun pararConexaoComGoogleApi() {
        if (googleApiClient!!.isConnected) {
            googleApiClient!!.disconnect()
        }
    }

    override fun onConnected(bundle: Bundle?) {
        // Método de callback quando a conexão é bem-sucedida
    }

    override fun onConnectionSuspended(i: Int) {
        // Aguardando o GoogleApiClient reestabelecer a conexão.
    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        // A conexão com o Google API falhou!
        pararConexaoComGoogleApi()
    }

    init {
        googleApiClient = GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
    }
}
