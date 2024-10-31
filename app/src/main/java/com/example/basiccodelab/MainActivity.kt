package com.example.basiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basiccodelab.ui.theme.BasicCodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BasicCodelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Myapp(modifier: Modifier = Modifier) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface {
        if (shouldShowOnboarding) {
            OnboardingScreen( onContinueClicked = { shouldShowOnboarding = false})
        } else {
            Greetings()
        }
    }
}

// state hosting onboarding screen
@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier =Modifier
) {
    // TODO : this is state should be hosted
    //var shouldShowOnboarding by remember { mutableStateOf(true) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun Greetings(
    modifier: Modifier =Modifier,
    names: List<String> = listOf("World", "compose")
){
    Column(modifier = modifier.padding(vertical = 4.dp,)) {
        for (name in names) {
            Greeting(name= name)
        }
    }
}

@Preview(showBackground = true , widthDp = 320 , heightDp = 320)
@Composable
fun OnboardingPreview(){
    BasicCodelabTheme {
        OnboardingScreen(onContinueClicked = {}) // do nothing on click
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }
    val extraPadding = if (expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        //columns and rows
        Row(modifier= Modifier.padding(24.dp)){
            Column(modifier = modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello")
                Text(text = name)
            }
            //button
            ElevatedButton(
                onClick = { expanded.value =!expanded.value },
            ) {
                Text(if (expanded.value) "show less" else "Show more")
            }

        }
    }
}


@Preview(showBackground = true , widthDp = 320)
@Composable
fun GreetingPreview() {
    BasicCodelabTheme {
        Greetings()
    }
}

// lastly
@Preview
@Composable
fun MyAppPreview() {
    BasicCodelabTheme {
        Myapp(Modifier.fillMaxSize())
    }
}


