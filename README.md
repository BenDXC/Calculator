# Java Calculator

A professional Java desktop calculator application with comprehensive calculation and conversion capabilities.

## Features

**Basic Operations**
- Arithmetic: Addition, Subtraction, Multiplication, Division, Modulo
- Sign toggle, decimal support, backspace, and clear

**Advanced Calculations**
- Power and root operations (square, cube)
- Temperature conversion (Celsius ↔ Fahrenheit)
- Length conversion (inches ↔ centimeters)
- Mass conversion (ounces ↔ grams)
- Number system conversion (decimal ↔ binary ↔ hexadecimal)

## Tech Stack

- **Language:** Java 11+
- **GUI:** Java Swing/AWT
- **Build Tool:** Maven
- **Testing:** JUnit 5, AssertJ, Mockito
- **Code Coverage:** JaCoCo

## Quick Start

### Prerequisites
- Java JDK 11 or higher
- Maven 3.6+

### Build & Run

```bash
# Clone repository
git clone <repository-url>
cd Calculator

# Build with Maven
mvn clean install

# Run application
java -jar calculator/target/calculator-1.0.0.jar

# Or run with Maven
mvn -f calculator/pom.xml exec:java
```

### Run Tests

```bash
# Run all tests
mvn test

# Run tests with coverage report
mvn clean test
# View coverage report at: calculator/target/site/jacoco/index.html
```

## Architecture

The application follows clean architecture principles with clear separation of concerns:

- **`Calculator`** - Swing GUI and event handling
- **`CalculationUtils`** - Pure calculation logic (100% test coverage)
- **`ConversionDialog`** - Dialog-based conversion operations
- **`InputValidator`** - Input validation utilities

All business logic is thoroughly tested with 258 comprehensive unit and integration tests.

## Project Structure

```
calculator/
├── src/
│   ├── main/java/com/calculator/
│   │   ├── Calculator.java           # Main GUI
│   │   ├── CalculationUtils.java     # Business logic
│   │   ├── ConversionDialog.java     # Conversion dialogs
│   │   └── InputValidator.java       # Input validation
│   └── test/java/com/calculator/
│       ├── CalculationUtilsTest.java # Unit tests
│       ├── IntegrationTest.java      # Integration tests
│       └── ValidationTest.java       # Validation tests
├── pom.xml                           # Maven configuration
└── COVERAGE_REPORT.md                # Detailed coverage report
```

## Code Quality

- ✅ **Final utility classes** with private constructors
- ✅ **Named constants** for all magic numbers
- ✅ **100% business logic coverage**
- ✅ **Clean, maintainable code** following Java best practices
- ✅ **Comprehensive error handling**

## Testing

**Test Coverage:**
- 258 total tests (all passing)
- 100% coverage on CalculationUtils (business logic)
- Unit, integration, and validation test suites
- Edge case and boundary testing

## License

Eclipse Public License v2.0

## Contributing

This project demonstrates professional Java development practices including:
- Clean code architecture
- Comprehensive testing
- Proper separation of concerns
- Maven build automation
- Continuous integration readiness
