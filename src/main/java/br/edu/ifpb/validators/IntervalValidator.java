package br.edu.ifpb.validators;

public class IntervalValidator implements Validator<Integer> {
    private int min;
    private int max;

    public IntervalValidator(int min, int max) {
        this.min = min;
        this.max = max;
    }
    @Override
    public boolean validate(Integer data) {
        return data >= min && data <= max;
    }
}
