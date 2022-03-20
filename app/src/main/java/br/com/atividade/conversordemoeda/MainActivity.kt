package br.com.atividade.conversordemoeda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import br.com.atividade.conversordemoeda.databinding.ActivityMainBinding
import br.com.atividade.conversordemoeda.model.IObserver
import br.com.atividade.conversordemoeda.model.Price
import br.com.atividade.conversordemoeda.repository.RateAPI

class MainActivity : AppCompatActivity(), IObserver {
    private lateinit var binding: ActivityMainBinding
    private val dollarPrice = Price()
    private val euroPrice = Price()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this
        binding.dollarPrice = dollarPrice
        binding.euroPrice = euroPrice

        binding.btnConvert.setOnClickListener {
            val rateAPI = RateAPI()
            rateAPI.getCurrency(applicationContext, this)
        }
    }

    override fun updateUI(data: MutableMap<String, Any>) {
        var alert = AlertDialog.Builder(this)
        alert.setTitle("Aguarde...")
        alert.setMessage("Estamos processando a conversão!")
        alert.show()

        if (data.isNotEmpty()) {
            val dollarValue = data["dollar"] as Double
            val euroValue = data["euro"] as Double

            val realValue = binding.txtReal.text.toString().toDouble()

            dollarPrice.setValue(realValue / dollarValue)
            euroPrice.setValue(realValue / euroValue)


        }
    }
}