package com.android.firebasesetup.firebaseAuth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import com.android.firebasesetup.firebaseAuth.ui.AuthScreen
import com.android.firebasesetup.firebaseAuth.ui.PhoneAuthScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            Surface {
                Scaffold (
                    floatingActionButton = {
                        FloatingActionButton(onClick = {

                        }) {

                            Icon(Icons.Default.Add, contentDescription = "" )
                        }
                    }
                ){ innerPadding ->
//                    AuthScreen(
//                        contentPadding = innerPadding,
//
//                    )

                    PhoneAuthScreen(activity = this, contentPadding = innerPadding)
                }
            }
        }
    }
}