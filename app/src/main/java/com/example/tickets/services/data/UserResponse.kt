package com.example.tickets.services.data

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Usuario(
    val id: Int,

    @SerializedName("e-mail")
    val email: String,

    val nome: String,
    val senha: String,
    val imagem: Imagem?,

    val cargo: List<Cargo>
)

data class Imagem(
    val id: Int,
    val nome: String,
    val altura: Int,
    val largura: Int,
    val dados: String,

    @SerializedName("extensão")
    val extensao: String,

    @SerializedName("criado_em")
    val criadoEm: LocalDateTime,

    @SerializedName("atualizado_em")
    val atualizadoEm: LocalDateTime
)

data class Cargo(
    val id: Int,
    val name: String
)