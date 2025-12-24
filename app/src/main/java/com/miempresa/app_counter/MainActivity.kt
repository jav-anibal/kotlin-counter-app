package com.miempresa.app_counter


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.miempresa.app_counter.ui.theme.App_counterTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App_counterTheme {
                CounterScreen()
            }
        }

    }


}


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CounterScreen() {
    var counter by remember { mutableStateOf(0) }
    var colorCounter by remember { mutableStateOf(Color.Black) }
    val banner = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()



    Scaffold(
        snackbarHost = { SnackbarHost(banner) },

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "COUNTER APP",
                        style = MaterialTheme.typography.titleLarge,
                        color = colorScheme.primary
                    )
                }

            )
        }

    ) { innerPadding ->


        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Contador = ",
                        style = MaterialTheme.typography.headlineLarge,
                    )

                    Text(
                        text = " $counter",
                        style = MaterialTheme.typography.headlineLarge,
                        color = colorCounter
                    )


                }
                Spacer(Modifier.height(25.dp))

                Button(
                    onClick = {
                        counter++
                        colorCounter = Color.Green
                    }, modifier = Modifier.size(150.dp, 50.dp)

                ) {
                    Text("UP")
                }
                Spacer(Modifier.height(15.dp))

                Button(onClick = {
                    counter--
                    colorCounter = Color.Red

                    if (counter < 0) {
                        scope.launch { banner.showSnackbar("Has llegado al límite inferior") }
                    }

                }, modifier = Modifier.size(150.dp, 50.dp)) {
                    Text("DOWN")
                }
            }

        }

    }

}