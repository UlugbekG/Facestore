package cd.ghost.catalog.domain.entity

import java.io.Serializable

data class FilterData(
    val sort: SortType,
    val itemsSize: Int,
    val category: Category
) : Serializable

enum class SortType(val value: String) {
    ASC("asc"),
    DESC("desc")
}

enum class Category(val value: String?) {
    ELECTRONICS("electronics"),
    JEWELERY("jewelery"),
    MEN_CLOTHING("men's clothing"),
    WOMEN_CLOTHING("women's clothing"),
    ALL("all")
}

fun String.getCategoryBy(): Category {
    return when (this) {
        Category.ELECTRONICS.value -> Category.ELECTRONICS
        Category.JEWELERY.value -> Category.JEWELERY
        Category.MEN_CLOTHING.value -> Category.MEN_CLOTHING
        Category.WOMEN_CLOTHING.value -> Category.WOMEN_CLOTHING
        else -> Category.ALL
    }
}

/**
 * Ascending order means the smallest or first or earliest in the order will appear at the top of the list:
 * For numbers or amounts, the sort is smallest to largest. Lower numbers or amounts will be at the top of the list.
 * For letters/words, the sort is alphabetical from A to Z.
 * For data with numbers and letters/words, such as address lines, the sort is most likely alphanumeric meaning 0-9 is sorted first then followed by A-Z.
 * For dates, the sort will be oldest/earliest dates to most recent. The oldest dates will be at the top of the list.
 *
 *
 * Descending order means the largest or last in the order will appear at the top of the list:
 * For numbers or amounts, the sort is largest to smallest. Higher numbers or amounts will be at the top of the list.
 * For letters/words, the sort is alphabetical from Z to A.
 * For data with numbers and letters/words, such as address lines, the sort is most likely alphanumeric meaning Z to A is sorted first then followed by 9-0.
 * For dates, the sort will be most recent dates to the oldest/earliest dates. The most recent/latest dates will be at the top of the list.
 */
