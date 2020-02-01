package org.mikekrolak.testing;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;

public class GetInfo extends AbstractHandler {

    private static final String JDT_NATURE = "org.michaelkrolak.testing";

    public static void main(String[] args) {
    	GetInfo gi = new GetInfo();
    	gi.execute(null);
    }
    
    @Override
    public Object execute(ExecutionEvent event) {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        // Get all projects in the workspace
        IProject[] projects = root.getProjects();
        // Loop over all projects
        for (IProject project : projects) {
            try {
                if (project.isNatureEnabled(JDT_NATURE)) {
                    analyseMethods(project);
                }
                else {
                	System.out.println(project.getName());
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void analyseMethods(IProject project) throws JavaModelException {
        IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
        // parse(JavaCore.create(project));
        for (IPackageFragment mypackage : packages) {
            if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
                createAST(mypackage);
            }

        }
    }

    private void createAST(IPackageFragment mypackage) throws JavaModelException {
        for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
            // now create the AST for the ICompilationUnits
            CompilationUnit parse = parse(unit);
            TMethodVisitor visitor = new TMethodVisitor();
            parse.accept(visitor);

            for (MethodDeclaration method : visitor.getMethods()) {
                System.out.print("Method name: " + method.getName()
                        + " Return type: " + method.getReturnType2());
            }

        }
    }

    /**
     * Reads a ICompilationUnit and creates the AST DOM for manipulating the
     * Java source file
     *
     * @param unit
     * @return
     */

    private static CompilationUnit parse(ICompilationUnit unit) {
        ASTParser parser = ASTParser.newParser(AST.JLS8);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(unit);
        parser.setResolveBindings(true);
        return (CompilationUnit) parser.createAST(null); // parse
    }
	public static void processJavaFile(File file) throws IOException {
		String source = FileUtils.readFileToString(file);
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(source.toCharArray());
		CompilationUnit unit = (CompilationUnit) parser.createAST(null);
		MyASTVisitor visitor = new MyASTVisitor(unit);
		unit.accept(visitor);
		visitor.printAllMethods();
	}
    private void test() {
    	int i =0;
    	int j = 2;
    	if(j>-1) {
    	switch(j){
    		case 0:
    		case 1:
    			break;
    		case 2:
    			break;
    		case 3:
    		case 4:
    			break;
    		default:
    			break;
    	}
    	}
    	else {
    		j=0;
    	}
    }
}