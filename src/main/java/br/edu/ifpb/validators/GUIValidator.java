package br.edu.ifpb.validators;

import javax.swing.text.JTextComponent;

public interface GUIValidator {
    Validator<String> getStringValidator();
    boolean validate(JTextComponent field);
}
