package com.example.diamanond_manager

class Diamante(
    var id: Int,
    var nombreDiamante: String,
    var caratDiamante: Double,
    var precioDiamante: Double,
    var fkClarity: FKClaridad,
    var fkColor: FKColor,
    var fkCountry: FKPais,
    var fkCut: FKCut
) {
    constructor() : this(
        // Por defecto
        0,
        "",
        0.0,
        0.0,
        FKClaridad(0, ""),
        FKColor(0, ""),
        FKPais(0, ""),
        FKCut(0, "")
    )

    override fun toString(): String {
        return "Diamante(id=$id, nombreDiamante='$nombreDiamante', caratDiamante=$caratDiamante, " +
                "precioDiamante=$precioDiamante, fkClarity=$fkClarity, fkColor=$fkColor, fkCountry=$fkCountry, fkCut=$fkCut)"
    }
}
