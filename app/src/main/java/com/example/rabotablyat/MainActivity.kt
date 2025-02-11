package com.example.rabotablyat

import FlowerListActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rabotablyat.ui.theme.RabotaBlyatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RabotaBlyatTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onNavigateToFlowerList = {
                            // Переход к экрану списка цветов
                            startActivity(Intent(this, FlowerListActivity::class.java))
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onNavigateToFlowerList: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Добро пожаловать в магазин цветов!",
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = onNavigateToFlowerList,
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = "Перейти к списку цветов")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    RabotaBlyatTheme {
        MainScreen(onNavigateToFlowerList = {})
    }
}