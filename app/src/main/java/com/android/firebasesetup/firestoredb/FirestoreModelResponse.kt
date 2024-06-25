package com.android.firebasesetup.firestoredb

data class FireStoreModelResponse(
    val item:FireStoreItem?,
    val key:String? = ""
){
    data class FireStoreItem(
        val title: String? = "",
        val description: String? = ""
    )
}
