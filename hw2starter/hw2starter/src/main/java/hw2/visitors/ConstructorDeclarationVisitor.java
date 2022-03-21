package hw2.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;


public class ConstructorDeclarationVisitor extends ASTVisitor {
    private String constructorReturnType = "";

    public boolean visit(ClassInstanceCreation classInstance) {
        constructorReturnType = classInstance.getType().toString();
        
        return true;
    }
    
    public String getConstructorReturnType() {
        return constructorReturnType; //constructor return type
    }


}
