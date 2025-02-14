@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rabotablyat.Functional

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.example.rabotablyat.models.Flower
import com.example.rabotablyat.ui.theme.RabotaBlyatTheme

class FlowerListActivity : ComponentActivity() {

    private val flowerList = mutableStateListOf<Flower>() // Используем mutableStateListOf для Compose
    private lateinit var flowerAdapter: FlowerAdapter // Если вы используете адаптер, но в Compose он не нужен

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RabotaBlyatTheme {
                FlowerListScreen(flowerList)
            }
        }

        // Загрузка цветов из Firebase
        loadFlowers()
    }

    private fun loadFlowers() {
        val database = FirebaseDatabase.getInstance()
        val flowersRef = database.getReference("flowers")

        flowersRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                flowerList.clear()
                for (flowerSnapshot in snapshot.children) {
                    val flower = flowerSnapshot.getValue(Flower::class.java)
                    flower?.let { flowerList.add(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Обработка ошибки
            }
        })
    }
}

@Composable
fun FlowerListScreen(flowerList: List<Flower>) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Список цветов") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (flowerList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Загрузка цветов...")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(flowerList) { flower ->
                        FlowerItem(flower)
                    }
                }
            }
        }
    }
}

@Composable
fun FlowerItem(flower: Flower) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = flower.name, style = MaterialTheme.typography.titleMedium)
            // Добавьте другие поля, если необходимо
        }
    }
}