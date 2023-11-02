package br.edu.ifpb.commands;

import br.edu.ifpb.domain.Paciente;
import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;
import br.edu.ifpb.validators.IntervalValidator;
import br.edu.ifpb.validators.NonEmptyValidator;
import br.edu.ifpb.validators.ValidationContext;

import java.util.List;

public class ExcluirPacienteCommand implements Command {
    @Override
    public void execute() {
        PacienteService pacienteService = new PacienteService(PacienteRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("Excluir paciente\nBusque um paciente para excluir\n");
        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String termo = strValidationContext.getValidValue("Digite parte do nome para buscar: ", "Termo de busca não pode ser vazio", String.class);

        List<Paciente> resultado = pacienteService.buscar(termo);

        System.out.println("\nResultado:\n");
        int indice = 0;
        for (Paciente p: resultado) {
            System.out.println(++indice + " - " + p);
        }

        ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IntervalValidator(1, indice));
        int indiceDigitado = intValidationContext.getValidValue("Digite o índice do paciente que você quer remover: ",
                String.format("O índice deve ser um valor entre 1 e %d (inclusive)%n", indice), Integer.class);

        pacienteService.remover(resultado.get(indiceDigitado - 1));
        System.out.println("\nPaciente removido");
    }
}
