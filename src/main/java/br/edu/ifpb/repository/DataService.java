package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Paciente;

import java.util.List;

public interface DataService {
    void add(Paciente p);
    List<Paciente> getAll();
    void update(Paciente p);
    List<Paciente> search(String termo);
    boolean exists(String cpf);
    void remove(Paciente p);
}
