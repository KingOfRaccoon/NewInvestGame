package com.castprogramms.newinvestgame.tools

enum class Industries(var nameIndustry: String, val listName: List<String>) {
    OilIndustry(
        "Нефтяная промышленность",
        listOf(
            "В нефтяной промышленности",
            "В нефтяной отрасли"
        )
    ),

    Software(
        "Программное обеспечение",
        listOf(
            "В индустрии программного обеспечения",
            "В отрасль разработки программного обеспечения"
        )
    ),

    BankingIndustry(
        "Денежные средства",
        listOf(
            "В банковской отрасли",
            "В банковской сфере"
        )
    ),

    FoodIndustry(
        "Пищевая промышленность",
        listOf(
            "В пищевой промышленности",
            "В пищевой отрасли"
        )
    )
}