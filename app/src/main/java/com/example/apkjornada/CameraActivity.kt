import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.example.apkjornada.databinding.ActivityCameraBinding
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : AppCompatActivity() {

    private lateinit var cameraExecutor: ExecutorService
    private lateinit var binding: ActivityCameraBinding
    private var cameraProvider: ProcessCameraProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa o executor para a câmera
        cameraExecutor = Executors.newSingleThreadExecutor()

        // Solicita permissão da câmera
        requestCameraPermission()

        // Configura o botão de captura
        binding.captureButton.setOnClickListener {
            // Adicione lógica para capturar a foto aqui
        }
    }

    private fun requestCameraPermission() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    // Se a permissão for concedida, inicia a câmera
                    startCamera()
                } else {
                    // Se a permissão for negada, pode tratar de acordo com sua lógica
                    finish()
                }
            }

        // Solicita permissão da câmera
        requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        // Configura o listener para o futuro do provedor de câmera
        cameraProviderFuture.addListener({
            try {
                // Obtém a instância do provedor de câmera
                cameraProvider = cameraProviderFuture.get()

                // Configura o caso de uso de visualização (Preview)
                val preview = Preview.Builder().build()
                preview.setSurfaceProvider(binding.cameraView.surfaceProvider)

                // Desvincula todos os casos de uso existentes
                cameraProvider?.unbindAll()

                // Liga o caso de uso de visualização à câmera
                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                val camera = cameraProvider?.bindToLifecycle(
                    this, cameraSelector, preview
                )
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    override fun onDestroy() {
        super.onDestroy()

        // Desvincula todos os casos de uso da câmera ao destruir a atividade
        cameraProvider?.unbindAll()

        // Encerra o executor da câmera ao destruir a atividade
        cameraExecutor.shutdown()
    }
}
