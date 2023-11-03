package br.edu.ifpb.commands;

import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;
import br.edu.ifpb.validators.CPFValidator;
import br.edu.ifpb.validators.DateValidator;
import br.edu.ifpb.validators.GUITextValidator;
import br.edu.ifpb.validators.NonEmptyValidator;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditarPacienteGUICommand implements Command {
    private final JTextField nome;
    private final JTextField dataDeNascimento;
    private final JTextField cpf;
    private final JFrame frame;
    private final PacienteService service = new PacienteService(PacienteRepository.getInstance());

    public EditarPacienteGUICommand(JFrame frame, JTextField nome, JTextField dataDeNascimento, JTextField cpf) {
        this.frame = frame;
        this.nome = nome;
        this.dataDeNascimento = dataDeNascimento;
        this.cpf = cpf;
    }
    @Override
    public void execute() {
        String nomeStr = nome.getText();
        String dataStr = dataDeNascimento.getText();
        String cpfStr = cpf.getText();

        GUITextValidator nomeValidator = new GUITextValidator(new NonEmptyValidator());
        GUITextValidator dataValidator = new GUITextValidator(new DateValidator());
        GUITextValidator cpfValidator = new GUITextValidator(new CPFValidator(false));

        boolean nomeIsValid = nomeValidator.validate(nome);
        boolean dataIsValid = dataValidator.validate(dataDeNascimento);
        boolean cpfIsValid = cpfValidator.validate(cpf);
        if (nomeIsValid && dataIsValid && cpfIsValid) {
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            try {
                service.editar(nomeStr, df.parse(dataStr), cpfStr);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            JOptionPane.showMessageDialog(nome.getParent(), "Paciente editado com sucesso.");
            nome.setText("");
            dataDeNascimento.setText("");
            cpf.setText("");
            frame.setVisible(false);
        }
    }
}
