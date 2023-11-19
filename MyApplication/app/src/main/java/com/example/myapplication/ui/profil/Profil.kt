package com.example.myapplication.ui.profil


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MyApplicationTheme
import java.lang.ProcessBuilder.Redirect


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(windowClass: WindowSizeClass,navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black


    ){
            when (windowClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                LayoutVert(navController)
            }

            else -> {

                Row(
                    Modifier.fillMaxSize().padding(16.dp)
                    ,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally,) {


                        Image(
                            painter = painterResource(id = R.drawable.me),
                            contentDescription = stringResource(id = R.string.app_name),
                            modifier = Modifier
                                .size(200.dp) // Ajustez la taille de l'image selon vos besoins
                                .clip(CircleShape)
                        )
                        textes("Meriem Chelli")
                        Column(
                        ) {
                            texte(contenu = "Apprentie Chez ATOUT MAJEUR CONCEPT")
                            texte(contenu = "Ecole d'ingénieur ISIS - INU Champollion")
                        }
                    }
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Image(
                                painter = painterResource(id = R.drawable.unnamed),
                                contentDescription = stringResource(id = R.string.app_name),
                                modifier = Modifier
                                    .size(30.dp)
                            )


                            texte(contenu = "chellymariem01@gmail.com")

                        }
                        Row(verticalAlignment = Alignment.CenterVertically)
                        {
                            Image(
                                painter = painterResource(id = R.drawable._74857),
                                contentDescription = stringResource(id = R.string.app_name),
                                modifier = Modifier
                                    .size(24.dp)
                            )
                            texte(contenu = "www.linkedin.com/meriem chelli")


                        }

                        button(navController, contenu = "submit")

                    }


                }
            }
        }

    }}










@Composable

fun LayoutVert(navController:NavController) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(

            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally


        ) {
            Image(
                painter = painterResource(id = R.drawable.me),
                contentDescription = stringResource(id = R.string.app_name),
                modifier = Modifier
                    .size(200.dp) // Ajustez la taille de l'image selon vos besoins
                    .clip(CircleShape)
            )

            textes("Meriem Chelli")
            Column {
                texte(contenu = "Apprentie Chez ATOUT MAJEUR CONCEPT")
                texte(contenu = "Ecole d'ingénieur ISIS - INU Champollion")
            }
            Column {
                Row (verticalAlignment = Alignment.CenterVertically)
                {
                    Image(painter = painterResource(id = R.drawable.unnamed), contentDescription = stringResource(id = R.string.app_name), modifier = Modifier
                        .size(30.dp) )


                    texte(contenu = "chellymariem01@gmail.com")

                }
                Row (verticalAlignment = Alignment.CenterVertically)
                {
                    Image(painter = painterResource(id = R.drawable._74857), contentDescription = stringResource(id = R.string.app_name), modifier = Modifier
                        .size(30.dp) )
                    texte(contenu = "www.linkedin.com/meriem chelli")

                }
            }

            button(navController,contenu = "submit")

        }
    }
}


@Composable
fun texte(contenu:String){
    Text(
        text=contenu,
        color = Color.White
    )
}
@Composable
fun textes(contenu:String){
    val offset = Offset(5.0f, 10.0f)

    Text(
        text=contenu,fontSize = 40.sp, style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Red, offset = offset, blurRadius = 6f)))
}
@Composable
fun button(navController: NavController, contenu:String){
    Button(colors= ButtonDefaults.buttonColors(Color.Red),onClick = { navController.navigate("films")}) {
        Text(text = "Choisissez Vos Films")
    }
}

