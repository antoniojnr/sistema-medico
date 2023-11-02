package br.edu.ifpb;

import br.edu.ifpb.commands.*;
import br.edu.ifpb.repository.FileDataService;
import br.edu.ifpb.repository.PacienteRepository;

import java.util.*;

public class SistemaMedicoConsoleApplication {
    public static void main(String[] args) {
        PacienteRepository dataService = PacienteRepository.getInstance();
        dataService.setRepository(new FileDataService());
        CommandExecutor executor = new CommandExecutor();

        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 7) {
            System.out.println("\n=================================");
            System.out.println("MENU");
            System.out.println("[1] - Cadastrar paciente");
            System.out.println("[2] - Editar paciente");
            System.out.println("[3] - Listar pacientes");
            System.out.println("[4] - Buscar pacientes");
            System.out.println("[5] - Excluir paciente");
            System.out.println("[6] - Sair");

            System.out.print("Digite a opção -> ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> executor.executeCommand(new CadastrarPacienteCommand());
                case 2 -> executor.executeCommand(new EditarPacienteCommand());
                case 3 -> executor.executeCommand(new ListarPacientesCommand());
                case 4 -> executor.executeCommand(new BuscarPacienteCommand());
                case 5 -> executor.executeCommand(new ExcluirPacienteCommand());
                case 6 -> System.out.println("Tchau!");
                default -> System.out.println("Opção inválida");
            }
        }
    }
}

