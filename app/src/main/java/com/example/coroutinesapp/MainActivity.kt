package com.example.coroutinesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var btn : Button
    lateinit var tvAdvice: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn=findViewById(R.id.btn)
        tvAdvice=findViewById(R.id.tvAdvice)
        btn.setOnClickListener{
            //set the Coroutine Scope
            CoroutineScope(IO).launch {
                callAPI()
            }
        }
    }

    private suspend fun  callAPI() {

        val apiInterface = APIClient().getClient()?.create(APIInterface::class.java)
        val call: Call<Advices?>? = apiInterface!!.getAdvises()

        call?.enqueue(object : Callback<Advices?> {
            override fun onResponse(
                call: Call<Advices?>?, response: Response<Advices?>
            ) {
                Log.d("TAG", response.code().toString() + "")
                val resource: Advices? = response.body()
                val datumAdvice = resource?.slip?.advice
                tvAdvice.text = "The advice : " + datumAdvice
            }

            override fun onFailure(call: Call<Advices?>, t: Throwable?) {
                call.cancel()
            }
        })

    }
}