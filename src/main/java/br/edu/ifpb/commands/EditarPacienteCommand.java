package br.edu.ifpb.commands;

import br.edu.ifpb.domain.Paciente;
import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;
import br.edu.ifpb.validators.IntervalValidator;
import br.edu.ifpb.validators.NonEmptyValidator;
import br.edu.ifpb.validators.OptionalDateValidator;
import br.edu.ifpb.validators.ValidationContext;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EditarPacienteCommand implements Command {
    @Override
    public void execute() {
        PacienteService pacienteService = new PacienteService(PacienteRepository.getInstance());

        System.out.println("\n=================================");
        System.out.println("Editar paciente\nBusque um paciente para editar\n");

        ValidationContext<String> strValidationContext = new ValidationContext<>(new NonEmptyValidator());
        String termo = strValidationContext.getValidValue("Digite parte do nome para buscar: ", "Termo de busca não pode ser vazio", String.class);

        List<Paciente> resultado = pacienteService.buscar(termo);

        System.out.println("\nResultado:\n");
        int indice = 0;
        for (Paciente p: resultado) {
            System.out.println(++indice + " - " + p);
        }

        ValidationContext<Integer> intValidationContext = new ValidationContext<>(new IntervalValidator(1, indice));
        int indiceDigitado = intValidationContext.getValidValue("Digite o índice do paciente que você quer editar: ",
                String.format("O índice deve ser um valor entre 1 e %d (inclusive)%n", indice), Integer.class);

        Paciente toEdit = resultado.get(indiceDigitado - 1);

        System.out.print("Digite um novo nome (ou deixe vazio para não mudar): ");
        String nome = new Scanner(System.in).nextLine();

        strValidationContext.setValidator(new OptionalDateValidator());
        Date data = null;
        String dataStr = strValidationContext.getValidValue("Digite uma nova data de nascimento (ou deixe vazio para não mudar): ", "Formato de data incorreto, use o formato 'dd/MM/yyyy'", String.class);

        if (!nome.equals("")) {
            toEdit.setNome(nome);
        }
        if (!dataStr.equals("")) {
            toEdit.setDataDeNascimento(data);
        }

        pacienteService.editar(toEdit);
        System.out.println("\nPaciente editado");
    }
}
