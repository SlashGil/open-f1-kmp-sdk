package com.slashgil.openf1.domain.usecase

import com.slashgil.openf1.domain.model.SessionLeaderboard
import com.slashgil.openf1.domain.model.SessionLeaderboardEntry
import com.slashgil.openf1.domain.model.specifier.SessionSpecifier
import com.slashgil.openf1.domain.repository.DriverRepository
import com.slashgil.openf1.domain.repository.RaceEventRepository

class GetSessionLeaderboardUseCase(
    private val driverRepository: DriverRepository,
    private val raceEventRepository: RaceEventRepository
) {
    suspend operator fun invoke(
        sessionKey: SessionSpecifier = SessionSpecifier.Latest
    ): SessionLeaderboard {
        val drivers = driverRepository.getDrivers(sessionKey = sessionKey).associateBy { it.driverNumber }
        val positions = raceEventRepository.getPosition(sessionKey = sessionKey)
            .groupBy { it.driverNumber }
            .mapValues { (_, posList) -> posList.lastOrNull()?.position }
        val intervals = raceEventRepository.getIntervals(sessionKey = sessionKey)
            .groupBy { it.driverNumber }
            .mapValues { (_, intList) -> intList.lastOrNull() }
        val laps = raceEventRepository.getLaps(sessionKey = sessionKey)
            .filter { it.lapDuration != null }
            .groupBy { it.driverNumber }
            .mapValues { (_, lapList) -> lapList.minByOrNull { it.lapDuration ?: Double.MAX_VALUE } }

        val activeDriverNumbers = (drivers.keys + positions.keys).filterNotNull().distinct()

        val entries = activeDriverNumbers.map { driverNumber ->
            SessionLeaderboardEntry(
                position = positions[driverNumber],
                driver = drivers[driverNumber],
                gapToLeader = intervals[driverNumber]?.gapToLeader,
                interval = intervals[driverNumber]?.interval,
                bestLap = laps[driverNumber]
            )
        }.sortedBy { it.position ?: Int.MAX_VALUE }

        val actualSessionKey = drivers.values.firstOrNull()?.sessionKey
            ?: when (sessionKey) {
                is SessionSpecifier.Key -> sessionKey.id
                else -> null
            }

        return SessionLeaderboard(
            sessionKey = actualSessionKey,
            entries = entries
        )
    }
}
