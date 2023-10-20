package com.example.matheus.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.matheus.NetworkUtils
import fraseViewModel
import retrofit2.Retrofit
import java.lang.IllegalStateException

class fraseViewModelFactory(
    private val retrofit: Retrofit = NetworkUtils.getRetrofitInstance("https://api.kanye.rest")
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(fraseViewModel::class.java) -> {
                fraseViewModel(retrofit)
            }
            else -> {
                throw IllegalArgumentException("Classe desconhecida: ${modelClass.name}")
            }
        } as T
    }
}