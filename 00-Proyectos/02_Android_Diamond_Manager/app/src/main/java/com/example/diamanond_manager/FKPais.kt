package com.example.diamanond_manager

class FKPais (
    var id: Int,
    var countryName: String
) {
    override fun toString(): String {
        return "FKPais(id=$id, countryName='$countryName')"
    }
}