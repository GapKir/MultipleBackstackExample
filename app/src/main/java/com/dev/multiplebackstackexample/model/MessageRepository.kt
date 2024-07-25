package com.dev.multiplebackstackexample.model

import kotlin.random.Random

object MessageRepository {
    val messages = List(20) {
        Message(
            id = Random.nextLong(1000),
            person = "Person #$it",
            subject = "Let`s discuss task №$it",
            body = "Something goes wrong in task №$it"
        )
    }
}