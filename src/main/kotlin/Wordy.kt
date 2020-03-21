import kotlin.math.pow

object Wordy {

    fun answer(input: String): Int {

        val operatorSet = setOf("plus", "minus", "multiplied-by", "divided-by", "raised-to-the-power")

        val problem = input.dropLast(1)
                .replace("multiplied by", "multiplied-by")
                .replace("divided by", "divided-by")
                .replace("raised to the", "raised-to-the-power")
                .split(" ")
                .dropWhile { !it.all { char -> char.isDigit() || char == '-' } }
                .dropLastWhile { it == "power" }
                .toMutableList()

        if (problem.isEmpty()) throw Exception()

        var answer = problem.removeAt(0).toInt()

        while (problem.isNotEmpty()) {

            val operator = problem.removeAt(0)

            if (operator !in operatorSet) throw Exception()

            val digit = problem.removeAt(0).dropLastWhile { it.isLetter() }

            answer = when (operator) {
                "plus" -> answer + digit.toInt()
                "minus" -> answer - digit.toInt()
                "multiplied-by" -> answer * digit.toInt()
                "divided-by" -> answer / digit.toInt()
                else -> answer.pow(digit.toInt())
            }
        }

        return answer
    }

    private fun Int.pow(x: Int): Int {
        return this.toDouble().pow(x.toDouble()).toInt()
    }
}
