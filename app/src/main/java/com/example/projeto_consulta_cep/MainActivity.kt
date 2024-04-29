package com.example.projeto_consulta_cep

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.projeto_consulta_cep.api.cep.EnderecoAPI
import com.example.projeto_consulta_cep.api.cep.RetrofitHelper
import com.example.projeto_consulta_cep.api.model.Endereco
import com.example.projeto_consulta_cep.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnIniciar.setOnClickListener{

            CoroutineScope(Dispatchers.IO).launch {
                recuperarEndereco()
            }

        }



    }




   private suspend fun recuperarEndereco() {

       var retorno : Response<Endereco> ?= null
       val cepDigitadoUsuario = binding.edtCep.text.toString()
       val numero = cepDigitadoUsuario.toInt()





       try {

           val informacoes = retrofit.create(EnderecoAPI::class.java)
           retorno =  informacoes.recuperarEndereco(numero)


       }catch(e:Exception){
           e.printStackTrace()
           Log.i("info_endereco" , "erro na solicitacao")

       }

       if(retorno!= null){
           if (retorno.isSuccessful){
               val informacao_endereco = retorno.body()

               val resultado = "cep:${informacao_endereco?.cep}\n"+
                       "complemento:${informacao_endereco?.logradouro}\n"+
                       "barirro:${informacao_endereco?.bairro}\n"+
                       "localidade:${informacao_endereco?.localidade}\n"+
                       "Estado:${informacao_endereco?.uf}\n"+
                       "ddd:${informacao_endereco?.ddd}\n"

               withContext(Dispatchers.Main){
                   binding.informacoes.text = resultado
               }
           }
       }

    }


}