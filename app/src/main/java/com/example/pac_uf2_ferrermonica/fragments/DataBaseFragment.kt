package com.example.pac_uf2_ferrermonica.fragments

import android.os.Bundle
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
import com.example.pac_uf2_ferrermonica.databinding.FragmentDataBaseBinding

class DataBaseFragment : Fragment() {

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

        val binding: FragmentDataBaseBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_data_base, container, false
        )

        Toast.makeText(requireActivity(), "Entrando en la Base de Datos.", Toast.LENGTH_SHORT)
            .show()

        binding.lifecycleOwner = this

        //Room crea automáticamente las tablas al insertar datos, así que he añadido la funcionalidad de borrar las tablas
        binding.buttonDeleteTable.setOnClickListener {
            songViewModel.deleteTable()
            Toast.makeText(requireActivity(), "Datos eliminados correctamente.", Toast.LENGTH_SHORT)
                .show()
        }

        binding.buttonInsertData.setOnClickListener {
            it.findNavController()
                .navigate(R.id.insertarFragment)
        }

        binding.buttonViewData.setOnClickListener {
            it.findNavController()
                .navigate(R.id.consultaFragment)
        }
        binding.buttonMusicPlayer.setOnClickListener {
            it.findNavController()
                .navigate(R.id.mainFragment)
        }
        // Inflar el layout para este fragment
        return binding.root
    }

}