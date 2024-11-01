package com.example.basiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.coerceAtLeast
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
fun Myapp(modifier:Modifier = Modifier) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

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
    // T : this is state should be hosted
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
    names: List<String> = List(1000) { "$it"}
){
    LazyColumn(modifier = modifier.padding(vertical = 4.dp,)) {
        items (items = names) { name ->
            Greeting(name= name)
        }
    }
}


@Composable
private fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }


    //var expanded by rememberSaveable  { mutableStateOf(false) }

//    val extraPadding by animateDpAsState(
//        if (expanded) 48.dp else 0.dp,
//        animationSpec = spring(
//            dampingRatio = Spring.DampingRatioMediumBouncy,
//            stiffness = Spring.StiffnessLow
//        )
//    )
//    Surface(
//        color = MaterialTheme.colorScheme.primary,
//        modifier = modifier
//            .padding(vertical = 4.dp, horizontal = 8.dp)
//    ){
//        //columns and rows
//        Row(modifier= Modifier.padding(24.dp)){
//            Column(modifier = modifier
//                .weight(1f)
//                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
//            ) {
//                Text(text = "Hello")
//                Text(text = name)
//            }
//            //button
//            ElevatedButton(
//                onClick = { expanded =!expanded },
//            ) {
//                Text(if (expanded) "show less" else "Show more")
//            }
//
//        }
//    }
}

@Composable
private fun CardContent(name:String){
    var expanded by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize (
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ){
        Column (
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ){
            Text(text = "hello")
            Text(
                text= name, style = MaterialTheme.typography.headllineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy," + "padding theme elit, sed do bouncy.").repeat(4),
                )
            }
        }
        IconButton(onClick = {expanded = !expanded}) {
            Icon(
                imageVector = if (expanded) Filled.Expandless else Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
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

@Preview(showBackground = true , widthDp = 320 , heightDp = 320)
@Composable
fun OnboardingPreview(){
    BasicCodelabTheme {
        OnboardingScreen(onContinueClicked = {}) // do nothing on click
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


