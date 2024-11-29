package com.mrh.conversordivisas

enum class ConvertValues(
    val divisa: String,
    val valor: Double,
    val image: Int,
    val simbolo: String
) {
    USD(divisa = "Dolar", valor = 1.0, image = R.drawable.us, simbolo = "$"),
    EUR(divisa = "Euro", valor = 0.92, image = R.drawable.eu, simbolo = "€"),
    GBP(divisa = "Libra Esterlina", valor = 0.78, image = R.drawable.gb, simbolo = "£"),
    JPY(divisa = "Yen Japones", valor = 144.66, image = R.drawable.jp, simbolo = "¥"),
    CAD(divisa = "Dolar Canadiense", valor = 1.36, image = R.drawable.ca, simbolo = "CA$"),
    AUD(divisa = "Dolar Australiano", valor = 1.56, image = R.drawable.au, simbolo = "AU$"),
    CHF(divisa = "Franco Suizo", valor = 0.98, image = R.drawable.ch, simbolo = "CHF"),
    CNY(divisa = "Yuan Chino", valor = 7.28, image = R.drawable.cn, simbolo = "¥"),
    INR(divisa = "Rupia India", valor = 83.18, image = R.drawable.ind, simbolo = "₹"),
    BRL(divisa = "Real Brasileño", valor = 4.95, image = R.drawable.br, simbolo = "R$"),
    MXN(divisa = "Peso Mexicano", valor = 17.14, image = R.drawable.mx, simbolo = "MX$"),
    ZAR(divisa = "Rand Sudafricano", valor = 19.05, image = R.drawable.za, simbolo = "R"),
    RUB(divisa = "Rublo Ruso", valor = 96.84, image = R.drawable.ru, simbolo = "₽"),
    KRW(divisa = "Won Surcoreano", valor = 1328.75, image = R.drawable.kr, simbolo = "₩"),
    SEK(divisa = "Corona Sueca", valor = 11.02, image = R.drawable.se, simbolo = "kr"),
    NOK(divisa = "Corona Noruega", valor = 11.57, image = R.drawable.no, simbolo = "kr"),
    DKK(divisa = "Corona Danesa", valor = 7.45, image = R.drawable.dk, simbolo = "kr")
}