package and5.abrar.challenge_ch8.view

import and5.abrar.challenge_ch8.ui.theme.Challengech8Theme
import android.content.Intent
import and5.abrar.challenge_ch8.model.RespondAnimeItem
import and5.abrar.challenge_ch8.viewmodel.AnimeViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeLayout : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Challengech8Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val animeViewModel = viewModel(modelClass = AnimeViewModel::class.java)
                    val dataAnime by animeViewModel.dataState.collectAsState()
                    LazyColumn{
                        if (dataAnime.isEmpty()){
                            item{

                            }
                        }
                        items(dataAnime){
                            Greeting3(anime = it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting3(anime : RespondAnimeItem) {
    val mContext = LocalContext.current
    Column(modifier = Modifier.padding(15.dp)) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(5.dp)
                .clickable {
                    val pindahdata = Intent(mContext, DetailLayout::class.java)
                    pindahdata.putExtra("detailanime", anime)
                    mContext.startActivity(pindahdata)
                }
        ) {

            Row(modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start) {
                Image( painter = rememberImagePainter(data = anime.link.thumbnail), contentDescription = "ini gambar" )
               Column(modifier = Modifier
                   .fillMaxWidth()
                   .padding(start = 10.dp)) {
                   Text(
                       text = anime.title,
                       color = Color.DarkGray,
                       fontWeight = FontWeight.Normal,
                   )
               }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview3() {
    Challengech8Theme {
    }
}