package controller;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class GeradorCadastro {

	Class<?> classe;

	public GeradorCadastro(Class<?> classe) {
		this.classe = classe;
	}

	public void cadastro() {

		ArrayList<Field[]> camposArr = buscaSuperClass(classe);

		StringBuilder b = new StringBuilder(headerCadastro(classe));
		for (Field[] fields : camposArr) {
			for (Field f : fields) {
				f.setAccessible(true);
				String n = f.getName();

				b.append(String
						.format("<p:outputLabel value=\"%s\" for=\"%s\" />"
								+ "<p:inputText id=\"%s\" size=\"20\" maxlength=\"20\" "
								+ "value=\"#{cadastro%sBean.%s.%s}\" />",
								nomeParaValor(n) + ": ", n, n,
								classe.getSimpleName(), classe.getSimpleName()
										.substring(0, 1).toLowerCase()
										+ classe.getSimpleName().substring(1),
								n));
			}
		}

		b.append("			</p:panelGrid>		</h:form>");
		System.out.println(b);

		StringSelection selection = new StringSelection(b.toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);

	}

	public void pesquisa() {
		ArrayList<Field[]> camposArr = buscaSuperClass(classe);

		StringBuilder b = new StringBuilder();
		for (Field[] fields : camposArr) {
			for (Field f : fields) {
				f.setAccessible(true);
				String n = f.getName();
				String nomeMinusc = classe.getSimpleName().substring(0, 1)
						.toLowerCase()
						+ classe.getSimpleName().substring(1);

				b.append(String.format(
						"<p:column headerText=\"%s\" style=\"text-align: center; width: 100px\">"
								+ "		<h:outputText value=\"#{%s.%s}\" />"
								+ "</p:column>", nomeParaValor(n), nomeMinusc,
						n));
			}
		}

		System.out.println(b);

		StringSelection selection = new StringSelection(b.toString());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(selection, selection);

	}

	private String nomeParaValor(String n) {
		char[] v = n.toCharArray();
		StringBuilder nome = new StringBuilder();
		nome.append(Character.toUpperCase(v[0]));
		for (int i = 1; i < v.length; i++) {
			if (Character.isUpperCase(v[i])) {
				nome.append(' ');
				nome.append(v[i]);
			} else {
				nome.append(v[i]);
			}
		}
		return nome.toString();
	}

	private ArrayList<Field[]> buscaSuperClass(Class<?> c) {
		ArrayList<Field[]> arr = new ArrayList<Field[]>();

		if (c.getSuperclass() != null) {
			arr.addAll(buscaSuperClass(c.getSuperclass()));
			arr.add(c.getDeclaredFields());
		}
		return arr;
	}

	private String headerCadastro(Class<?> c) {
		return String
				.format("<h:form id=\"frmCadastro\">"
						+ "			<p:messages id=\"messages\" autoUpdate=\"true\" closable=\"true\" />"
						+ ""
						+ "			<p:toolbar style=\"margin-top: 20px\">"
						+ "				<p:toolbarGroup>"
						+ "					<p:commandButton value=\"Salvar\" id=\"botaoSalvar\""
						+ "						action=\"#{cadastro%sBean.salvar}\" update=\"frmCadastro\" />"
						+ "				</p:toolbarGroup>"
						+ "				<p:toolbarGroup align=\"right\">"
						+ "					<p:button value=\"Pesquisa\" outcome=\"/%s/Pesquisa%s.xhtml\" />"
						+ "				</p:toolbarGroup>"
						+ "			</p:toolbar>"
						+ "<p:panelGrid columns=\"2\" id=\"painel\" style=\"width: 100%%\""
						+ "				columnClasses=\"rotulo, campo\">",
						c.getSimpleName(), c.getSimpleName().substring(0, 1)
								.toLowerCase()
								+ c.getSimpleName().substring(1),
						c.getSimpleName());

	}

}
