package com.android.firebasesetup.firebaseAuth.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.firebasesetup.common.CommonDialog
import com.android.firebasesetup.firebaseAuth.AuthUser
import com.android.firebasesetup.utility.ResultState
import com.android.firebasesetup.utility.showMsg
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(
    contentPadding: PaddingValues,
    viewModel: AuthViewModel = hiltViewModel()
) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email1 by remember { mutableStateOf("") }
    var password1 by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var isDialog by remember { mutableStateOf(false) }


    if (isDialog){
        CommonDialog()
    }

    LazyColumn (modifier = Modifier.padding(20.dp)
    ){
        item {
            Column(modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(text = "Register")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = email, onValueChange = {
                    email = it
                },
                    placeholder = { Text(text = "Enter Email")}
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password, onValueChange = {
                    password = it
                },
                    placeholder = { Text(text = "Enter Password")}
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    scope.launch (Dispatchers.Main) {
                        viewModel.createUser(
                            AuthUser(
                                email, password
                            )
                        ).collect {
                            isDialog = when(it){
                                is ResultState.Success -> {
                                    context.showMsg(it.data)
                                    false
                                }
                                is ResultState.Failure -> {
                                    context.showMsg(it.msg.toString())
                                    false
                                }
                                is ResultState.Loading -> {
                                    true
                                }
                            }
                        }

                    }
                }) {
                    Text(text = "Register")
                }
            }
        }

        item {
            Column(modifier = Modifier.fillMaxWidth().padding(top = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally)
            {
                Text(text = "Login")
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = email1, onValueChange = {
                    email1 = it
                },
                    placeholder = { Text(text = "Enter Email")}
                )

                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password1, onValueChange = {
                    password1 = it
                },
                    placeholder = { Text(text = "Enter Password")}
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(onClick = {
                    scope.launch (Dispatchers.Main) {
                        viewModel.loginUser(
                            AuthUser(
                                email1, password1
                            )
                        ).collect {
                            isDialog = when(it){
                                is ResultState.Success -> {
                                    context.showMsg(it.data)
                                    false
                                }
                                is ResultState.Failure -> {
                                    context.showMsg(it.msg.toString())
                                    false
                                }
                                is ResultState.Loading -> {
                                    true
                                }
                            }
                        }

                    }
                }) {
                    Text(text = "Login")
                }
            }
        }

    }

}