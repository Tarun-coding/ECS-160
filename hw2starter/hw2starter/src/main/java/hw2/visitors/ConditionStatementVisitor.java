package hw2.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class ConditionStatementVisitor extends ASTVisitor {
    //This keeps track of the total constructor calls in singleton. It can't exceed more than 1
    private int constructorCalls = 0;  
    private String constructorReturnType = "";
    private String className = "";
    private boolean constructorCalledProperly = false;
   
    @Override
	public boolean visit(TypeDeclaration node) {
        // TODO
        className = node.getName().getIdentifier(); //this will set the className for comparision
        return true; 
    }
   
    @Override
    public boolean visit(IfStatement conditionStatement) {
        if (!constructorCalledProperly) {
            var visitor = new ConstructorDeclarationVisitor();
            conditionStatement.accept(visitor); //now this goes into finding the constructor
            constructorReturnType = visitor.getConstructorReturnType();
            constructorCalledProperly = className.equals(constructorReturnType);
            }
        return true;
    }
    

    @Override
    public boolean visit(ClassInstanceCreation classInstance) {

        if (classInstance.getType().toString().equals(className)) {
            constructorCalls++;
        }
        
        return true;
    }

    public boolean getConstructorCalledProperly() {
        return (constructorCalledProperly && (constructorCalls == 1));
    }

  
    
}
