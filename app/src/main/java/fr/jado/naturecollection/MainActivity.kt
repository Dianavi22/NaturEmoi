package fr.jado.naturecollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.jado.naturecollection.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //charger le PlantRepository
        val repo = PlantRepository()

        //mettre à jour la liste de plante
        repo.updateData{
            // injecter le fragment dans notre boîte (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, HomeFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }


    }
}