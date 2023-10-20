    package com.example.matheus.view

    import androidx.appcompat.app.AppCompatActivity
    import androidx.appcompat.app.AlertDialog
    import androidx.lifecycle.Observer
    import androidx.lifecycle.ViewModelProvider
    import android.os.Bundle
    import android.widget.TextView
    import com.example.matheus.databinding.ActivityMainBinding
    import com.example.matheus.model.Frase
    import com.example.matheus.viewModel.fraseViewModelFactory
    import com.example.matheus.R
    import fraseViewModel

    class MainActivity : AppCompatActivity() {

        lateinit var bind: ActivityMainBinding

        val fraseFactory = fraseViewModelFactory()
        lateinit var viewModel: fraseViewModel

        // Declare a variável para a textView txtFraseTraduzida
        val txtFrase: TextView by lazy {
            findViewById(R.id.txtFrase)
        }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            bind = ActivityMainBinding.inflate(layoutInflater)
            setContentView(bind.root)

            viewModel = ViewModelProvider(this, fraseFactory).get(fraseViewModel::class.java)

            setupObservers()

            viewModel.getQuote()

            bind.btnFrase.setOnClickListener {
                viewModel.getQuote()
            }
        }

        fun setupObservers() {
            viewModel.liveQuote.observe(
                this,
                Observer(::setQuote)
            )
            viewModel.liveError.observe(
                this,
                Observer(::showErrorMessage)
            )
        }

        private fun setQuote(quote: Frase) {
            bind.txtFrase.text = quote.quote
        }

        private fun showErrorMessage(errorMessage: String) {
            val errorDialog = AlertDialog.Builder(this)
            errorDialog.setTitle("Erro")
            errorDialog.setMessage(errorMessage)
            errorDialog.setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            errorDialog.show()
        }
    }
