package com.github.nine.rules.noaccessor;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;

import com.github.nine.entities.LineNumber;
import com.github.nine.entities.LineNumbers;
import com.github.nine.rules.Violation;
import com.github.nine.rules.ViolationChecker;
import com.github.nine.rules.ViolationType;

public class NoAccessorVisitor extends ViolationChecker{
    public static final ViolationType SETTER = new ViolationType("setter method found");
    public static final ViolationType GETTER = new ViolationType("getter method found");

    @Override
    public boolean visit(MethodDeclaration node) {
        SimpleName simpleName = node.getName();
        LineNumber lineNumber = getLineNumber(node.getStartPosition());
        String name = simpleName.getIdentifier();

        check(name, new LineNumbers(lineNumber));

        return super.visit(node);
    }

    private void check(String methodName, LineNumbers lineNumbers){
        if(methodName.matches("get[A-Z][a-zA-Z]*$")){
            addViolation(new Violation(GETTER, lineNumbers));
        }
        else if(methodName.matches("set[A-Z][a-zA-Z]*")){
            addViolation(new Violation(SETTER, lineNumbers));
        }
    }
}
