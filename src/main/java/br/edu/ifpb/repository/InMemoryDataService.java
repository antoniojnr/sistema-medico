package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Paciente;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDataService implements DataService {
    protected List<Paciente> pacientes = new ArrayList<>();
    @Override
    public void add(Paciente p) {
        pacientes.add(p);
    }

    @Override
    public List<Paciente> getAll() {
        return pacientes;
    }

    @Override
    public void update(Paciente p) {
        int index = pacientes.indexOf(p);
        pacientes.set(index, p);
    }

    @Override
    public List<Paciente> search(String termo) {
        return pacientes.stream().filter(p -> p.getNome().toLowerCase().contains(termo.toLowerCase())).toList();
    }

    @Override
    public boolean exists(String cpf) {
        return pacientes.stream().anyMatch(p -> p.getCpf().equals(cpf));
    }

    @Override
    public void remove(Paciente p) {
        pacientes.remove(p);
    }
}
