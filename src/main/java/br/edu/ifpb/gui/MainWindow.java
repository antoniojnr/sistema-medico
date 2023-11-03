package br.edu.ifpb.gui;

import br.edu.ifpb.domain.Paciente;
import br.edu.ifpb.repository.FileDataService;
import br.edu.ifpb.repository.PacienteRepository;
import br.edu.ifpb.service.PacienteService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow {
    private final JFrame frame;
    private final JList<Paciente> list;
    private final JPopupMenu menu;
    private final PacienteService dataService;

    public MainWindow() {
        frame = new JFrame("Sistema médico");
        list = new JList<>();
        menu = new JPopupMenu();
        JMenuItem editarItem = new JMenuItem("Editar");
        editarItem.addActionListener(e -> editarPaciente(list.getSelectedIndex()));
        JMenuItem excluirItem = new JMenuItem("Excluir");
        excluirItem.addActionListener(e -> excluirPaciente(list.getSelectedIndex()));
        menu.add(editarItem);
        menu.add(excluirItem);

        PacienteRepository repository = PacienteRepository.getInstance();
        repository.setRepository(new FileDataService());
        dataService = new PacienteService(repository);

        JButton criarBtn = new JButton("Criar");
        criarBtn.addActionListener(e -> {
            // PacienteWindow pode ser aberta para criação ou edição de um paciente
            // Se paciente for nulo, a janela funcionará para criar um novo
            // Caso contrário, carregará os dados para editar
            new PacienteWindow(this, null).show();
        });
        JButton editarBtn = new JButton("Editar");
        editarBtn.addActionListener(e -> editarPaciente(list.getSelectedIndex()));
        JButton excluirBtn = new JButton("Excluir");
        excluirBtn.addActionListener(e -> excluirPaciente(list.getSelectedIndex()));

        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.LEADING, 2, 2));
        panelButtons.setPreferredSize(new Dimension(400, 50));
        panelButtons.add(criarBtn);
        panelButtons.add(editarBtn);
        panelButtons.add(excluirBtn);

        list.setListData(dataService.getPacientes().toArray(new Paciente[0]));
        list.setPreferredSize(new Dimension(400, 200));

        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JList<Paciente> list = (JList<Paciente>)e.getSource(); // em que componente foi o clique? no JList

                // Se o botão esquerdo for clicado duas vezes
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    editarPaciente(list.locationToIndex(e.getPoint()));
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                showContextMenu(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                showContextMenu(e);
            }
        });
        JScrollPane listScroller = new JScrollPane(list);

        frame.add(panelButtons, BorderLayout.PAGE_START);
        frame.add(listScroller, BorderLayout.CENTER);
    }

    private void showContextMenu(MouseEvent e) {
        if (e.isPopupTrigger()) {
            // Melhora a usabilidade selecionando uma linha com o botão direito
            // antes de abrir o menu de contexto. Assim fica claro sobre qual
            // linha o botão foi acionado
            int index = list.locationToIndex(e.getPoint());
            list.setSelectedIndex(index);

            menu.show(e.getComponent(),
                    e.getX(), e.getY());
        }
    }

    private void editarPaciente(int listIndex) {
        Paciente p = dataService.get(listIndex);
        // PacienteWindow pode ser aberta para criação ou edição de um paciente
        // Se paciente for nulo, a janela funcionará para criar um novo
        // Caso contrário, carregará os dados para editar
        PacienteWindow cadastroWindow = new PacienteWindow(this, p);
        cadastroWindow.show();
    }

    private void excluirPaciente(int listIndex) {
        Paciente p = dataService.get(listIndex);
        int option = JOptionPane.showConfirmDialog(frame, String.format("Deseja excluir o paciente %s?", p.getNome()),
                "Excluir paciente", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            dataService.remover(p);
            JOptionPane.showMessageDialog(frame, "O paciente foi removido.");
            update();
        }
    }

    public void update() {
        list.setListData(dataService.getPacientes().toArray(new Paciente[0]));
    }

    public void show() {
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
//        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.show();
    }
}
