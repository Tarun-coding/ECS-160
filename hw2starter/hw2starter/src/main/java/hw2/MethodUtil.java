package hw2;

import java.util.List;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;

public class MethodUtil {
	/**
	 * Gets the name of the return type of a method in a simple way
	 */
	public static String getMethodReturnTypeName(MethodDeclaration method) {
		return method.getReturnType2().toString();
		// This is somewhat hacky. The documentation specifically says that
		// the toString() method is intended for debugging only and relies
		// on an AST unparser. If we were writing this code "in the real world"
		// we would be hessitant to rely on the the toString method to give us
		// the type name as its implementation could change in the future.
		// However, pulling out the name is somewhat complicated due to the many
		// different subclasses of Type nodes. The assignement spec makes
		// the toString representation be correct by definition. Thus, this will
		// work well enough for our purposes.
	}

	public static List<Expression> getMethodInvocationArgs(MethodInvocation node) {
		@SuppressWarnings("unchecked") // docs specify type so can safely cast
		var args = (List<Expression>) node.arguments();
		return args;
	}

	/**
	 * Gets the name of a method in a simple way
	 */
	public static String getMethodName(MethodDeclaration method) {
		return method.getName().toString();
	}

	public static boolean methodIsPrivate(MethodDeclaration method) {
		// TODO?
		// Should be able use get
		// NodeUtil.getKeywordModsFromDeclaration() and see if any
		// modifiers isPrivate()
		// System.out.println(NodeUtil.getKeywordModsFromDeclaration(method));
		var modifierList = NodeUtil.getKeywordModsFromDeclaration(method);
		for (int i = 0; i < modifierList.size(); i++) {
			if ((modifierList.get(i)).isPrivate()) {
				return true;
			}

		}

		// assert false;
		return false;
	}

	public static boolean methodIsStatic(MethodDeclaration method) {
		// TODO?
		// Should be able use get
		// NodeetKeywordModsFromDeclaration() and see if any
		// modifiers isPrivate()
		var modifierList = NodeUtil.getKeywordModsFromDeclaration(method);
		for (int i = 0; i < modifierList.size(); i++) {
			if ((modifierList.get(i)).isStatic()) {
				return true;
			}

		}

		// assert false;
		return false;
	}

	public static boolean fieldIsPrivate(FieldDeclaration field) {
		var modifierList = NodeUtil.getKeywordModsFromDeclaration(field);
		for (int i = 0; i < modifierList.size(); i++) {
			if ((modifierList.get(i)).isPrivate()) {
				return true;
			}

		}

		// assert false;
		return false;

	}

	public static boolean fieldIsStatic(FieldDeclaration field) {
		var modifierList = NodeUtil.getKeywordModsFromDeclaration(field);
		for (int i = 0; i < modifierList.size(); i++) {
			if ((modifierList.get(i)).isStatic()) {
				return true;
			}

		}

		// assert false;
		return false;

	}

	public static boolean methodIsPublic(MethodDeclaration method) {
		// TODO?
		// Should be able use get
		// NodeetKeywordModsFromDeclaration() and see if any
		// modifiers isPublic()
		var modifierList = NodeUtil.getKeywordModsFromDeclaration(method);
		for (int i = 0; i < modifierList.size(); i++) {
			if ((modifierList.get(i)).isPublic()) {
				return true;
			}

		}

		// assert false;
		return false;
	}

	// TODO?
}
