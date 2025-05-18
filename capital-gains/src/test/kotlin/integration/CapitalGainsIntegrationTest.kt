import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class CapitalGainsIntegrationTest {

    @Test
    fun `test integration with sample input`() {
        val input = """
            [{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
            {"operation":"sell", "unit-cost":20.00, "quantity": 5000},
            {"operation":"sell", "unit-cost":5.00, "quantity": 5000}]
        """.trimIndent()

        val expectedOutput = """
            [{"tax": 0.0},{"tax": 10000.0},{"tax": 0.0}]
        """.trimIndent()

        // Redirect stdin
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // Redirect stdout
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Call the main function from App.kt
        main()

        // Verify the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim())
    }

    @Test
    fun `test integration with another sample input`() {
        val input = """
            [{"operation":"buy", "unit-cost":10.00, "quantity": 10000},
            {"operation":"sell", "unit-cost":20.00, "quantity": 5000},
            {"operation":"sell", "unit-cost":5.00, "quantity": 5000},
            {"operation":"sell", "unit-cost":25.00, "quantity": 5000}]
        """.trimIndent()

        val expectedOutput = """
            [{"tax": 0.0},{"tax": 10000.0},{"tax": 0.0},{"tax": 10000.0}]
        """.trimIndent()

        // Redirect stdin
        System.setIn(ByteArrayInputStream(input.toByteArray()))

        // Redirect stdout
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))

        // Call the main function from App.kt
        main()

        // Verify the output
        assertEquals(expectedOutput.trim(), outputStream.toString().trim())
    }
}