package ru.practicum.android.diploma.favorites.data.model.entity

data class ContactsEntity(
    val id: String,
    val name: String,
    val email: String,
    val phone: List<String>?
)
