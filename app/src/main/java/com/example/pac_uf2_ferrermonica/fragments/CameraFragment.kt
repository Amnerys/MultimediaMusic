package com.example.pac_uf2_ferrermonica.fragments

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.pac_uf2_ferrermonica.R
import com.example.pac_uf2_ferrermonica.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {

    //Esta variable nos revirá para el request code de los permisos
    private val PERMISSIONS_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        val binding: FragmentCameraBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_camera, container, false
        )

        binding.lifecycleOwner = this

        binding.cameraBtn.setOnClickListener {
            // Antes de abrir la cámara, comprobar los permisos para evitar que crashee si no tiene
            checkCameraPermission()
        }

        binding.buttonMusicPlayer.setOnClickListener {
            it.findNavController()
                .navigate(R.id.mainFragment)
        }
        // Inflamos el layout con el fragment
        return binding.root
    }

    private var imageUri: Uri? = null

    //Función para abrir la cámara
    private fun openCameraInterface() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "tomar imagen")
        values.put(MediaStore.Images.Media.DESCRIPTION, "descripción de la imagen")
        imageUri =
            activity?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        // Crear el intent para abrir la cámara
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)

        // Lanzar el intent con el código para pedir los permisos
        startActivityForResult(intent, PERMISSIONS_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Resultado del intent hecho a la cámara
        if (resultCode == Activity.RESULT_OK) {
            // Esto nos serviría para agregar la imagen a un ImageView
            //?.setImageURI(imageUri)
        } else {
            // Lanzar un alert par avisar de que no se ha podido hacer la foto
            showAlert("Failed to take camera picture")
        }
    }

    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(activity as Context)
        builder.setMessage(message)
        builder.setPositiveButton("OK", null)

        val dialog = builder.create()
        dialog.show()
    }

    //TUTORIAL de permisos en Kotlin: https://cursokotlin.com/capitulo-21-gestion-de-permisos-en-android/
    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext() as Activity,
                Manifest.permission.CAMERA
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            //El permiso NO está aceptado --> pedir permiso.
            requestCameraPermission()
        } else {
            //El permiso SI está aceptado --> abrir la cámara.
            openCameraInterface()
        }
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireContext() as Activity,
                Manifest.permission.CAMERA
            )
        ) {
            //En este caso, el usuario ya ha rechazado los permisos anteriormente, tendrá que aceptarlos en ajustes.
            Toast.makeText(requireActivity(), "Por favor, activa los permisos en ajustes de la aplicación.",
                Toast.LENGTH_SHORT).show()
        } else {
            //Se pide al usuario que acepte el permiso por primera vez.
            requestPermissions(
                arrayOf(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE),
                PERMISSIONS_CODE
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == PERMISSIONS_CODE){
            //Aquí recordemos que hemos dado dos tipos de permisos, estarán en posición 0 y 1
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)) {
                //Si el permiso está aceptado, abrir la cámara.
                openCameraInterface()
                Toast.makeText(requireActivity(), "Abriendo la cámara...", Toast.LENGTH_SHORT)
                    .show()
            } else {
                //Permiso rechazado por el usuario.
                Toast.makeText(requireActivity(), "Permiso rechazado.", Toast.LENGTH_SHORT)
                    .show()
            }
            return
        }
    }
}

