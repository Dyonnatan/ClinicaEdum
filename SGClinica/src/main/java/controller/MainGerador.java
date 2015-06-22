package controller;

import javax.swing.JOptionPane;

public class MainGerador {
	public static void main(String[] args) {
		String classe = JOptionPane.showInputDialog("Classe");

		GeradorXHTML g = null;
		try {
			g = new GeradorXHTML(Class.forName(classe));
		} catch (ClassNotFoundException e) {
		}
		if (g != null) {
			int i = JOptionPane.showConfirmDialog(null,
					"Cadastro? Sim \n ou Pesquisa");
			if (i == JOptionPane.YES_OPTION) {
				g.cadastro();
			} else {
				g.pesquisa();
			}
		}
	}
}
