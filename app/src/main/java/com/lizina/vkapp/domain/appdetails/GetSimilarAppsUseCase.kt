package com.lizina.vkapp.domain.appdetails

class GetSimilarAppsUseCase(private val repository: AppDetailsRepository) {
    suspend operator fun invoke(): List<SimilarApp> = repository.getSimilarApps()
}