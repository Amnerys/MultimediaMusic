package com.example.pac_uf2_ferrermonica.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pac_uf2_ferrermonica.R
import com.example.pac_uf2_ferrermonica.databinding.FragmentGalleryBinding

class GalleryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding: FragmentGalleryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_gallery, container, false
        )

        binding.lifecycleOwner = this

        binding.buttonMusicPlayer.setOnClickListener {
            it.findNavController()
                .navigate(R.id.mainFragment)
        }
        // Inflar el layout para este fragment
        return binding.root
    }

}