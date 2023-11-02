package br.edu.ifpb.commands;


import br.edu.ifpb.domain.Paciente;
import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;
import br.edu.ifpb.validators.NonEmptyValidator;
import br.edu.ifpb.validators.ValidationContext;

import java.util.List;

public class BuscarPacienteCommand implements Command {
    @Override
    public void execute() {
        PacienteService pacienteService = new PacienteService(PacienteRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("Buscar pacientes\n");
        System.out.print("Digite parte do nome para buscar: ");
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String nome = strValidationContext.getValidValue("Digite parte do nome para buscar: ", "Termo de busca não pode ser vazio", String.class);

        List<Paciente> resultado = pacienteService.buscar(nome);

        System.out.println("\nResultado:\n");
        for (Paciente p: resultado) {
            System.out.println(p);
        }
    }
}