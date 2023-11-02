package br.edu.ifpb.commands;

import br.edu.ifpb.domain.Paciente;
import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;

public class ListarPacientesCommand implements Command {
    @Override
    public void execute() {
        PacienteService pacienteService = new PacienteService(PacienteRepository.getInstance());

        System.out.println("\n\n=================================");
        System.out.println("Listar pacientes\n");
        for (Paciente p: pacienteService.getPacientes()) {
            System.out.println(p);
        }
    }
}
