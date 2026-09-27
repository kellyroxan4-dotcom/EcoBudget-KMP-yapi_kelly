package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.ui.screens.EcoBudgetApp
import com.example.viewmodel.EcoBudgetViewModel

class MainActivity : ComponentActivity() {

  private val viewModel: EcoBudgetViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()

    setContent {
      EcoBudgetApp(viewModel = viewModel)
    }
  }
}