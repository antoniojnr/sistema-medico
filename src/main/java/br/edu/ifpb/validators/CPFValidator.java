package br.edu.ifpb.validators;

import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPFValidator implements Validator<String> {
    private final PacienteService pacienteService = new PacienteService(PacienteRepository.getInstance());
    private final boolean checkIfExists;

    public CPFValidator(boolean checkIfExists) {
        this.checkIfExists = checkIfExists;
    }

    @Override
    public boolean validate(String data) {
        String cpfPattern = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";

        // Use the Pattern and Matcher classes to perform the match
        Pattern pattern = Pattern.compile(cpfPattern);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches() && (!checkIfExists || !pacienteService.existe(data));
    }
}
