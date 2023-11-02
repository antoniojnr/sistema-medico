package br.edu.ifpb.validators;

public class NonEmptyValidator implements Validator<String> {
    @Override
    public boolean validate(String data) {
        return !data.equals("");
    }
}
