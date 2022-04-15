package fr.jado.naturecollection

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.jado.naturecollection.PlantRepository.Singleton.databaseRef
import fr.jado.naturecollection.PlantRepository.Singleton.plantList

class PlantRepository {

    object Singleton{

    // se connecter à la référence "Plants"
    val databaseRef = FirebaseDatabase.getInstance().getReference("Plants")
    //créer une liste qui va contenir nos plantes
    val plantList = arrayListOf<PlantModel>()
    }

    fun updateData(callback: () -> Unit){
        //absorber les données depuis la databaseRef -> liste de plantes
        databaseRef.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                //retirer les anciennes plantes de la liste
                plantList.clear()
               //récolter la liste
                for (ds in snapshot.children){
                    //construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)
                    // vérifier que la plante n'est pas null
                    if(plant!=null){
                        plantList.add(plant)
                    }
                }

                // accctionner le callback
                callback()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    // Maj objet plante en BDD
    fun updatePlante(plant:PlantModel) = databaseRef.child(plant.id).setValue(plant)



}