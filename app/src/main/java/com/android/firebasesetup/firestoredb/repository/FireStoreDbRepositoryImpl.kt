package com.android.firebasesetup.firestoredb.repository

import com.android.firebasesetup.firestoredb.FireStoreModelResponse
import com.android.firebasesetup.utility.ResultState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FireStoreDbRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : FireStoreRepository{
    override fun insert(item: FireStoreModelResponse.FireStoreItem): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        db.collection("user")
            .add(item)
            .addOnSuccessListener {
                trySend(ResultState.Success("Data is inserted with ${it.id}"))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }

        awaitClose{
            close()
        }
    }

    override fun getItems(): Flow<ResultState<List<FireStoreModelResponse>>> = callbackFlow{
        trySend(ResultState.Loading)
        db.collection("user")
            .get()
            .addOnSuccessListener {
                val items = it.map { data->
                    FireStoreModelResponse(
                        item = FireStoreModelResponse.FireStoreItem(
                            title = data["title"] as String?,
                            description = data["description"] as String?
                        ),
                        key = data.id
                    )
                }
                trySend(ResultState.Success(items))
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
        }

        awaitClose {
            close()
        }
    }

    override fun delete(key: String): Flow<ResultState<String>> = callbackFlow {
        trySend(ResultState.Loading)
        db.collection("user")
            .document(key)
            .delete()
            .addOnCompleteListener{
                if (it.isSuccessful) {
                    trySend(ResultState.Success("Deleted Successfully.."))
                }
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose{
            close()
        }
    }

    override fun update(item: FireStoreModelResponse): Flow<ResultState<String>> = callbackFlow{
        trySend(ResultState.Loading)
        val map = HashMap<String, Any>()
        map["title"] = item.item?.title!!
        map["description"] = item.item.description!!

        db.collection("user")
            .document(item.key!!)
            .update(map)
            .addOnCompleteListener{
                if (it.isSuccessful){
                    trySend(ResultState.Success("Updated Successfully"))
                }
            }.addOnFailureListener {
                trySend(ResultState.Failure(it))
            }
        awaitClose {
            close()
        }
    }
}