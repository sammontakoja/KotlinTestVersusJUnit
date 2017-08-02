import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DisplayName("Class")
public class BombSpec {

    @Nested
    class TestClass {

        @Test
        @DisplayName("function should fail")
        void function() {
            assertThat("", is(""));
        }
    }
}
