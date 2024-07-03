package com.example.netflixclone

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Black)
                    .verticalScroll(

                        state = rememberScrollState()
                    )

            ) {
                TopNavbar()
                Options()
                FeaturedMovieItem()
                Categories("Sci-Fi")
                Categories("Action")
                Categories("Drama")
                Categories("Horror")
                
            }
        }
    }
}

@Preview
@Composable
fun TopNavbar(){
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black)
        .padding(start = 15.dp, end = 15.dp, top = 15.dp, bottom = 25.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.ic_menu), contentDescription = "Menu", modifier = Modifier.height(50.dp))
        Image(painter = painterResource(id = R.drawable.netflix_icon), contentDescription = "Icon", modifier = Modifier.height(50.dp))
        Image(painter = painterResource(id = R.drawable.ic_user), contentDescription = "User", modifier = Modifier.height(43.dp))
    }
}

@Preview
@Composable
fun Options() {
    val customStyles = TextStyle(
        color = Color.White,
        fontSize = 19.sp

    )
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Black)
        .padding(start = 30.dp, end = 30.dp, bottom = 15.dp), horizontalArrangement = Arrangement.SpaceBetween){
        Text(text = "All",style = customStyles)
        Text(text = "TV Shows",style = customStyles)
        Text(text = "Movies",style = customStyles)
        Text(text = "My List",style = customStyles)

    }
}



@Preview
@Composable
fun FeaturedMovieItem() {
    val context = LocalContext.current
    Column (modifier = Modifier
        .background(Color.Black)
        .fillMaxWidth()
     ){
        Image(painter = painterResource(id = R.drawable.movie), contentDescription = "Featured Movie", modifier = Modifier.fillMaxWidth())
        Row (horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 50.dp, end = 50.dp, top = 15.dp)
                .fillMaxWidth())

           {
            Text(text = "Action", color = Color.White, fontSize = 15.sp)
            Text(text = "Sci-Fi", color = Color.White, fontSize = 15.sp)
            Text(text = "Thriller", color = Color.White, fontSize = 15.sp)

        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 50.dp, end = 50.dp, top = 25.dp, bottom = 25.dp) ) {
            Column {

                Image(
                    painter = painterResource(id = R.drawable.ic_info),
                    contentDescription = "Movie Info",
                    modifier = Modifier.height(25.dp),
                )
                Text(text = "Info", color = Color.White, fontSize = 17.sp)
            }
            Button(onClick = {play(context)}, colors = ButtonDefaults.buttonColors(Color.White) ,shape = RoundedCornerShape(4.dp)) {
                Text(text = "Play", color = Color.Black, fontSize = 19.sp)
            }
            Column {
                Image(painter = painterResource(id = R.drawable.ic_add), contentDescription = "Movie Info", modifier = Modifier.height(25.dp))
                Text(text = "Info", color = Color.White, fontSize = 17.sp)
            }

        }
    }
}
fun play(context: android.content.Context){
    Toast.makeText(context,"Playing Video", Toast.LENGTH_SHORT).show()
}
fun addToList(context : android.content.Context){
    Toast.makeText(context, "Adding to your List", Toast.LENGTH_SHORT).show()
}
fun getInfo(context : android.content.Context){
    Toast.makeText(context, "Getting info", Toast.LENGTH_SHORT).show()
}
data class Cat(
    val image : Int,
)


@Composable
fun Categories(genres : String) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 25.dp)) {
        Text(text = genres, color = Color.White, fontSize = 21.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 15.dp, top = 15.dp, start = 5.dp)
            )
        LazyRow (modifier = Modifier.fillMaxWidth()){

            items(getShuffleList()){
                    item ->
                CategoryItem(image = item.image)
            }
        }
    }
}

@Composable
fun CategoryItem(image : Int) {
    Row (modifier = Modifier.background(Color.Black)){
        Image(
            painter = painterResource(id = image),
            contentDescription = "movie",
            modifier = Modifier
                .width(150.dp)
                .height(210.dp)
        )
    }
}

fun getShuffleList() : MutableList<Cat> {
    val catList = mutableListOf<Cat>()
    catList.add(Cat(R.drawable.movie_1))
    catList.add(Cat(R.drawable.movie_2))
    catList.add(Cat(R.drawable.movie_3))
    catList.add(Cat(R.drawable.movie_4))
    catList.add(Cat(R.drawable.movie_5))

    catList.shuffle()
    return catList

}
