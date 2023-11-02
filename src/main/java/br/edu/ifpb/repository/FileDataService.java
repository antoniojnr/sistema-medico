package br.edu.ifpb.repository;

import br.edu.ifpb.domain.Paciente;

import java.io.*;
import java.util.List;

public class FileDataService extends InMemoryDataService {
    public FileDataService() {
        File data = new File("data.bin");
        if (data.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.bin"))) {
                pacientes = (List<Paciente>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Paciente p) {
        super.add(p);
        write();
    }

    @Override
    public void update(Paciente p) {
        super.update(p);
        write();
    }

    @Override
    public void remove(Paciente p) {
        super.remove(p);
        write();
    }

    private void write() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.bin"))) {
            oos.writeObject(getAll());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
