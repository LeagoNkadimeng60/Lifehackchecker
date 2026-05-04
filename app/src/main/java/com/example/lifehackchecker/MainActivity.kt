package com.example.lifehackchecker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lifehackchecker.ui.theme.LifehackcheckerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            LifehackcheckerTheme {

                var screen by remember { mutableStateOf("WELCOME") }
                var index by remember { mutableStateOf(0) }
                var score by remember { mutableStateOf(0) }
                var resultText by remember { mutableStateOf("") }

                val questions = listOf(
                    "Sleeping 8 hours improves health." to true,
                    "Skipping breakfast boosts energy." to false,
                    "Reading daily sharpens the mind." to true,
                    "Using a phone in the dark protects your eyes." to false
                )

                val current = questions[index]

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF101820)
                ) {

                    when (screen) {

                        "WELCOME" -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Welcome to my app",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(30.dp))

                                Button(onClick = { screen = "HOME" }) {
                                    Text("Continue")
                                }
                            }
                        }

                        "HOME" -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Life Hack Checker",
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(40.dp))

                                Button(
                                    onClick = { screen = "QUIZ" },
                                    shape = RoundedCornerShape(20.dp)
                                ) {
                                    Text("Start")
                                }
                            }
                        }

                        "QUIZ" -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(20.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Color(0xFF1F2A44),
                                            shape = RoundedCornerShape(16.dp)
                                        )
                                        .padding(20.dp)
                                ) {
                                    Text(
                                        text = current.first,
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                }

                                Spacer(modifier = Modifier.height(30.dp))

                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                                ) {

                                    Button(onClick = {
                                        if (current.second) {
                                            score++
                                            resultText = "Correct"
                                        } else {
                                            resultText = "Wrong"
                                        }
                                        screen = "RESULT"
                                    }) {
                                        Text("True")
                                    }

                                    Button(onClick = {
                                        if (!current.second) {
                                            score++
                                            resultText = "Correct"
                                        } else {
                                            resultText = "Wrong"
                                        }
                                        screen = "RESULT"
                                    }) {
                                        Text("False")
                                    }
                                }
                            }
                        }

                        "RESULT" -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = resultText,
                                    fontSize = 24.sp,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                Button(onClick = {
                                    if (index < questions.size - 1) {
                                        index++
                                        screen = "QUIZ"
                                    } else {
                                        screen = "FINAL"
                                    }
                                }) {
                                    Text("Next")
                                }
                            }
                        }

                        "FINAL" -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {

                                Text(
                                    text = "Score: $score / ${questions.size}",
                                    fontSize = 26.sp,
                                    color = Color.White
                                )

                                Spacer(modifier = Modifier.height(20.dp))

                                Text(
                                    text = if (score >= 3) "Well done" else "Try again",
                                    color = Color.LightGray
                                )

                                Spacer(modifier = Modifier.height(30.dp))

                                Button(onClick = {
                                    index = 0
                                    score = 0
                                    screen = "QUIZ"
                                }) {
                                    Text("Restart")
                                }

                                Spacer(modifier = Modifier.height(10.dp))

                                Button(onClick = {
                                    index = 0
                                    score = 0
                                    screen = "HOME"
                                }) {
                                    Text("Home")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}