package ru.practicum.android.diploma.core.data.utils

import ru.practicum.android.diploma.core.data.network.VacancyNetworkClient

/**
 * Объект с кодами ответов для сетевых операций.
 *
 * Используется в [VacancyNetworkClient] для обозначения результата запроса.
 */
object ResponseCode {
    /** Запрос выполнен успешно. */
    const val SUCCESS = 200

    /** Ошибка ввода-вывода (например, нет интернета). */
    const val IO_ERROR = 500
}

