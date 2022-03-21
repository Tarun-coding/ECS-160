package hw2.visitors;

import java.util.HashSet;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import hw2.MethodUtil;

public class StatePatternCheckerVisitor extends ASTVisitor {
    private HashSet<String> methodNames 
    = new HashSet<String>(); // Method names that we will be returning

    public boolean visit(MethodDeclaration node) {
        // if node is public only then can i add to methodNames
        if (MethodUtil.methodIsPublic(node)) {
            methodNames.add(MethodUtil.getMethodName(node));
        }
        return true;

    }

    // getter method
    public HashSet<String> getMethodNames() {
        return methodNames;
    }

}
