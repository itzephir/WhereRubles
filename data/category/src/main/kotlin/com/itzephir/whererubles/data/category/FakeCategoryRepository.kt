package com.itzephir.whererubles.data.category

import com.itzephir.whererubles.domain.model.Category
import com.itzephir.whererubles.domain.model.CategoryId
import com.itzephir.whererubles.domain.repository.CategoryRepository

/**
 * Fake implementation to mock categories
 */
@Suppress("MagicNumber")
class FakeCategoryRepository : CategoryRepository {
    @Suppress("LongMethod")
    override suspend fun readAll(): List<Category> = listOf(
        Category(
            id = CategoryId(0),
            name = "Аренда Квартиры",
            emoji = "\uD83C\uDFE0",
            isIncome = false,
        ),
        Category(
            id = CategoryId(1),
            name = "Одежда",
            emoji = "\uD83D\uDC57",
            isIncome = false,
        ),
        Category(
            id = CategoryId(2),
            name = "На собачку",
            emoji = "\uD83D\uDC36",
            isIncome = false,
        ),
        Category(
            id = CategoryId(3),
            name = "Ремонт квартиры",
            emoji = "РК",
            isIncome = false,
        ),
        Category(
            id = CategoryId(4),
            name = "Продукты",
            emoji = "\uD83C\uDF6D",
            isIncome = false,
        ),
        Category(
            id = CategoryId(5),
            name = "Спортзал",
            emoji = "\uD83C\uDFCB\uFE0F",
            isIncome = false,
        ),
        Category(
            id = CategoryId(6),
            name = "Медицина",
            emoji = "\uD83D\uDC8A",
            isIncome = false,
        ),
        Category(
            id = CategoryId(7),
            name = "Зарплата",
            emoji = "\uD83D\uDCB0",
            isIncome = true,
        ),
        Category(
            id = CategoryId(8),
            name = "Подработка",
            emoji = "\uD83D\uDCBB",
            isIncome = true,
        ),
    )
}
