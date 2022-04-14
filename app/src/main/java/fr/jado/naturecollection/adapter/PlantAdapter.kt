package fr.jado.naturecollection.adapter

import android.net.Uri
import android.view.AttachedSurfaceControl
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.jado.naturecollection.MainActivity
import fr.jado.naturecollection.PlantModel
import fr.jado.naturecollection.R

class PlantAdapter(
    private val context: MainActivity,
    private val plantList: List<PlantModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    // Boîte pour ranger tous les composants à controller
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      val view = LayoutInflater
          .from(parent.context)
          .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
// récupérer les informations de la plante
        val currentPlant = plantList[position]

        // utiliser glide pour récupérer l'image ) partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)

    }

    override fun getItemCount(): Int = plantList.size

}