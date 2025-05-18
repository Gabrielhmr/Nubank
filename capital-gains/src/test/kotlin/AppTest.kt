import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppTest {

    @Test
    fun `test tax calculation for buy operation`() {
        val input = """[{"operation":"buy", "unit-cost":10.00, "quantity": 100}]"""
        val expectedOutput = """[{"tax": 0.0}]"""
        val actualOutput = App.calculateTaxes(input)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test tax calculation for sell operation with profit`() {
        val input = """[{"operation":"buy", "unit-cost":10.00, "quantity": 100},
                        {"operation":"sell", "unit-cost":20.00, "quantity": 50}]"""
        val expectedOutput = """[{"tax": 0.0}, {"tax": 1000.0}]"""
        val actualOutput = App.calculateTaxes(input)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test tax calculation for sell operation with loss`() {
        val input = """[{"operation":"buy", "unit-cost":20.00, "quantity": 100},
                        {"operation":"sell", "unit-cost":10.00, "quantity": 50}]"""
        val expectedOutput = """[{"tax": 0.0}, {"tax": 0.0}]"""
        val actualOutput = App.calculateTaxes(input)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test tax calculation for multiple operations`() {
        val input = """[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
                        {"operation":"sell", "unit-cost":20.00, "quantity": 5000},
                        {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]"""
        val expectedOutput = """[{"tax": 0.0}, {"tax": 10000.0}, {"tax": 0.0}]"""
        val actualOutput = App.calculateTaxes(input)
        assertEquals(expectedOutput, actualOutput)
    }

    @Test
    fun `test tax calculation for complex scenario`() {
        val input = """[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
                        {"operation":"sell", "unit-cost":2.00, "quantity": 5000},
                        {"operation":"sell", "unit-cost":20.00, "quantity": 3000}]"""
        val expectedOutput = """[{"tax": 0.0}, {"tax": 0.0}, {"tax": 1000.0}]"""
        val actualOutput = App.calculateTaxes(input)
        assertEquals(expectedOutput, actualOutput)
    }
}