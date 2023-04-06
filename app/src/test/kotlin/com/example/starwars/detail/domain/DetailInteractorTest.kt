package com.example.starwars.detail.domain

import com.example.starwars.detail.data.DetailRepository
import com.example.starwars.detail.domain.model.DetailMapper

import io.mockk.mockk

class DetailInteractorTest {
    private val repository: DetailRepository = mockk()
    private val mapper: DetailMapper = mockk()

    private val characterInteractor = DetailInteractorImpl(repository, mapper)
}