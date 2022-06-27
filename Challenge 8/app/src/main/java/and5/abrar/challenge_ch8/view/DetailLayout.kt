package and5.abrar.challenge_ch8.view

import and5.abrar.challenge_ch8.model.RespondAnimeItem
import and5.abrar.challenge_ch8.ui.theme.Challengech8Theme
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter


class DetailLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val detailAnime = intent.getParcelableExtra<RespondAnimeItem>("detailanime") as RespondAnimeItem
        setContent {
            Challengech8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting4(detailAnime)
                }
            }
        }
    }
}

@Composable
fun Greeting4(anime : RespondAnimeItem) {
    val mContext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = rememberImagePainter(data = anime.link.thumbnail), contentDescription = "ini gambar", modifier = Modifier.width(200.dp).height(150.dp).padding(end = 10.dp))
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f)
            .padding(5.dp),
            horizontalAlignment = Alignment.Start) {

            Text(
                text = "Judul : ${anime.title}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Genre : ${anime.genre}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Jadwal : ${anime.release}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal
            )

            Text(
                text = "Link : ${anime.link.url}",
                color = Color.DarkGray,
                fontWeight = FontWeight.Normal
            )
            Button(onClick = {
                mContext.startActivity(Intent(mContext,LoginLayout::class.java))
            },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Gray)
            ) {
                Text(text = "LOGOUT")
            }

        }
        
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview4() {
    Challengech8Theme {
    }
}