package com.example.projeto_consulta_cep.api.cep

import com.example.projeto_consulta_cep.api.model.Endereco
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path


interface EnderecoAPI {


    @GET("ws/{cep}/json/")
    suspend fun recuperarEndereco(
        @Path("cep") cep:Int
    ) : Response<Endereco>






}