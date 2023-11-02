package br.edu.ifpb.validators;

import java.util.Scanner;


public class ValidationContext<T> {
    private Validator<T> validator;

    public ValidationContext(Validator<T> validator) {
        this.validator = validator;
    }

    public void setValidator(Validator<T> validator) {
        this.validator = validator;
    }

    public boolean validate(T data) {
        return validator.validate(data);
    }

    public T getValidValue(String prompt, String errorMsg, Class<T> type) {
        T data = null;
        Scanner sc = new Scanner(System.in);

        while (data == null) {
            System.out.print(prompt);
            if (type == String.class) {
                data = (T) sc.nextLine();
            } else if (type == Integer.class) {
                data = (T) Integer.valueOf(sc.nextInt());
                sc.nextLine();
            }

            if (!validator.validate(data)) {
                System.out.println(errorMsg);
                data = null;
            } else {
                break;
            }
        }

        return data;
    }
}
