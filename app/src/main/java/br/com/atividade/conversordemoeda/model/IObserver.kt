package br.com.atividade.conversordemoeda.model

interface IObserver {
    fun updateUI(data:MutableMap<String,Any>)
}