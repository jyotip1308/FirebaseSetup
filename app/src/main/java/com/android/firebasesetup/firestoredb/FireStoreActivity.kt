package com.android.firebasesetup.firestoredb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.android.firebasesetup.firestoredb.ui.FireStoreScreen
import com.android.firebasesetup.ui.theme.FirebaseSetupTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FireStoreActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseSetupTheme {

                val isInput = remember { mutableStateOf(false) }
                Surface {
                    Scaffold (
                        floatingActionButton = {
                            FloatingActionButton(onClick = {
                                isInput.value = true

                            }) {

                                Icon(Icons.Default.Add, contentDescription = "" )
                            }
                        }
                    ){ innerPadding ->
                            FireStoreScreen(contentPadding = innerPadding,
                                isInput = isInput

                            )
                    }
                }
            }
        }
    }
}