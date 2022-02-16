package com.example.pac_uf2_ferrermonica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pac_uf2_ferrermonica.*
import com.example.pac_uf2_ferrermonica.databinding.FragmentConsultaBinding

class ConsultaFragment : Fragment() {

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

        val binding: FragmentConsultaBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_consulta, container, false
        )

        binding.lifecycleOwner = this

        val adapter = SongListAdapter()
        binding.recyclerview.adapter = adapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireActivity())

        songViewModel.allWords.observe(requireActivity()) { songs ->
            // Actualizar la copia de la caché con las palabras del adapter
            songs.let { adapter.submitList(it) }
        }

        binding.fab.setOnClickListener {
            it.findNavController()
                .navigate(R.id.dataBaseFragment)
        }

        // Inflar el layout para este fragment
        return binding.root
    }

}