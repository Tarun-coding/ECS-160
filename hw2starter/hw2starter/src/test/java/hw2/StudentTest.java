package hw2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.jdt.core.dom.ASTNode;
import org.junit.jupiter.api.Test;

public class StudentTest {
    @Test
    public void constructorShouldOnlyBeCalledOnce() {
            ASTNode ast = ParserUtil.parseJavaText(
                """
                class Test {
                    private static Test instance;

                    public static Test getInstance() {
                        instance=new Test();
                        if(instance == null) {
                            instance = new Test();
                        }
                        return instance;
                    }
                }
                """
            );
            assertFalse(SingletonChecker.checkConstructorCalledCorrectly(ast));
        

    }

    @Test
    public void contextCanHaveMoreFunctionsThanAbstractState() {
        assertTrue(
            StatePatternChecker.checkContextHasMatchingMethodNames(
                ParserUtil.parseJavaText(
                    """
                    class LibraryBook {
                        public void issue() {}
                        public void returnIt() {}
                        public void extend() {}
                        public void extraFunction() {}
                    }
                    """
                ),
                ParserUtil.parseJavaText(
                    """
                    interface LBState {
                        public void issue() {}
                        public void extend() {}
                        public void returnIt() {}
                    }
                    """
                )
            )
        );
    }
}
