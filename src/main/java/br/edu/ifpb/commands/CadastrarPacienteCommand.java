package br.edu.ifpb.commands;

import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;
import br.edu.ifpb.validators.CPFValidator;
import br.edu.ifpb.validators.DateValidator;
import br.edu.ifpb.validators.NonEmptyValidator;
import br.edu.ifpb.validators.ValidationContext;

public class CadastrarPacienteCommand implements Command {
    @Override
    public void execute() {
        PacienteService pacienteService = new PacienteService(PacienteRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("Cadastrar paciente");
        System.out.println("=================================");

        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String nome = strValidationContext.getValidValue("Nome: ", "Nome não pode ser vazio", String.class);

        strValidationContext.setValidator(new DateValidator());
        String dataStr = strValidationContext.getValidValue("Data de nascimento: ", "Formato de data incorreto, use o formato 'dd/MM/yyyy'", String.class);

        strValidationContext.setValidator(new CPFValidator());
        String cpf = strValidationContext.getValidValue("CPF: ", "CPF inválido ou já cadastrado (formato: xxx.xxx.xxx-xx)", String.class);

        pacienteService.criar(nome, dataStr, cpf);
        System.out.println("\nPaciente cadastrado");
    }
}
