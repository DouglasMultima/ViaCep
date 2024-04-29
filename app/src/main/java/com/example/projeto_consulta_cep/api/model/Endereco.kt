package com.example.projeto_consulta_cep.api.model

data class Endereco(

    val cep: String?,
    val bairro: String?,
    val complemento: String?,
    val ddd: String?,
    val gia: String?,
    val ibge: String?,
    val localidade: String?,
    val logradouro: String?,
    val siafi: String?,
    val uf: String?

)