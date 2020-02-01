package org.mikekrolak.testing;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

public class MyASTVisitor extends ASTVisitor {
	private CompilationUnit cu;
	private int cyclotomicComplexityCounter = 1;
	private List<MethodInfo> methods = new ArrayList<MethodInfo>();

	public MyASTVisitor(final CompilationUnit cu) {
		this.cu = cu;
	}

	@Override
	public void endVisit(final MethodDeclaration md) {
		int loc = 1 + cu.getLineNumber(md.getStartPosition() + md.getLength())
		- cu.getLineNumber(md.getBody().getStartPosition());
		methods.add(new MethodInfo(md.getName().getFullyQualifiedName(), loc, md.parameters(), md.getReturnType2(),cyclotomicComplexityCounter));
		cyclotomicComplexityCounter = 1;
	}
	
	public void printAllMethods() {
		methods.stream().forEach(System.out::println);
	}
	
	@Override
	public boolean visit(final ReturnStatement node) {
	//	cyclotomicComplexityCounter++;
		return true;
	}

	@Override
	public boolean visit(SynchronizedStatement node) {
		node.getBody().statements().forEach(System.out::println);
		return true;
	}
	
	@Override
	public boolean visit(ExpressionStatement node) {
		System.out.println("Expression at "+ cu.getLineNumber(node.getStartPosition()));
		return true;
	}
	
	@Override 
	public boolean visit(TypeDeclarationStatement node) {
		return true;
	}

	@Override
	public boolean visit(IfStatement node) {
		cyclotomicComplexityCounter++;
		return true;
	}


	@Override
	public boolean visit(ForStatement node) {
		System.out.println("For at "+ cu.getLineNumber(node.getStartPosition()));
		cyclotomicComplexityCounter++;
		return true;
	}


	@Override
	public boolean visit(MethodDeclaration node) {
		
		System.out.println("method" +  node.getName().getFullyQualifiedName() + "(");
        List<SingleVariableDeclaration> params = node.parameters();

        for(SingleVariableDeclaration param: params) {
            System.out.println("param" + param.getName().getFullyQualifiedName());
        }
        
        Block methodBlock = node.getBody();
        String myblock = methodBlock.toString();
   //     System.out.println(myblock);
		return true;
	}
	
	
	@Override
	public boolean visit(ImportDeclaration node) {
		System.out.println(node.getName().getFullyQualifiedName());
		return true;
	}

	@Override
	public boolean visit(DoStatement node) {
		System.out.println("Do at "+ cu.getLineNumber(node.getStartPosition()));
		cyclotomicComplexityCounter++;
		return true;
	}
	
	@Override
	public boolean visit(EnhancedForStatement node) {
		System.out.println("Enhanced For at "+ cu.getLineNumber(node.getStartPosition()));
		cyclotomicComplexityCounter++;
		return true;
	}

	@Override
	public boolean visit(SwitchStatement node) {
		return true;
	}
	@Override
	public boolean visit(BreakStatement node) {
		cyclotomicComplexityCounter++;
		return true;
	}
	
	@Override
	public boolean visit(WhileStatement node) {
		cyclotomicComplexityCounter++;
		return true;
	}

	@Override
	public boolean visit(TryStatement node) {
		return true;
	} 
	
	@Override
	public boolean visit(ConstructorInvocation node) {
		return true;
	}
	
	@Override
	public boolean visit(SuperConstructorInvocation node) {
		return true;
	}
	
	public boolean visit(SuperMethodInvocation node) {
		return true;
	}
}
