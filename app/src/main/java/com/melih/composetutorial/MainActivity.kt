package com.melih.composetutorial

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.color.MaterialColors
import com.melih.composetutorial.ui.theme.ComposeTutorialTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App {
                NameListContent()
            }
        }
    }
}

@Composable
fun App(content: @Composable () -> Unit) {
    ComposeTutorialTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Preview
@Composable
fun NameListPreview() {
    App {
        NameListContent()
    }
}

@Composable
fun NameListContent(names: List<String> = List(1000) { "Android #$it" }) {
    val counterState = remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxHeight()) {
        NameList(names, Modifier.weight(1f))
        ClickCounter(
            clickCount = counterState.value,
            onClick = {
                counterState.value++
            }
        )
    }
}

@Composable
fun NameList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun NewsStory(names: List<String> = listOf("Android", "there")) {
    val counterState = remember { mutableStateOf(0) }

    MaterialTheme {
        val typography = MaterialTheme.typography
        Column(modifier = Modifier.fillMaxHeight()) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.header),
                    contentDescription = null,
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(4.dp)),
                    contentScale = ContentScale.Crop,
                )

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "A day wandering through the sandhills \" +\n" +
                            "                     \"in Shark Fin Cove, and a few of the \" +\n" +
                            "                     \"sights I saw",
                    style = typography.h6,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text("Davenport, California", style = typography.body2)
                Text("December 2018", style = typography.body2)

                for (name in names) {
                    Greeting(name = name)
                    Divider()
                }
            }

            ClickCounter(
                clickCount = counterState.value,
                onClick = {
                    counterState.value++
                },
            )
        }
    }
}

@Preview
@Composable
fun NewsPreview() {
    ComposeTutorialTheme {
        NewsStory()
    }
}

@Composable
fun ClickCounter(clickCount: Int, onClick: () -> Unit) {
    Button(onClick = onClick) {
        Text("I've been clicked $clickCount times")
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember { mutableStateOf(false) }
    val backgroundColor by animateColorAsState(if (isSelected) Color.Red else Color.Transparent)

    Text(
        text = "Hello $name!",
        modifier = Modifier
            .clickable { isSelected = !isSelected }
            .padding(24.dp)
            .background(color = backgroundColor),
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.onSurface,
    )
}
