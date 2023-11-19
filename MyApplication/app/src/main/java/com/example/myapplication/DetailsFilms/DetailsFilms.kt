import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.myapplication.R

@Composable
fun DetailsScreen( id: String, viewModel: HomeViewModel, navController: NavController) {
    val movie by viewModel.movie.collectAsState()

    viewModel.movieDetails(id)

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),

       color = Color.Black
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Red
                        )
                    }


                    Image(
                        painter = painterResource(id = R.drawable.pngwing_com),
                        contentDescription = "NEF Logo",
                        modifier = Modifier.size(50.dp)
                    )
                }
                TitreFilm(movie)
                Spacer(modifier = Modifier.height(16.dp))
                PosterFilm(movie)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SmallPoster(movie)
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        DateFilm(movie)
                        Spacer(modifier = Modifier.height(8.dp))
                        GenreMovie(movie)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextSynop(movie)
                Spacer(modifier = Modifier.height(16.dp))
                Acteurs(movie)
            }
        }
    }
}

@Composable
fun TitreFilm(movie: Movie?) {
    if (movie != null) {
        Text(
            text = movie.original_title,
            fontFamily = FontFamily.SansSerif,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier.background(Color.Black)
        )
    }
}

@Composable
fun PosterFilm(movie: Movie?) {
    if (movie != null) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w1280/" + movie.backdrop_path,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun SmallPoster(movie: Movie?) {
    if (movie != null) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w300/" + movie.poster_path,
            contentDescription = "Ma super image",
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

@Composable
fun DateFilm(movie: Movie?) {
    if (movie != null) {
        Text(
            text = movie.release_date,
            fontWeight = FontWeight.W300,
            fontStyle = FontStyle.Italic,
            fontSize = 16.sp,
            color = Color.White,

        )
    }
}

@Composable
fun GenreMovie(movie: Movie?){

    if(movie!=null){
        LazyColumn(modifier = Modifier.height(70.dp)){
            items(movie.genres){
                    genre ->
                Text(
                    text = genre.name,
                    //modifier = Modifier.padding(1.dp),
                    fontStyle = FontStyle.Italic,
                    color = Color.White,

                )
            }
        }

    }
}

@Composable
fun TextSynop(movie: Movie?) {
    if (movie != null) {
        Text(
            text = "Synopsis",
            modifier = Modifier.padding(5.dp),
            fontWeight = FontWeight.Bold,
            color = Color.Red, // Set text color to white

        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.overview,
            modifier = Modifier.padding(6.dp),
            color = Color.White,

        )
    }
}

@Composable
fun Acteurs(movie: Movie?) {
    if (movie != null) {
        Text(
            text = "Cast",
            fontWeight = FontWeight.Bold,
            color = Color.Red,
        )
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        ) {
            items(movie.credits.cast) { actor ->
                ActeursList(actor)
            }
        }
    }
}


@Composable
fun ActeursList(actor: CastM) {
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w300/" + actor.profile_path,
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(RoundedCornerShape(8.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = actor.name,
            fontWeight = FontWeight.Bold,
            color = Color.White,

        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = actor.character,
            fontWeight = FontWeight.W300,
            color = Color.White,

        )
    }
}
