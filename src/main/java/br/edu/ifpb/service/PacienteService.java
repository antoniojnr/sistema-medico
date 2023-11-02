package br.edu.ifpb.service;

import br.edu.ifpb.domain.Paciente;
import br.edu.ifpb.repository.PacienteRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PacienteService {
    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }
    public void criar(String nome, String dataStr, String cpf) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date data = dateFormat.parse(dataStr);
            repository.add(new Paciente(nome, data, cpf));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public List<Paciente> buscar(String termo) {
        return repository.search(termo);
    }

    public List<Paciente> getPacientes() {
        return repository.getAll();
    }

    public boolean existe(String cpf) {
        return repository.exists(cpf);
    }

    public void editar(Paciente p) {
        repository.update(p);
    }

    public void remover(Paciente p) {
        repository.remove(p);
    }
}
