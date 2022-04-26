package fr.jado.naturecollection.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import fr.jado.naturecollection.MainActivity
import fr.jado.naturecollection.PlantModel
import fr.jado.naturecollection.PlantRepository
import fr.jado.naturecollection.PlantRepository.Singleton.downloadUri
import fr.jado.naturecollection.R
import java.util.*

class AddPlantFragment (
    private val context:MainActivity
    ) : Fragment(){

    private var file: Uri? = null
    private var uploadedImage:ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
val view = inflater?.inflate(R.layout.fragment_add_plant, container, false)

// récupérer uploadedImage pour lui associer sn composant
        uploadedImage = view.findViewById(R.id.preview_image)

        // récupérer bouton pour charger image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)
        
        // lorsqu'on clique dessus ça ouvre les images du téléphone
        pickupImageButton.setOnClickListener{pickupImage()}

        //récupérer le bouton confirmer
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener{sendForm(view)}

        return view
    }

    private fun sendForm(view: View) {
        val repo = PlantRepository()
        repo.uploadImage(file!!){

            val plantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val plantDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            var grow = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
            var water = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
            val downloadImageUrl = downloadUri


        //créer un nouvel objet PlantModel
        val plant = PlantModel(
            UUID.randomUUID().toString(),
            plantName,
            plantDescription,
            downloadImageUrl.toString(),
            grow,
            water
        )

            //envoyer en BDD
            repo.insertPlant(plant)

        }

    }

    private fun pickupImage()
    {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK)
        {
//vérifier les les données réceptionnées sont nulles
            if(data == null || data.data == null) return

            //récupérer image sélectionnée

            file = data.data

            //maj apercu image
            uploadedImage?.setImageURI(file)


        }
    }

    }