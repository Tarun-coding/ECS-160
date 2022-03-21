package hw2.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import hw2.MethodUtil;

public class PrivateStaticInstanceVisitor extends ASTVisitor {
    String className = "";
    String fieldType = "";
    boolean privateFieldExist = false;
    boolean staticFieldExist = false;
    boolean privateStaticInstanceExist = false;

    @Override
    public boolean visit(TypeDeclaration node) { // initializes the className
        // TODO
        className = node.getName().getIdentifier(); // this will set the className for comparision
        return true;
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        // System.out.println(count);
        // count++;
        if (!privateStaticInstanceExist) {
            fieldType = node.getType().toString();
            privateFieldExist = MethodUtil.fieldIsPrivate(node);
            staticFieldExist = MethodUtil.fieldIsStatic(node);
            if (privateFieldExist && staticFieldExist) {
                privateStaticInstanceExist = className.equals(fieldType);
            }
        }
        return true;
    }

    public boolean getPrivateStaticInstanceExist() {
        return privateStaticInstanceExist;
    }
}
