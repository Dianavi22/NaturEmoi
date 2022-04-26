package fr.jado.naturecollection.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.jado.naturecollection.*

class PlantAdapter(
    val context: MainActivity,
    private val plantList: List<PlantModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    // Boîte pour ranger tous les composants à controller
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage = view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView? = view.findViewById(R.id.name_item)
        val plantDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
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

        //reccuperer le repository
        val repo = PlantRepository()

        // utiliser glide pour récupérer l'image ) partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)

        // MAJ nom de la plante
        holder.plantName?.text = currentPlant.name
        // MAJ description de la plante
        holder.plantDescription?.text = currentPlant.description

        // vérifier si la plante est liked
        if(currentPlant.liked)
        {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }

        for (i in plantList.indices) {

            if (i == plantList.indices.last) {
                //ajouter une margin sur la dernière plannte
            } else {

            }
        }


        // rajouter une interaction sur étoile
        holder.starIcon.setOnClickListener{
            //inverser si le bouton est like ou unlike
            currentPlant.liked = !currentPlant.liked
            // mettre à jour l'objet Plante
            repo.updatePlante(currentPlant)
        }

        //interraction lors du clic sur une plante
        holder.itemView.setOnClickListener{
            //afficher la popup
            PlantPopup(this, currentPlant).show()
        }


    }



    override fun getItemCount(): Int = plantList.size

}