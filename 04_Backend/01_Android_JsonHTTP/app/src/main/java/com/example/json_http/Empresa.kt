package com.example.json_http

import java.util.*
import kotlin.collections.ArrayList


class Empresa(
    var id: Int,
    var nombre: String,
    var createdAt: Long,
    var updatedAt: Long,
    var usuariosDeEmpresa: ArrayList<UsuarioEmpresa>
) {
    var fechaCreacion: Date
    var fechaActualizacion: Date

    init {
        fechaCreacion = Date(createdAt)
        fechaActualizacion = Date(updatedAt)
    }
}