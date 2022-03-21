package hw2.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import hw2.MethodUtil;

public class ConstructorStatsVisitor extends ASTVisitor {
    private int constructorsVisitedCount = 0;
    private int privateConstructorsVisitedCount = 0;

    @Override
	public boolean visit(MethodDeclaration node) {
       if (node.isConstructor()) { 
            constructorsVisitedCount++;
            if (MethodUtil.methodIsPrivate(node)) {
                privateConstructorsVisitedCount++;
            }
        }
        
        //assuming this is going to be false: a constructor is never going to be nested in another
        return false;  
    }

    public int getConstructorsVisitedCount() {
        return constructorsVisitedCount;
    }

    public int getPrivateConstructorsVisited() {
        return privateConstructorsVisitedCount;
    }
 
}
