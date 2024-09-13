package com.example.android_gerenciaestado

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_gerenciaestado.ui.theme.Android_GerenciaEstadoTheme
import com.example.android_gerenciaestado.ui.viewmodels.ContadorViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Android_GerenciaEstadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
//                        ContadorStateful(
//                            modifier = Modifier.padding(innerPadding)
//                        )
                        val viewModel by viewModels<ContadorViewModel>()
                        val contador by viewModel.contador.collectAsState()
                        ContadorStateless(
                            contador,
                            onIncrementContador = { viewModel.incrementar() },
                            onDecrementoContador = { viewModel.decremento() },
                            modifier = Modifier.padding(innerPadding)
                        )
                        ContadorStateless(
                            contador,
                            onIncrementContador = {viewModel.incrementar()},
                            onDecrementoContador = { viewModel.decremento() },
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ContadorStateless(contador: Int, onIncrementContador: () -> Unit, onDecrementoContador: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingValues(24.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Contador: $contador"
        )
        Row {
            Button(
                onClick = onIncrementContador,
                modifier = Modifier.padding(PaddingValues(6.dp))
            ) {
                Text("Incremento")
            }
            Button(
                onClick = onDecrementoContador,
                modifier = Modifier.padding(PaddingValues(6.dp))
            ) {
                Text(text = "Decremento")
            }
        }
    }
}


@Composable
fun ContadorStateful(modifier: Modifier = Modifier) {
    var contador by remember { mutableIntStateOf(0) }

    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Contador: $contador"
        )
        Button(onClick = {contador++}) {
            Text("Incremente aqui")

        }

    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Android_GerenciaEstadoTheme {
    }
}