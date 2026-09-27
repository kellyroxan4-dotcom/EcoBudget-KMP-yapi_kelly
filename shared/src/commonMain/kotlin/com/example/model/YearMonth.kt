package com.example.model

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

/**
 * Modèle immuable représentant un mois spécifique pour la navigation budgétaire (KMP Compatible).
 *
 * @property year Année (ex: 2026).
 * @property month Index du mois de 0 (Janvier) à 11 (Décembre).
 */
data class YearMonth(
    val year: Int,
    val month: Int
) {
    /**
     * Libellé du mois en français (ex: "Août 2026").
     */
    val displayLabel: String
        get() {
            val monthNames = listOf(
                "Janvier", "Février", "Mars", "Avril", "Mai", "Juin",
                "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Décembre"
            )
            val monthName = monthNames.getOrElse(month) { "" }
            return "\(monthName\)year"
        }

    /**
     * Retourne le YearMonth précédent.
     */
    fun previous(): YearMonth {
        return if (month == 0) {
            YearMonth(year - 1, 11)
        } else {
            YearMonth(year, month - 1)
        }
    }

    /**
     * Retourne le YearMonth suivant.
     */
    fun next(): YearMonth {
        return if (month == 11) {
            YearMonth(year + 1, 0)
        } else {
            YearMonth(year, month + 1)
        }
    }

    /**
     * Vérifie si un timestamp millisecondes appartient à ce mois précis.
     */
    fun containsTimestamp(timestamp: Long): Boolean {
        val instant = Instant.fromEpochMilliseconds(timestamp)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        val monthIndex = localDateTime.monthNumber - 1 // kotlinx.datetime retourne 1-12
        return localDateTime.year == year && monthIndex == month
    }

    companion object {
        /**
         * Crée le YearMonth courant.
         */
        fun current(): YearMonth {
            val now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            return YearMonth(
                year = now.year,
                month = now.monthNumber - 1 // Conversion vers l'index 0-11
            )
        }

        /**
         * Crée le YearMonth correspondant à un timestamp.
         */
        fun fromTimestamp(timestamp: Long): YearMonth {
            val instant = Instant.fromEpochMilliseconds(timestamp)
            val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
            return YearMonth(
                year = localDateTime.year,
                month = localDateTime.monthNumber - 1 // Conversion vers l'index 0-11
            )
        }
    }
}