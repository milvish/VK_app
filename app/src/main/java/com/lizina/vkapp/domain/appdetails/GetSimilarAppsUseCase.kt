package com.lizina.vkapp.domain.appdetails

import javax.inject.Inject

class GetSimilarAppsUseCase @Inject constructor(private val repository: AppDetailsRepository) {
    suspend operator fun invoke(): List<SimilarApp> = repository.getSimilarApps()
}