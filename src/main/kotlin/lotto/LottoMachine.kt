package lotto

import camp.nextstep.edu.missionutils.Randoms

class LottoMachine {

    private var numberOfLotto: Int = 0
    private lateinit var winningLotto: Lotto
    private var bonusNumber = 0

    fun start() {
        val paymentInput = View.getPaymentAmount()
        numberOfLotto = getNumberOfLottos(paymentInput)

        val lottos: ArrayList<Lotto> = arrayListOf()
        repeat(numberOfLotto) {
            lottos.add(generateLotto())
        }

        View.printNumberOfLottos(numberOfLotto)
        View.printLottos(lottos)

        val winningNumberInput = View.getWinningNumber()
        winningLotto = convertNumbersToLotto(winningNumberInput)

        bonusNumber = View.getBonusNumber().toInt()
        checkBonusNumberException(bonusNumber)
    }

    fun getNumberOfLottos(payment: String): Int {
        checkPaymentException(payment)
        return payment.toInt() / 1000
    }

    fun checkPaymentException(payment: String) {
        val price = payment.toIntOrNull() ?: throw IllegalArgumentException("[ERROR] 잘못된 입력입니다.")
        if (price % 1000 != 0) {
            throw IllegalArgumentException("[ERROR] 1000원으로 나눠지지 않습니다.")
        }
    }

    fun generateLotto(): Lotto {
        val randomNumbers = Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
        return Lotto(randomNumbers)
    }

    fun convertNumbersToLotto(numbers: String): Lotto {
        return Lotto(numbers.split(",").map { it.toInt() }.toList().sorted())
    }

    fun checkWinningNumberException() {
        TODO()
    }

    fun checkBonusNumberException(number: Int) {
        if (number !in 1..45) {
            throw IllegalArgumentException("[ERROR] 로또 번호 범위(1~45)를 벗어났습니다.")
        }
        if (winningLotto.getNumbers().contains(number)) {
            throw IllegalArgumentException("[ERROR] 당첨 번호와 중복됩니다.")
        }
    }

    fun calculateResult() {
        TODO()
    }

    fun calculateLottoResult() {
        TODO()
    }

    fun getEarningsRate(): Double {
        TODO()
    }

}