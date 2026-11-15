package com.lizina.vkapp.domain.appdetails

import javax.inject.Inject

class GetRatingsSequentiallyUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(ids: List<String>): Map<String, Float> =
        repository.loadRatingsSequentially(ids)
}