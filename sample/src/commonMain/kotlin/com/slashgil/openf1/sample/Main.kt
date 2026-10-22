package com.slashgil.openf1.sample

import com.slashgil.openf1.di.OpenF1Sdk
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.result.Result

/**
 * Consumer sample verifying that openf1-sdk resolves and compiles in commonMain across all target platforms.
 */
suspend fun runSampleConsumer() {
    val sdk = OpenF1Sdk.create()

    when (val leaderboardResult = sdk.getSessionLeaderboardUseCase.asResult(SessionSpecifier.Latest)) {
        is Result.Success -> {
            println("Session leaderboard fetched: ${leaderboardResult.data.entries.size} drivers")
        }
        is Result.Error -> {
            println("Failed to fetch session leaderboard: ${leaderboardResult.exception.message}")
        }
        is Result.Loading -> {
            println("Loading session leaderboard...")
        }
    }

    sdk.close()
}
