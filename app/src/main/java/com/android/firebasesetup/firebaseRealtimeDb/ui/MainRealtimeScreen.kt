package com.android.firebasesetup.firebaseRealtimeDb.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MainRealtimeScreen(){

    val isInsert = remember {
        mutableStateOf(false)
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = {
                isInsert.value = true

            }) {

           Icon(Icons.Default.Add, contentDescription = "" )
            }
        }
    ){ innerPadding ->
        RealtimeScreen(contentPadding = innerPadding,
           isInsert =  isInsert
        )
    }
}