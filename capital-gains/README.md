# Capital Gains Tax Calculator

This project is a command-line application that calculates the tax on profits or losses from stock market operations based on defined rules. The application processes financial operations provided in JSON format and outputs the calculated taxes in JSON format.

## Project Structure

- **src/main/kotlin/App.kt**: Entry point of the application. Contains the main function that reads input from stdin, processes the JSON operations, calculates the taxes, and outputs the results.
  
- **src/main/kotlin/model/Operation.kt**: Defines the `Operation` data class, representing a financial operation with properties for `operation` (buy/sell), `unitCost`, and `quantity`. Includes methods for calculating the weighted average cost and determining the tax based on the operation type.

- **src/test/kotlin/AppTest.kt**: Contains unit tests for the functionality in `App.kt`. Tests various scenarios to ensure accurate tax calculations based on different input operations.

- **src/test/kotlin/integration/CapitalGainsIntegrationTest.kt**: Contains integration tests that validate the overall functionality of the application, ensuring that the complete flow from input to output behaves as expected with real input data.

- **build.gradle.kts**: Gradle build configuration file for Kotlin. Specifies dependencies, plugins, and tasks needed to build and run the project.

- **settings.gradle.kts**: Contains settings for the Gradle project, including the project name and any included modules.

## How to Compile and Run

1. Ensure you have [Gradle](https://gradle.org/install/) installed on your machine.
2. Clone this repository to your local machine.
3. Navigate to the project directory.
4. To build the project, run:
   ```
   ./gradlew build
   ```
5. To run the application, use:
   ```
   ./gradlew run
   ```
   You can provide input through standard input (stdin) or redirect from a file.

## Running Tests

To run the unit and integration tests, execute:
```
./gradlew test
```

## Additional Notes

- The application is designed to handle multiple lines of input independently, processing each line as a separate simulation.
- The output is formatted in JSON, making it easy to integrate with other systems or tools.
- Ensure that the input adheres to the specified JSON format for accurate processing.

This project aims to provide a simple yet effective solution for calculating capital gains tax on stock market operations, adhering to the specified rules and regulations.