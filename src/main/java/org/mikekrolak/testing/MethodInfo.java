package org.mikekrolak.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclaration;

public class MethodInfo {

	private String name;
	private int loc = 0;
	private int cyclotomicComplexity = 0;
	private Type returnType;
	private List<VariableDeclaration> parameters = new ArrayList<VariableDeclaration>();

	public MethodInfo(String name, int loc, List<VariableDeclaration> parameters, Type returnType,
			int cyclotomicComplexity) {
		this.name = name;
		this.loc = loc;
		this.parameters = parameters.stream().collect(Collectors.toList());
		this.returnType = returnType;
		this.cyclotomicComplexity = cyclotomicComplexity;
	}

	public String getParameters() {
		StringBuffer params = new StringBuffer("");
		for (VariableDeclaration variableDeclaration : this.parameters) {
			params.append(
					variableDeclaration.getStructuralProperty(SingleVariableDeclaration.TYPE_PROPERTY).toString());
			for (int i = 0; i < variableDeclaration.getExtraDimensions(); i++) {
				params.append("[]");
			}
			params.append(", ");
		}
		return params.length() == 0 ? "" : params.substring(0, params.length() - 2);
	}

	public String toString() {
		return "name: " + this.name + " (" + getParameters() + ") loc: " + this.loc + " Cyclotomic Complexity: "
				+ this.cyclotomicComplexity;
	}
}
