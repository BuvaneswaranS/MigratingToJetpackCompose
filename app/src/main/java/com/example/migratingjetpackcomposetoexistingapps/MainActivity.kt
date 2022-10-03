package com.example.migratingjetpackcomposetoexistingapps

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.migratingjetpackcomposetoexistingapps.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.bodyLayout.apply {
            setContent {
                MaterialTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(1F)
                    ) {
                        Scaffold(
                            topBar = { TopAppBarLayout() },
                            floatingActionButton = { FloatingActionButtonLayout() }
                        ) {
                            BodyLayout()
                        }
                    }

                }
            }
        }

    }
}

@Composable
fun TopAppBarLayout() {
    Surface(
        modifier = Modifier
            .fillMaxWidth(1F)
            .background(Color.White),
        elevation = 8.dp
    ) {
        Text(
            text =  stringResource(id = R.string.app_name),
            modifier = Modifier
                .fillMaxWidth(1F)
                .height(54.dp),
            textAlign = TextAlign.Start,
            color = Color.Red
        )
    }
}

@Composable
fun FloatingActionButtonLayout() {
    FloatingActionButton(onClick = {}, modifier = Modifier.size(50.dp)) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = stringResource(id = R.string.image_description)
        )
    }
}

class TestViewModel: ViewModel() {
    fun carList(): List<CardItems> {
        return listOf(
            CardItems("Max"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1"),
            CardItems("A1")
        )
    }
}

@Composable
fun BodyLayout(viewModel: TestViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val list = viewModel.carList()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier
        ) {
            items(list) { item ->
                CardItem(cardItem = item)
            }
        }
    }
}

data class CardItems(
    var name: String
)

@Composable
fun CardItem(cardItem: CardItems) {
    val dimen8dp  = dimensionResource(id = R.dimen.card_elevation)
    Card (
        modifier = Modifier
            .padding(start = dimen8dp, end = dimen8dp, bottom = dimen8dp)
            .fillMaxWidth(1F),
        elevation = dimen8dp,
        shape = RoundedCornerShape(
            topEnd = 15.dp
        ),
        border = BorderStroke(1.dp, Color.Blue)
    ) {
        Box {
            Column {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription =  stringResource(id = R.string.image_description)
                    )
                    Column {
                        SelectionContainer {
                            Text(
                                text = cardItem.name,
                                textAlign = TextAlign.Start,
                                textDecoration = TextDecoration.None,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = dimen8dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = stringResource(id = R.string.privacy),
                                textAlign = TextAlign.Start,
                                textDecoration = TextDecoration.None,
                            )
                            Text(
                                text = stringResource(id = R.string.time),
                                textAlign = TextAlign.Start,
                                textDecoration = TextDecoration.None,
                            )
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(1F)
        ) {
            Scaffold(
                topBar = { TopAppBarLayout() },
                floatingActionButton = { FloatingActionButtonLayout() }
            ) {
                BodyLayout()
            }
        }
    }
}