package app;

import java.awt.EventQueue;
import java.util.List;

import controller.VistaController;
import model.Arista;
import model.Grafo;
import view.Vista;

public class Main {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(() -> {
            Vista vista = new Vista();
            new VistaController(vista); 
            vista.setVisible(true);
        });
	}
		
}
