package com.lizina.vkapp

data class Country(
    val name: String,
    val isoCode: String,   // ISO-код: "RU", "US", ...
    val dialCode: String,  // "+7", "+1", ...
    val flag: String       // emoji-флаг
)

val countries = listOf(
    Country("Россия",        "RU", "+7",   "🇷🇺"),
    Country("США",           "US", "+1",   "🇺🇸"),
    Country("Великобритания","GB", "+44",  "🇬🇧"),
    Country("Германия",      "DE", "+49",  "🇩🇪"),
    Country("Франция",       "FR", "+33",  "🇫🇷"),
    Country("Италия",        "IT", "+39",  "🇮🇹"),
    Country("Испания",       "ES", "+34",  "🇪🇸"),
    Country("Казахстан",     "KZ", "+7",   "🇰🇿"),
    Country("Беларусь",      "BY", "+375", "🇧🇾"),
    Country("Украина",       "UA", "+380", "🇺🇦"),
    Country("Польша",        "PL", "+48",  "🇵🇱"),
    Country("Турция",        "TR", "+90",  "🇹🇷"),
    Country("Китай",        "CN", "+86",  "🇨🇳"),
    Country("Япония",        "JP", "+81",  "🇯🇵"),
    Country("Индия",        "IN", "+91",  "🇮🇳"),
    Country("Бразилия",      "BR", "+55",  "🇧🇷"),
    Country("Канада",        "CA", "+1",   "🇨🇦"),
    Country("Австралия",     "AU", "+61",  "🇦🇺"),
    Country("ОАЭ",           "AE", "+971", "🇦🇪"),
    Country("Южная Корея",   "KR", "+82",  "🇰🇷"),
)
