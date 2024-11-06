package com.example.fe_taks_il.models

data class Movies(
    val id: Int,
    val judul: String,
    val sutradara: String,
    val photo: Int,
    val genre: String,
    val category: String,
    val sub_judul: String,
    val deskripsi: String,
    val link_trailer: String,
)
