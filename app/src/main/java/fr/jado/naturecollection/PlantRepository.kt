package fr.jado.naturecollection

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.jado.naturecollection.PlantRepository.Singleton.databaseRef
import fr.jado.naturecollection.PlantRepository.Singleton.downloadUri
import fr.jado.naturecollection.PlantRepository.Singleton.plantList
import fr.jado.naturecollection.PlantRepository.Singleton.storageReference
import java.util.*

class PlantRepository {

    object Singleton{

        //donner lien pour accéder au bucket
        private val BUCKET_URL: String = "gs://naturecollection-c0bcf.appspot.com/"

        //se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

    // se connecter à la référence "Plants"
    val databaseRef = FirebaseDatabase.getInstance().getReference("Plants")
    //créer une liste qui va contenir nos plantes
    val plantList = arrayListOf<PlantModel>()

        //contenir le lien de l'image courante
        var downloadUri : Uri? = null
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

                // acctionner le callback
                callback()
            }

            override fun onCancelled(p0: DatabaseError) {

            }
        })
    }

    //créer une fonction pour envoyer des fichiers sur le storage
    fun uploadImage(file : Uri, callback: () -> Unit){
        //vérifier que ce fichier n'est pas null
        if(file != null){
            val fileName = UUID.randomUUID().toString() + ".jpg"
            val ref = storageReference.child(fileName)
            val uploadTask = ref.putFile(file)

            //démarrer tâche d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->
                // si il y a eu un problème lors de l'envoi du fichier
                if(!task.isSuccessful){
                    task.exception?.let{throw it}
                }
                return@Continuation ref.downloadUrl
            }).addOnCompleteListener{ task ->

                //vérifier sitout a fonctionné
                if(task.isSuccessful){
                    //récupérer l'image
                    downloadUri = task.result
                    callback()
                }

            }
        }
    }

    // Maj objet plante en BDD
    fun updatePlante(plant:PlantModel) = databaseRef.child(plant.id).setValue(plant)

    //insérer une nouvelle plante en BDD
    fun insertPlant(plant:PlantModel) = databaseRef.child(plant.id).setValue(plant)

//supprimer un plante de la basse
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()

}