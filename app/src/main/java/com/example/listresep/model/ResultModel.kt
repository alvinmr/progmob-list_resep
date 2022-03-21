package com.example.listresep.model


data class ResultModel(
    val method: String?,
    val status: Boolean,
    var results: MutableList<RecipeModel>
)
