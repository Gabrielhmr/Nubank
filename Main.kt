import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.example.core.CalculateTaxesUseCase
import org.example.data.OperationInput
import org.example.core.TaxCalculator
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.regex.Pattern

fun main() {
    val mapper = jacksonObjectMapper()
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val arrayPattern = Pattern.compile("""\[[^\[\]]*]""")

    val taxCalculator: TaxCalculator = CalculateTaxesUseCase()

    reader.lineSequence().forEach { line ->
        if (line.isNotBlank()) {
            val matcher = arrayPattern.matcher(line)
            while (matcher.find()) {
                val arrayStr = matcher.group()
                val operationInputs: List<OperationInput> = mapper.readValue(arrayStr)
                val taxes = taxCalculator.execute(operationInputs)
                val result = mapper.writeValueAsString(taxes)
                println(result)
            }
        }
    }
}