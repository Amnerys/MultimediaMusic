package com.example.pac_uf2_ferrermonica.fragments

import android.content.res.Configuration
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pac_uf2_ferrermonica.R
import com.example.pac_uf2_ferrermonica.R.layout
import com.example.pac_uf2_ferrermonica.R.raw
import com.example.pac_uf2_ferrermonica.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    //Recordamos que usamos Navigation y Fragments para hacer una Single Activity App
    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentMainBinding = DataBindingUtil.inflate(
            inflater, layout.fragment_main, container, false
        )

        binding.lifecycleOwner = this
        createMediaPlayer()

        //Botón para iniciar la canción o seguir con ella donde se había pausado.
        binding.playBtn.setOnClickListener {
            mediaPlayer.start()
            Toast.makeText(context, "Reproduciendo...", Toast.LENGTH_SHORT).show()
        }

        //Botón para PAUSAR. Al darle a play seguirá donde lo haya pausado.
        binding.pauseBtn.setOnClickListener {
            mediaPlayer.pause()
            Toast.makeText(context, "En pausa...", Toast.LENGTH_SHORT).show()
        }

        //Botón para PARAR la canción. Al darle a play empezará de 0.
        binding.stopBtn.setOnClickListener {
            mediaPlayer.stop()
            Toast.makeText(context, "Parado...", Toast.LENGTH_SHORT).show()
            //Cuando se para el mediaPlayer, hay que volver a prepararlo
            createMediaPlayer()
        }

        // Botón para ir a la Base de Datos (pantalla 2)
        binding.buttonBd.setOnClickListener {
            it.findNavController()
                .navigate(R.id.dataBaseFragment)
        }

        // Botón para ir a la cámara (pantalla 3)
        binding.buttonCamera.setOnClickListener {
            it.findNavController()
                .navigate(R.id.cameraFragment)
        }

        // Botón para ir a la galería (pantalla 4)
        binding.buttonGallery.setOnClickListener {
            it.findNavController()
                .navigate(R.id.galleryFragment)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    //Función para crear el objeto MediaPlayer con la canción que tenemos en la carpeta raw
    fun createMediaPlayer(){
        //Canción obtenida de: http://arabix.site/yt/6Mp0spcvKY8/Sagi+Abitbul+-+Macha+%28Official+Audio%29+%282%29.html
        mediaPlayer = MediaPlayer.create(requireActivity(), raw.macha)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        super.onDestroy()
    }

    //Si el usuario minimiza la app, pausar el mediaPlayer
    override fun onStop() {
        super.onStop()
        mediaPlayer.pause()
    }

    //Si el usuario cierra la app o cambia de pantalla, parar el mediaPlayer
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
    }

}