package com.example.batman_project.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Rating(
    @Id var id: Long = 0,
    val Source: String,
    val Value: String
)