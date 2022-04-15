package fr.jado.naturecollection.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.jado.naturecollection.MainActivity
import fr.jado.naturecollection.PlantModel
import fr.jado.naturecollection.PlantRepository.Singleton.plantList
import fr.jado.naturecollection.R
import fr.jado.naturecollection.adapter.PlantAdapter
import fr.jado.naturecollection.adapter.PlantItemDecoration

class HomeFragment(private val context: MainActivity) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home, container, false)



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