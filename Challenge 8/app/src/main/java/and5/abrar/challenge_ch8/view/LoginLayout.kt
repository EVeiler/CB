package and5.abrar.challenge_ch8.view

import and5.abrar.challenge_ch8.ui.theme.Challengech8Theme
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class LoginLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challengech8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    val mcontext = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .padding(15.dp)
        .height(200.dp)
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = username , onValueChange ={username = it},label = {Text("username")} )
        TextField(value = password , onValueChange ={password = it},label = {Text("password")} )
        Button(onClick = {
            mcontext.startActivity(Intent(mcontext,HomeLayout::class.java))
        },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(text = "Login")
        }
        Button(onClick = {
            mcontext.startActivity(Intent(mcontext,RegisterLayout::class.java))
        },
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Text(text = "Register")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Challengech8Theme {
        Greeting()
    }
}