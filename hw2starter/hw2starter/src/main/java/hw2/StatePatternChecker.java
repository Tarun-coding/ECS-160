package hw2;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTNode;

import hw2.visitors.StatePatternCheckerVisitor;

public final class StatePatternChecker {
    private StatePatternChecker() {
    }

    public static boolean 
    checkContextHasMatchingMethodNames(ASTNode context, ASTNode abstractState) {
        // I'm assuming I can create two seperate visitor instaces of the same class.
        // Then I get the returned has set from both and compare
        var contextVisitor = new StatePatternCheckerVisitor();
        context.accept(contextVisitor);

        var abstractStateVisitor = new StatePatternCheckerVisitor();
        abstractState.accept(abstractStateVisitor);

        HashSet<String> abstractStateSet = abstractStateVisitor.getMethodNames();
        HashSet<String> contextSet = contextVisitor.getMethodNames();

        abstractStateSet.removeAll(contextSet);
        return abstractStateSet.isEmpty();

    }
}
