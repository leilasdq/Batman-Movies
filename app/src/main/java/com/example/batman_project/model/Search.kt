package com.example.batman_project.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import io.objectbox.annotation.Index

@Entity
data class Search(
    @Id var id: Long = 0,
    var Poster: String,
    var Title: String,
    var Type: String,
    var Year: String,
    @Index var imdbID: String
)