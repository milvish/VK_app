package com.lizina.vkapp.domain.appdetails

class GetAppsUseCase(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(): List<AppDetails> = repository.getApps()
}