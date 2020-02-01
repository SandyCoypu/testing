package org.mikekrolak.testing;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class ASTParsing {

	public static void main(String[] args) throws IOException {
		File dir = new File("C:\\Users\\ukrolmi\\eclipse-workspace\\testing\\src\\main\\java\\org\\mikekrolak\\testing\\");
		Collection<File> files = FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			ASTParsing.processJavaFile(file);
		}
	}

	public static void processDirectory(File dir) throws IOException {
		Collection<File> files = FileUtils.listFiles(dir, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
		for (File file : files) {
			ASTParsing.processJavaFile(file);
		}
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
}
