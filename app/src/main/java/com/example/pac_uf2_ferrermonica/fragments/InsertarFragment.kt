package com.example.pac_uf2_ferrermonica.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.pac_uf2_ferrermonica.R
import com.example.pac_uf2_ferrermonica.SongApplication
import com.example.pac_uf2_ferrermonica.SongViewModel
import com.example.pac_uf2_ferrermonica.SongViewModelFactory
import com.example.pac_uf2_ferrermonica.database.Song
import com.example.pac_uf2_ferrermonica.databinding.FragmentInsertarBinding


class InsertarFragment : Fragment() {

    private val songViewModel: SongViewModel by viewModels {
        SongViewModelFactory((requireActivity().applicationContext as SongApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        Toast.makeText(requireActivity(), "Insertar Música Actividad.", Toast.LENGTH_SHORT)
            .show()

        val binding: FragmentInsertarBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_insertar, container, false
        )

        binding.lifecycleOwner = this

        // Funcionalidad para insertar datos
        binding.insertar.setOnClickListener {
            //Comprobamos que no se deje campos en blanco
            if (TextUtils.isEmpty(binding.artista.text.toString()) || TextUtils.isEmpty(binding.cancion.text.toString())
                || TextUtils.isEmpty(binding.album.text.toString())
            ) {
                Toast.makeText(requireActivity(), "Por favor, rellene todos los campos.", Toast.LENGTH_SHORT)
                    .show()

            } else { //Los campos han sido rellenado correctamente
                val song = Song(binding.artista.text.toString(), binding.cancion.text.toString(),
                    binding.album.text.toString())
                songViewModel.insert(song)

                Toast.makeText(requireActivity(), "Datos introducidos correctamente.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        //Funcionalidad para volver al fragment de la base de datos
        binding.volver.setOnClickListener {
            it.findNavController()
                .navigate(R.id.dataBaseFragment)
        }

        // Inflar el layout para este fragment
        return binding.root
    }
}