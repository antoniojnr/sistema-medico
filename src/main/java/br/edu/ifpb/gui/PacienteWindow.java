package br.edu.ifpb.gui;

import br.edu.ifpb.commands.CadastrarPacienteGUICommand;
import br.edu.ifpb.commands.CommandExecutor;
import br.edu.ifpb.commands.EditarPacienteGUICommand;
import br.edu.ifpb.domain.Paciente;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;

public class PacienteWindow {
    private final JFrame frame;
    private final JTextField txtNome;
    private final JTextField txtData;
    private final JTextField txtCPF;

    public PacienteWindow(MainWindow main, Paciente paciente) {
        CommandExecutor commandExecutor = new CommandExecutor();

        frame = new JFrame("Cadastro de paciente");
        JLabel labelNome = new JLabel("Nome:");
        JLabel labelData = new JLabel("Data de nascimento:");
        JLabel labelCPF = new JLabel("CPF:");
        txtNome = new JTextField();
        txtData = new JTextField();
        txtCPF = new JTextField();

        if (paciente != null) {
            txtNome.setText(paciente.getNome());
            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            txtData.setText(df.format(paciente.getDataDeNascimento()));
            txtCPF.setText(paciente.getCpf());
        }

        // A janela pode ser aberta para criação ou edição de um paciente
        JButton btnCriar = new JButton(paciente == null ? "Criar" : "Editar");
        btnCriar.addActionListener(e -> {
            // Se paciente for nulo, a janela funcionará para criar um novo
            // Caso contrário, carregará os dados para editar
            if (paciente == null) {
                commandExecutor.executeCommand(new CadastrarPacienteGUICommand(frame, txtNome, txtData, txtCPF));
            } else {
                commandExecutor.executeCommand(new EditarPacienteGUICommand(frame, txtNome, txtData, txtCPF));
            }

            main.update();
        });
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> frame.setVisible(false));

        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBorder(new EmptyBorder(10, 10, 0, 10));
        labelNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldsPanel.add(labelNome);
        txtNome.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtNome.setMaximumSize(new Dimension(400, 30));
        fieldsPanel.add(txtNome);

        labelData.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldsPanel.add(labelData);
        txtData.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtData.setMaximumSize(new Dimension(400, 30));
        fieldsPanel.add(txtData);

        labelCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        fieldsPanel.add(labelCPF);
        txtCPF.setAlignmentX(Component.LEFT_ALIGNMENT);
        txtCPF.setMaximumSize(new Dimension(400, 30));
        fieldsPanel.add(txtCPF);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(btnCancelar);
        buttonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        buttonPanel.add(btnCriar);

        frame.add(fieldsPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.PAGE_END);
    }

    public void update() {

    }

    public void show() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
