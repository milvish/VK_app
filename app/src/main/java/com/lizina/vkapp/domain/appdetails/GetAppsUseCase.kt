package com.lizina.vkapp.domain.appdetails

import javax.inject.Inject

class GetAppsUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(): List<AppDetails> = repository.getApps()
}