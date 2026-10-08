package com.es.jma.testing

import com.es.jma.model.Breed
import com.es.jma.model.CatInfo

val fakeCat = CatInfo(
    id = "1",
    url = "www.image.com",
    isFavorite = false,
    idBreed = "1",
    name = "Mayillo",
    temperament = "crazy, dump, funny, smart",
    origin = "home",
    description = "A little and fat kitting bit sociopath cat that live in your house and is very clingy and dump"
)
val fakeCatTwo = CatInfo(
    id = "2",
    url = "www.image.com",
    isFavorite = false,
    idBreed = "3",
    name = "Pirula",
    temperament = "smart, cute, funny, spicy",
    origin = "home",
    description = "A tiny cute sociopath cat that live in your house and is very clingy and spicy"
)
val fakeBreed = Breed(
    id = "1",
    name = "Mayillo",
    temperament = "crazy, dump, funny, smart",
    origin = "Home",
    wikipediaUrl = "https://wiki.test/mayillo"
)