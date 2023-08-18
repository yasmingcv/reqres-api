package br.senai.sp.jandira.retrofit_reqres

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //arquivo xml

        apiService = RetrofitHelper.getInstance().create(ApiService::class.java)

        //GET
        findViewById<Button>(R.id.btnGET).setOnClickListener {
            //Log.e("GETTING-DATA", "TESTE DE BOTÃO GET")
            getUserByID()
        }

        //PUT
        findViewById<Button>(R.id.btnPUT).setOnClickListener {
            //Log.e("PUTTING-DATA", "TESTE DE BOTÃO PUT")
            updateUser()
        }

        //DELETE
        findViewById<Button>(R.id.btnDELETE).setOnClickListener {
            //Log.e("DELETING-DATA", "TESTE DE BOTÃO DELETE")
            deleteUser()
        }

        //POST
        findViewById<Button>(R.id.btnPOST).setOnClickListener {
            createUser()
        }
    }

    //LISTAGEM DE USUÁRIOS
    private fun getUserByID() {
        lifecycleScope.launch {
            val result = apiService.getUserByID("2")

            if (result.isSuccessful){
                Log.e("GETTING-DATA", "${result.body()?.data}")
            }else{
                Log.e("GETTING-DATA", "${result.message()}")
            }

        }
    }

    //INSERE USUÁRIO
    private fun createUser(){
        lifecycleScope.launch {

            val body = JsonObject().apply {
                addProperty("name", "Yasmin")
                addProperty("job", "FullStack Dev")
            }

            val result = apiService.createUser(body)

            if (result.isSuccessful){
                Log.e("CREATE-DATA", "${result.body()}")
            }else{
                Log.e("CREATE-DATA", "${result.message()}")
            }

        }
    }

    //ALTERA USUÁRIO
    private fun updateUser(){
        lifecycleScope.launch {

            val body = JsonObject().apply {
                addProperty("name", "Yasmin")
                addProperty("job", "FullStack Dev")
            }

            val result = apiService.updateUser("10", body)

            if (result.isSuccessful){
                Log.e("UPDATE-DATA", "${result.body()}")
            }else{
                Log.e("UPDATE-DATA", "${result.message()}")
            }
        }
    }

    private fun deleteUser(){
        lifecycleScope.launch {
            val result = apiService.deleteUser("2")

            if (result.isSuccessful){
                Log.e("DELETE-DATA", "${result}")
            }else{
                Log.e("DELETE-DATA", "${result.message()}")
            }
        }
    }
}