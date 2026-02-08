# Calculator Test Suite Documentation

## Overview
This comprehensive test suite provides extensive coverage for the Calculator application using JUnit 5, Mockito, and AssertJ.

## Test Statistics
- **Total Tests**: 225
- **Test Success Rate**: 100%
- **Testing Framework**: JUnit 5.10.1
- **Assertion Library**: AssertJ 3.24.2
- **Code Coverage**: JaCoCo reports enabled

## Test Structure

### 1. CalculationUtils Tests (109 tests)
**File**: `src/test/java/com/calculator/CalculationUtilsTest.java`

#### Power and Root Calculations (8 tests)
- Power calculations with various bases and exponents
- Square root calculations
- Cube root calculations
- Parameterized tests for comprehensive coverage

#### Modulo Operations (5 tests)
- Various modulo calculations with different operands
- Edge cases and boundary values

#### Temperature Conversions (3 tests)
- Fahrenheit to Celsius conversion
- Celsius to Fahrenheit conversion
- Reversibility tests to ensure precision

#### Length Conversions (8 tests)
- Inches to centimeters
- Centimeters to inches
- Parameterized tests for multiple values
- Precision validation

#### Mass Conversions (8 tests)
- Ounces to grams
- Grams to ounces  
- Multiple test cases with parameterized inputs
- Precision checking

#### Binary Conversions (15 tests)
- Decimal to binary conversion
- Binary to decimal conversion
- Invalid input handling
- Reversibility tests
- Edge cases (0, 1, large numbers)

#### Hexadecimal Conversions (19 tests)
- Decimal to hexadecimal conversion
- Hexadecimal to decimal conversion
- Case-insensitive input handling
- Invalid input validation
- Reversibility tests

#### Basic Operations (7 tests)
- Addition, subtraction, multiplication, division
- Modulo operation
- Division by zero handling
- Invalid operation detection

#### Validation Methods (32 tests)
- Numeric string validation
- Binary string validation
- Hexadecimal string validation
- Null and empty string handling
- Whitespace-only string rejection

#### Edge Cases (4 tests)
- Very large numbers
- Very small decimals
- Negative number handling
- Zero handling across all operations

### 2. Integration Tests (23 tests)
**File**: `src/test/java/com/calculator/IntegrationTest.java`

#### Combined Operations and Conversions (3 tests)
- Arithmetic combined with temperature conversion
- Arithmetic combined with mass conversion
- Arithmetic combined with length conversion

#### Number System Conversion Integration (6 tests)
- Multiple number system conversions
- Arithmetic followed by number system conversion
- Consistency verification across systems
- Parameterized conversion tests

#### Scientific Calculations (3 tests)
- Power and root operation sequences
- Complex modulo calculations
- Combined scientific operations

#### Real-World Scenarios (4 tests)
- Recipe temperature conversion
- Shipping weight conversion
- Room dimension conversion
- Programming bit value calculations

#### Error Recovery and Validation (4 tests)
- Input validation before calculation
- Binary validation before conversion
- Hexadecimal validation before conversion
- Division by zero graceful handling

#### Performance and Precision (3 tests)
- Large number calculation accuracy
- Conversion chain precision maintenance
- Rapid sequential calculation handling

### 3. Validation Tests (93 tests)
**File**: `src/test/java/com/calculator/ValidationTest.java`

#### Numeric Input Validation (22 tests)
- Valid numeric strings (integers, decimals, negatives)
- Invalid numeric strings (alphabetic, mixed, formatted)
- Null and empty string handling
- Whitespace-only string rejection

#### Binary Input Validation (20 tests)
- Valid binary strings (various lengths)
- Invalid binary strings (non-binary digits)
- Null and empty handling
- Long binary string support

#### Hexadecimal Input Validation (20 tests)
- Valid hexadecimal strings (0-9, A-F, mixed case)
- Invalid hexadecimal strings (G-Z, special chars)
- Null and empty handling
- Mixed case support

#### Conversion Edge Cases (4 tests)
- Zero in all conversions
- Maximum 8-bit value (255)
- Powers of 2 in binary
- Powers of 16 in hexadecimal

#### Operation Validation (12 tests)
- Valid operation symbols (+, -, *, /, %)
- Invalid operation symbol rejection
- Edge case operation handling

#### Boundary Value Tests (5 tests)
- Large integer conversions
- Negative temperature handling
- Small decimal value handling
- Large decimal value handling

#### Exception Handling (4 tests)
- Invalid binary input exceptions
- Invalid hexadecimal input exceptions
- Division by zero exceptions
- Invalid operation exceptions

#### Special Character and Format Tests (3 tests)
- Special character rejection in numeric validation
- Leading zero handling in conversions
- All hexadecimal digits validation

#### Precision and Rounding Tests (3 tests)
- Temperature conversion precision
- Length conversion precision
- Floating-point arithmetic edge cases

## Test Execution

### Running All Tests
```bash
cd calculator
mvn clean test
```

### Running Specific Test Class
```bash
mvn test -Dtest=CalculationUtilsTest
mvn test -Dtest=IntegrationTest
mvn test -Dtest=ValidationTest
```

### Generating Coverage Report
```bash
mvn clean test
# Coverage report available at: target/site/jacoco/index.html
```

## Coverage Reports

### JaCoCo Coverage
After running tests, JaCoCo generates comprehensive coverage reports:
- **Location**: `calculator/target/site/jacoco/`
- **Main Report**: `index.html`
- **Format**: HTML, XML, and CSV

### Test Reports
Surefire generates detailed test execution reports:
- **Location**: `calculator/target/surefire-reports/`
- **Format**: XML and TXT files for each test class

## Key Features

### Test Organization
- **Nested Test Classes**: Logical grouping of related tests
- **Descriptive Names**: Clear, readable test and class names
- **Parameterized Tests**: Efficient testing of multiple inputs

### Assertion Library
- **AssertJ**: Fluent assertions for better readability
- **Precision Assertions**: `isCloseTo()` for floating-point comparisons
- **Exception Assertions**: `assertThatThrownBy()` for error cases

### Test Coverage
- **Unit Tests**: Individual method testing
- **Integration Tests**: Multi-component workflow testing
- **Validation Tests**: Input validation and edge cases
- **Boundary Tests**: Limit and extreme value testing

## Dependencies

```xml
<!-- JUnit 5 -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>5.10.1</version>
</dependency>

<!-- AssertJ for fluent assertions -->
<dependency>
    <groupId>org.assertj</groupId>
    <artifactId>assertj-core</artifactId>
    <version>3.24.2</version>
</dependency>

<!-- Mockito for mocking -->
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>5.7.0</version>
</dependency>
```

## Best Practices Implemented

1. **Descriptive Test Names**: Each test clearly describes what it tests
2. **Arrange-Act-Assert Pattern**: Consistent test structure
3. **One Assertion Per Test**: Focused, maintainable tests
4. **Parameterized Tests**: Efficient coverage of multiple scenarios
5. **Edge Case Testing**: Comprehensive boundary and limit testing
6. **Exception Testing**: Proper error condition validation
7. **Precision Handling**: Appropriate tolerance for floating-point comparisons
8. **Test Independence**: No test dependencies or shared state

## Future Enhancements

Potential areas for test expansion:
- GUI component testing (with headless mode support)
- Performance benchmarking tests
- Stress testing with extremely large datasets
- Concurrency testing for multi-threaded scenarios
- Property-based testing with jqwik or QuickTheories

## Notes

- GUI-dependent tests have been excluded due to X11 display requirements
- All tests are designed to run in CI/CD environments
- Code coverage reports are automatically generated with each test run
- Tests are compatible with Java 11+

## Maintenance

- Review and update tests when adding new features
- Maintain test coverage above 80% for critical business logic
- Run full test suite before committing changes
- Keep test dependencies up to date
