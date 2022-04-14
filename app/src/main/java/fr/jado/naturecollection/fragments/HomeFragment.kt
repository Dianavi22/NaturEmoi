package fr.jado.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.jado.naturecollection.MainActivity
import fr.jado.naturecollection.PlantModel
import fr.jado.naturecollection.R
import fr.jado.naturecollection.adapter.PlantAdapter
import fr.jado.naturecollection.adapter.PlantItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)

        //Créer une liste qui va stocker ces plantes
        val plantList = arrayListOf<PlantModel>()

        //Eregistrer une première plante dans la liste (pissenlit)

        plantList.add(PlantModel(
            name = "Pissenlit",
            description = "Jaune soleil",
            imageUrl = "https://media.ooreka.fr/public/image/plant/125/mainImage-full-9587419.jpg",
            liked = false

        ))

        //Eregistrer une seconde plante dans la liste (rose)

        plantList.add(PlantModel(
            name = "Rose",
            description = "ça pique un peu",
            imageUrl = "https://cdn2.pepinieres-naudet.com/boutique/2663-large_default/rosier-kimono-rose-pale-fleurs-groupees.jpg",
            liked = false

        ))

        //Eregistrer une Troisième plante dans la liste (cactus)

        plantList.add(PlantModel(
            name = "Cactus",
            description = "ça pique beaucoup",
            imageUrl = "https://www.plantedshack.com/wp-content/uploads/2020/02/Golden-Ball-Cactus-610x420.jpg",
            liked = false

        ))

        //Eregistrer une seconde plante dans la liste (Tulipe)

        plantList.add(PlantModel(
            name = "Tulipe",
            description = "c'est beau",
            imageUrl = "https://photos.gammvert.fr/v5/products/fiche_590/47539-tulipe-triomphe-washington-2.jpg",
            liked = false

        ))

        //recuperer le premier recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_horizontal_plant)

        //recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context, plantList, R.layout.item_vertical_plante)
verticalRecyclerView.addItemDecoration(PlantItemDecoration())

        return view
    }

}