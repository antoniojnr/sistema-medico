package br.edu.ifpb.validators;

import javax.swing.text.JTextComponent;
import java.awt.*;

public class GUITextValidator implements GUIValidator {
    private final Validator<String> validator;

    public GUITextValidator(Validator<String> validator) {
        this.validator = validator;
    }

    @Override
    public boolean validate(JTextComponent field) {
        boolean textIsValid = getStringValidator().validate(field.getText());

        if (!textIsValid) {
            field.setBackground(new Color(255, 155, 163));
        } else {
            field.setBackground(Color.WHITE);
        }

        return textIsValid;
    }

    @Override
    public Validator<String> getStringValidator() {
        return validator;
    }
}
