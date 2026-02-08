package calculator.src.test.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import calculator.src.main.java.com.calculator.exitFunction;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Exit Function Tests")
public class exitFunctionTest {
    
    @Test
    @DisplayName("Should create exitFunction instance successfully")
    void shouldCreateExitFunctionInstance() {
        exitFunction exit = new exitFunction();
        assertThat(exit).isNotNull();
    }
    
    @Test
    @DisplayName("Should be a subclass of run")
    void shouldBeSubclassOfRun() {
        exitFunction exit = new exitFunction();
        assertThat(exit).isInstanceOf(calculator.src.main.java.com.calculator.run.class);
    }
}
