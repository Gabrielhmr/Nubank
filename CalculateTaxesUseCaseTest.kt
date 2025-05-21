package core

import org.example.core.CalculateTaxesUseCase
import org.example.data.OperationInput
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class CalculateTaxesUseCaseTest{

  private val useCase = CalculateTaxesUseCase()

  @Test
  fun `should return zero when value below 20k`() {
   val operations = listOf(
    OperationInput("buy", 10.00, 100),
    OperationInput("sell", 15.00, 50),
    OperationInput("sell", 15.00, 50)
   )

   val result = useCase.execute(operations)

   assertEquals(0.0, result[0]["tax"])
   assertEquals(0.0, result[1]["tax"])
   assertEquals(0.0, result[2]["tax"])
  }

  @Test
  fun `should return tax when profit above 20k and no loss before`() {
   val operations = listOf(
       OperationInput("buy", 10.00, 10000),
       OperationInput("sell", 20.00, 5000),
       OperationInput("sell", 5.00, 5000)
   )

   val result = useCase.execute(operations)

      assertEquals(0.0, result[0]["tax"])
      assertEquals(10000.0, result[1]["tax"])
      assertEquals(0.0, result[2]["tax"])
  }

  @Test
  fun `should return zero tax when no profit and no loss before`() {
   val operations = listOf(
       OperationInput("buy", 10.00, 10000),
       OperationInput("buy", 25.00, 5000),
       OperationInput("sell", 15.00, 10000)
   )

   val result = useCase.execute(operations)

      assertEquals(0.0, result[0]["tax"])
      assertEquals(0.0, result[1]["tax"])
      assertEquals(0.0, result[2]["tax"])
  }

  @Test
  fun `should return tax when selling with profit above 20k`() {
   val operations = listOf(
    OperationInput("buy", 10.00, 10000),
    OperationInput("buy", 25.00, 5000),
    OperationInput("sell", 15.00, 10000),
    OperationInput("sell", 25.00, 5000)
   )

   val result = useCase.execute(operations)

      assertEquals(0.0, result[0]["tax"])
      assertEquals(0.0, result[1]["tax"])
      assertEquals(0.0, result[2]["tax"])
      assertEquals(10000.0, result[3]["tax"])
  }

    @Test
    fun `should return tax when selling with profit above 20k and had loss before`() {
        val operations = listOf(
            OperationInput("buy", 10.00, 10000),
            OperationInput("sell", 2.00, 5000),
            OperationInput("sell", 20.00, 2000),
            OperationInput("sell", 20.00, 2000),
            OperationInput("sell", 25.00, 1000)
        )

        val result = useCase.execute(operations)

        assertEquals(0.0, result[0]["tax"])
        assertEquals(0.0, result[1]["tax"])
        assertEquals(0.0, result[2]["tax"])
        assertEquals(0.0, result[3]["tax"])
        assertEquals(3000.0, result[4]["tax"])
    }

    @Test
    fun `should return tax when buy twice and selling with profit above 20k and had loss before`() {
        val operations = listOf(
            OperationInput("buy", 10.00, 10000),
            OperationInput("sell", 2.00, 5000),
            OperationInput("sell", 20.00, 2000),
            OperationInput("sell", 20.00, 2000),
            OperationInput("sell", 25.00, 1000),
            OperationInput("buy", 20.00, 10000),
            OperationInput("sell", 15.00, 5000),
            OperationInput("sell", 30.00, 4350),
            OperationInput("sell", 30.00, 650)

        )

        val result = useCase.execute(operations)

        assertEquals(0.0, result[0]["tax"])
        assertEquals(0.0, result[1]["tax"])
        assertEquals(0.0, result[2]["tax"])
        assertEquals(0.0, result[3]["tax"])
        assertEquals(3000.0, result[4]["tax"])
        assertEquals(0.0, result[5]["tax"])
        assertEquals(0.0, result[6]["tax"])
        assertEquals(3700.0, result[7]["tax"])
        assertEquals(0.0, result[8]["tax"])
    }

    @Test
    fun `should return tax when buy twice and selling with profit above 20k and no loss before`() {
        val operations = listOf(
            OperationInput("buy", 10.00, 10000),
            OperationInput("sell", 50.00, 10000),
            OperationInput("buy", 20.00, 10000),
            OperationInput("sell", 50.00, 10000)

        )

        val result = useCase.execute(operations)

        assertEquals(0.0, result[0]["tax"])
        assertEquals(80000.0, result[1]["tax"])
        assertEquals(0.0, result[2]["tax"])
        assertEquals(60000.0, result[3]["tax"])
    }

    @Test
    fun `should return tax when buy more then twice and profit below 20k`() {
        val operations = listOf(
            OperationInput("buy", 5000.00, 10),
            OperationInput("sell", 4000.00, 5),
            OperationInput("buy", 15000.00, 5),
            OperationInput("buy", 4000.00, 2),
            OperationInput("buy", 23000.00, 2),
            OperationInput("sell", 20000.00, 1),
            OperationInput("sell", 12000.00, 10),
            OperationInput("sell", 15000.00, 3)

        )

        val result = useCase.execute(operations)

        assertEquals(0.0, result[0]["tax"])
        assertEquals(0.0, result[1]["tax"])
        assertEquals(0.0, result[2]["tax"])
        assertEquals(0.0, result[3]["tax"])
        assertEquals(0.0, result[4]["tax"])
        assertEquals(0.0, result[5]["tax"])
        assertEquals(1000.0, result[6]["tax"])
        assertEquals(2400.0, result[7]["tax"])
    }


}