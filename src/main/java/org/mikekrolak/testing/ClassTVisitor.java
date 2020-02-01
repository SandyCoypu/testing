package org.mikekrolak.testing;

import java.io.IOException;

import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class ClassTVisitor extends ClassVisitor {

	public ClassTVisitor() {
		super(Opcodes.ASM4);
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		System.out.println(name + " extends " + superName + " {");
		super.visit(version, access, name, signature, superName, interfaces);

	}

	public static void main(String[] args) throws IOException {
		ClassTVisitor cp  = new ClassTVisitor();
		ClassReader cr = new ClassReader("org.mikekrolak.testing.GetInfo");
		cr.accept(cp, 0);

	}

	public void visitAttribute(Attribute attr) {
	}

	public void visitInnerClass(String name, String outerName, String innerName, int access) {
	}

	public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
		System.out.println(" " + desc + " " + name);
		return null;
	}

	public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
		System.out.println("** " + name + " ::::::" +  desc);
		
		return null;
	}

	public void visitEnd() {
		System.out.println("}");
	}

}