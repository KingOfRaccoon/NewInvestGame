
package com.castprogramms.newinvestgame.news

enum class TypeEvent(
    val listDesc: List<String> = listOf(),
    private val increase: Float,
    private val delta: Float
) {
    CRYSIS(
        listOf(" провалилась очередная затея", " произошёл раскол в"),
        0.8f,
        0.1f
    ),

    OBVAL(
        listOf(" пошли трудности из-за", " повис требующий безотлагательного решения вопрос"),
        0.9f,
        0.05f
    ),

    NOTHING(
        listOf(" ничего не произошло", " ни от куда не возьмись ни от куда не взялось"),
        1f,
        0f
    ),

    PODEM(
        listOf(" появилась интересная идея", " пошли слухи о новой разработке"),
        1.1f,
        0.05f
    ),

    INCREASE(
        listOf(" произошла научно-техническая революция", " затея принесла колоссальные доходы"),
        1.2f,
        0.1f
    );

    fun getIncreaseWithDelta(): Float {
        val increases = mutableListOf<Float>()
        val first = increase - delta
        val last = increase + delta
        generateSequence(first, { it + 0.05f })
            .takeWhile { it <= last }
            .forEach { increases.add(it) }

        return increases.random()
    }
}