package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Paciente;

import java.util.List;

public class PacienteRepository {
    private DataService dataService;
    private static PacienteRepository instance;

    private PacienteRepository(DataService dataService) {
        this.dataService = dataService;
    }

    public static PacienteRepository getInstance() {
        if (instance == null) {
            instance = new PacienteRepository(new InMemoryDataService());
        }

        return instance;
    }

    public void setRepository(DataService dataService) {
        this.dataService = dataService;
    }

    public void add(Paciente p) {
        dataService.add(p);
    }

    public List<Paciente> getAll() {
        return dataService.getAll();
    }

    public void update(Paciente p) {
        dataService.update(p);
    }

    public List<Paciente> search(String termo) {
        return dataService.search(termo);
    }

    public boolean exists(String cpf) {
        return dataService.exists(cpf);
    }

    public void remove(Paciente p) {
        dataService.remove(p);
    }
}
