package com.group.libraryapp.calculator

import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class CalculatorTestWithJnuit {
    @Test
    fun addTest(){
        val calculator=Calculator(5)

        calculator.add(3)

        assertThat(calculator.number).isEqualTo(8)
    }

    @Test
    fun minusTest(){
        val calculator=Calculator(5)

        calculator.minus(3)

        assertThat(calculator.number).isEqualTo(2)
    }
    @Test
    fun multiTest(){
        val calculator=Calculator(5)

        calculator.multiply(3)

        assertThat(calculator.number).isEqualTo(15)
    }

    @Test
    fun diviTest(){
        val calculator=Calculator(5)

        calculator.divide(3)

        assertThat(calculator.number).isEqualTo(1)
    }

    @Test
    fun diviExceptionTest(){
        val calculator=Calculator(5)

        assertThrows<IllegalArgumentException> {
            calculator.divide(0)
        }.apply { assertThat(message).isEqualTo("0으로 나눌 수 없습니다.") }


    }

}