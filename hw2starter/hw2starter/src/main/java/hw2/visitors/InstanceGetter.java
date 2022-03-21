package hw2.visitors;


import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;


import hw2.MethodUtil;

public class InstanceGetter extends ASTVisitor {
    private String className = "";
    private String methodReturnType = "";
    private boolean publicMethodExist = false;
    private boolean staticMethodExist = false;
    private boolean instanceGetterExist = false;

    @Override
	public boolean visit(TypeDeclaration node) {
        className = node.getName().getIdentifier(); //this will set the className for comparision
        return true; 
    }

    @Override
	public boolean visit(MethodDeclaration node) { //will check for static and private modifiers
       if (!(node.isConstructor())) {
            methodReturnType 
            = MethodUtil.getMethodReturnTypeName(node); //set the method Return type for comparison
            publicMethodExist = MethodUtil.methodIsPublic(node);
            staticMethodExist = MethodUtil.methodIsStatic(node);
            if (publicMethodExist && staticMethodExist) { //checks for private and static modifiers
                instanceGetterExist = className.equals(methodReturnType);
             }

         }
        return true; 
    }

   //getter method
   public boolean getInstanceGetterExist() {
        return instanceGetterExist;
    }    

}
