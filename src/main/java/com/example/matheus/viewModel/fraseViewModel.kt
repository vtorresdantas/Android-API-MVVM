import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.matheus.model.Frase
import com.example.matheus.model.IQuote
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class fraseViewModel(
    private val retrofitClient: Retrofit
) : ViewModel() {
    val liveQuote = MutableLiveData<Frase>()
    val liveError = MutableLiveData<String>()

    fun getQuote() {
        val endPoint = getDeckEndPoint()
        val callBack = endPoint.getKanyeText()
        callBack.enqueue(object : Callback<Frase> {
            override fun onResponse(call: Call<Frase>, resp: Response<Frase>) {
                val quote = resp.body()
                if (quote != null) {
                    // Atualizar o LiveData com a nova instância de Frase
                    liveQuote.value = quote
                } else {
                    liveError.value = "Resposta nula da API"
                }
            }

            override fun onFailure(call: Call<Frase>, t: Throwable) {
                liveError.value = t.message ?: "Erro desconhecido"
            }
        })
    }

    private fun getDeckEndPoint(): IQuote {
        return retrofitClient.create(IQuote::class.java)
    }
}
