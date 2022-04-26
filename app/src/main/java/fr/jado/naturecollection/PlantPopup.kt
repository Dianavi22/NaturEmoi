package fr.jado.naturecollection

import android.app.Dialog
import android.media.Image
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.jado.naturecollection.R
import fr.jado.naturecollection.adapter.PlantAdapter
import java.util.*

class PlantPopup(
    private val adapter: PlantAdapter,
    private val currentPlant: PlantModel
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plant_details)
        setupComponents()
        setupCloseButton()
        setUpDeleteButton()
        setupLikeButton()
    }

    private fun setUpDeleteButton() {
      findViewById<ImageView>(R.id.delete_button).setOnClickListener{
          // supprimer la plante de la BDD
          val repo = PlantRepository()
          repo.deletePlant(currentPlant)
          dismiss()
      }
    }

    private fun updateStar(button:ImageView){
        if(currentPlant.liked)
        {
            button.setImageResource(R.drawable.ic_star)
        }
        else
        {
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setupLikeButton() {
        //récupérer

        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)
        //interaction

        starButton.setOnClickListener{
            currentPlant.liked = !currentPlant.liked
            val repo = PlantRepository()
            repo.updatePlante(currentPlant)
            updateStar(starButton)


        }
    }

    private fun setupCloseButton() {
       findViewById<ImageView>(R.id.close_button).setOnClickListener{
           //fermer la fenêtre
           dismiss()
       }
    }

    private fun setupComponents() {
        //actualiser image de la plante
        val plantImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)

        //actualiser Nom de la plante
        findViewById<TextView>(R.id.popup_plant_name).text = currentPlant.name

        //actualiser la description
        findViewById<TextView>(R.id.popup_plant_description_subtitle).text = currentPlant.description

        //actualiser la croissance
        findViewById<TextView>(R.id.popup_plant_grow_subtitle).text = currentPlant.grow

        //actualiser la consommation d'eau
        findViewById<TextView>(R.id.popup_plant_water_subtitle).text = currentPlant.water

    }



}