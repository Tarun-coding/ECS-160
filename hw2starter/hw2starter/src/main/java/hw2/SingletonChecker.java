package hw2;

import org.eclipse.jdt.core.dom.ASTNode;

import hw2.visitors.ConditionStatementVisitor;
import hw2.visitors.ConstructorStatsVisitor;
import hw2.visitors.InstanceGetter;
// TODO probably need new visitors
import hw2.visitors.PrivateStaticInstanceVisitor;

public final class SingletonChecker {
    private SingletonChecker() {
    }

    public static boolean checkPrivateConstructor(ASTNode ast) {
        // TODO
        var visitor = new ConstructorStatsVisitor();
        ast.accept(visitor);

        if ((visitor.getPrivateConstructorsVisited() > 0)
                && (visitor.getConstructorsVisitedCount() 
                == visitor.getPrivateConstructorsVisited())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkInstanceGetter(ASTNode ast) {
        // TODO
        // System.out.println("does this print?");
        // var visitor=new ConstructorStatsVisitor();
        // ast.accept(visitor); //asuming the ASTNode passed in is of type
        // declaration(lets test it)
        var visitor = new InstanceGetter();
        ast.accept(visitor);

        return visitor.getInstanceGetterExist();
    }

    public static boolean checkPrivateStaticVarForInstance(ASTNode ast) {
        // TODO
        var visitor = new PrivateStaticInstanceVisitor();
        ast.accept(visitor);

        // I can compare the class name with the type of the field
        return visitor.getPrivateStaticInstanceExist();
    }

    public static boolean checkConstructorCalledCorrectly(ASTNode ast) {
        // TODO

        var visitor = new ConditionStatementVisitor();
        ast.accept(visitor);
        return visitor.getConstructorCalledProperly();


    }

    public static boolean checkCouldBeSingleton(ASTNode ast) {
        return checkPrivateConstructor(ast) 
        && checkInstanceGetter(ast) 
        && checkPrivateStaticVarForInstance(ast)
                && checkConstructorCalledCorrectly(ast);
    }
}
