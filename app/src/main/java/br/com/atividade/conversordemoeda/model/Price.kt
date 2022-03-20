package br.com.atividade.conversordemoeda.model

import androidx.databinding.ObservableDouble

class Price {
    private var value = ObservableDouble()

    fun getValue() = value
    fun setValue(value: Double) {
        this.value.set(value)
    }
}