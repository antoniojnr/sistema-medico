package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Paciente implements Serializable {
    private String nome;
    private Date dataDeNascimento;
    private final String cpf;

    public Paciente(String nome, Date dataDeNascimento, String cpf) {
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(Date dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente paciente)) return false;
        return Objects.equals(getCpf(), paciente.getCpf());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCpf());
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", nome, cpf);
    }
}
