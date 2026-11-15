package com.lizina.vkapp.domain.appdetails

import javax.inject.Inject

class GetRatingsInParallelUseCase @Inject constructor(
    private val repository: AppDetailsRepository,
) {
    suspend operator fun invoke(ids: List<String>): Map<String, Float> =
        repository.loadRatingsInParallel(ids)
}