package fr.jado.naturecollection

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import fr.jado.naturecollection.R
import fr.jado.naturecollection.adapter.PlantAdapter

class PlantPopup(
    private val adapter: PlantAdapter
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plant_details)
    }

}