package testingTool;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class HerramientaTesting extends JFrame {

	JFileChooser chooser;
	File file;
	JTextArea classCodeTxtArea;
	JList<File> fileList = new JList<>();
	Directory directory = new Directory();
	private static final long serialVersionUID = 1L;

	public HerramientaTesting() {

		this.add(new myPanel(fileList, directory));

		// automatiza la vista del frame a la pantalla del computador

		Dimension dimension = new Dimension(1024,600);

		this.setSize(dimension);

		// ----------Inicio Creacion de la barra de menu con los items
		// correspondientes-------------

		JMenu menu = new JMenu("Archivo");

		JMenuBar menuBar = new JMenuBar();

		JMenuItem exitItem = new JMenuItem("Salir");
		JMenuItem fileItem = new JMenuItem("Elegir Carpeta....");

		fileItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				openShowDialog(arg0);
				obtenerArchivo();
				cargarArchivos();
			}

		});

		menu.add(fileItem);
		menu.add(exitItem);

		menuBar.add(menu);

		setJMenuBar(menuBar);

		// ----------------- fin -----------------------------------------------

	}

	protected void cargarArchivos() {
		directory.searchFiles(file.getAbsolutePath());
		DefaultListModel<File> modelo = new DefaultListModel<>();
		for (File file : directory.getFiles()) {
			modelo.addElement(file);
		}
		fileList.setModel(modelo);

	}

	protected void obtenerArchivo() {
		file = chooser.getSelectedFile();
	}

	private void openShowDialog(ActionEvent arg0) {
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.showOpenDialog(this);
	}
}

class myPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JList<File> fileList;
	Directory directory;
	JList<Clase> classList;
	JList<Metodo> methodList;
	Archivo archivo;
	Clase clase;
	Metodo metodo;
	JLabel tlResult;
	JLabel clResult;
	JLabel plResult;
	JLabel ccResult;
	JLabel fiResult;
	JLabel foResult;
	JLabel lResult;
	JLabel Lenght;
	JLabel volume;
	JLabel hResult;
	JTextArea l1Result;
	JTextArea l2Result;
	private JTextArea classCodeTxtArea;

	public myPanel(JList<File> fileList, Directory directory) {
		this.fileList = fileList;
		this.directory = directory;

		setLayout(null);

		JScrollPane fileScrollPane = new JScrollPane();
		fileScrollPane.setBounds((int) (800 * (0.02)), 55,
				(int) (800 * (0.66)),
				(int) (600 * (0.15)));

		JLabel lblArchivos = new JLabel("Archivos");
		lblArchivos.setFont(new Font("Calibri", Font.BOLD, 15));
		lblArchivos.setBounds((int) (800 * (0.02)),
				(int) (600 * (0.02)), 100, 30);

		JLabel lblClases = new JLabel("Clases");
		lblClases.setFont(new Font("Calibri", Font.BOLD, 15));
		lblClases.setBounds((int) (800 * (0.02)),
				55 + (int) (600 * (0.15)) + 10, 100, 50);

		JScrollPane classScrollPane = new JScrollPane();
		classScrollPane.setBounds((int) (800 * (0.02)),
				55 + (int) (600 * (0.15)) + 10 + 50,
				(int) (800 * (0.32)),
				(int) (600 * (0.25)));

		JScrollPane methodScrollPane = new JScrollPane();
		methodScrollPane.setBounds(
				(int) (800 * (0.30))
						+ (int) (800 * (0.06)),
				55 + (int) (600 * (0.15)) + 10 + 50,
				(int) (800 * (0.32)),
				(int) (600 * (0.25)));

		JLabel lblMetodos = new JLabel("Metodos");
		lblMetodos.setFont(new Font("Calibri", Font.BOLD, 15));
		lblMetodos.setBounds(
				(int) (800 * (0.30))
						+ (int) (800 * (0.06)),
				55 + (int) (600 * (0.15)) + 10, 100, 50);

		JLabel lblCodigoArchivo = new JLabel("Codigo Archivo");
		lblCodigoArchivo.setFont(new Font("Calibri", Font.BOLD, 15));

		JScrollPane fileCodeScrollPane = new JScrollPane();

		JLabel lblClassCode = new JLabel("Codigo Clase");
		lblClassCode.setFont(new Font("Calibri", Font.BOLD, 15));

		JScrollPane classCodeScrollPane = new JScrollPane();
		classCodeScrollPane.setBounds((int) (800 * (0.02)),
				55 + (int) (600 * (0.15)) + 10 + 50
						+ (int) (600 * (0.25)) + 10 + 40,
				(int) (800 * (1.210)),
				(int) (600 * (0.19)));
		
		

		JLabel lblMethodCode = new JLabel("Codigo Metodo");
		lblMethodCode.setFont(new Font("Calibri", Font.BOLD, 15));
		lblMethodCode
				.setBounds((int) (800 * (0.02)),
						55 + (int) (600 * (0.15)) + 10 + 50
								+ (int) (600 * (0.25)) + 10,
						100, 50);

		classCodeTxtArea = new JTextArea();
		classCodeScrollPane.setViewportView(classCodeTxtArea);

		JTextArea fileCodeTxtArea = new JTextArea();
		fileCodeScrollPane.setViewportView(fileCodeTxtArea);

		methodList = new JList<>();
		methodScrollPane.setViewportView(methodList);

		classList = new JList<>();
		classScrollPane.setViewportView(classList);

		fileScrollPane.setViewportView(fileList);

		JLabel title = new JLabel("Análisis del Método");
		title.setFont(new Font("Calibri", Font.BOLD, 15));
		title.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				5, (int) (800 * (0.28)), 10);

		JLabel totalLines = new JLabel("Lineas de Código Totales");
		totalLines.setFont(new Font("Calibri", Font.ITALIC, 15));
		totalLines.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				15, (int) (800 * (0.28)), 30);

		tlResult = new JLabel("Resultado 1");
		tlResult.setFont(new Font("Calibri", Font.BOLD, 15));
		tlResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 35, 100, 30);

		JLabel commentedLines = new JLabel("Lineas de Código Comentadas");
		commentedLines.setFont(new Font("Calibri", Font.ITALIC, 15));
		commentedLines.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				55, (int) (800 * (0.28)), 30);

		clResult = new JLabel("Resultado 2");
		clResult.setFont(new Font("Calibri", Font.BOLD, 15));
		clResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 75, 100, 30);

		JLabel porcentLines = new JLabel("% de Lineas de Código Comentadas");
		porcentLines.setFont(new Font("Calibri", Font.ITALIC, 15));
		porcentLines.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				95, (int) (800 * (0.28)), 30);

		plResult = new JLabel("Resultado 3");
		plResult.setFont(new Font("Calibri", Font.BOLD, 15));
		plResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 115, 100, 30);

		JLabel complex = new JLabel("Complejidad Ciclomática");
		complex.setFont(new Font("Calibri", Font.ITALIC, 15));
		complex.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				135, (int) (800 * (0.28)), 30);

		ccResult = new JLabel("Resultado 4");
		ccResult.setFont(new Font("Calibri", Font.BOLD, 15));
		ccResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 155, 100, 30);

		JLabel fanIn = new JLabel("Fan In");
		fanIn.setFont(new Font("Calibri", Font.ITALIC, 15));
		fanIn.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				175, (int) (800 * (0.28)), 30);

		fiResult = new JLabel("Resultado 5");
		fiResult.setFont(new Font("Calibri", Font.BOLD, 15));
		fiResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 195, 100, 30);

		JLabel fanOut = new JLabel("Fan Out");
		fanOut.setFont(new Font("Calibri", Font.ITALIC, 15));
		fanOut.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				215, (int) (800 * (0.28)), 30);

		foResult = new JLabel("Resultado 6");
		foResult.setFont(new Font("Calibri", Font.BOLD, 15));
		foResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 235, 100, 30);

		Lenght = new JLabel("Hastead Longitud");
		Lenght.setFont(new Font("Calibri", Font.ITALIC, 15));
		Lenght.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				255, (int) (800 * (0.28)), 30);

		lResult = new JLabel("Resultado 7");
		lResult.setFont(new Font("Calibri", Font.BOLD, 15));
		lResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 275, 100, 30);

		volume = new JLabel("Hastead Volumen");
		volume.setFont(new Font("Calibri", Font.ITALIC, 15));
		volume.setBounds(
				(int) (800 * (0.66))
						+ (int) (800 * (0.05)),
				295, (int) (800 * (0.28)), 30);

		hResult = new JLabel("Resultado 8");
		hResult.setFont(new Font("Calibri", Font.BOLD, 15));
		hResult.setBounds((int) (800 * (0.66))
				+ (int) (800 * (0.05))
				+ (int) (800 * (0.02)), 315, 100, 30);

		JLabel label1 = new JLabel("Operadores");
		label1.setFont(new Font("Calibri", Font.ITALIC, 15));
		label1.setBounds((int) ((int) (1024 * (0.50))
				+ (int) (1024 * (0.05))
				+ (1024 * (0.02))
				+ (int) (1024 * (0.02))
				+ (int) (1024 * (0.20))), 15, 80, 30);

		l1Result = new JTextArea("Resultado 9");
		l1Result.setFont(new Font("Calibri", Font.BOLD, 15));
		l1Result.setBounds((int) (1024 * (0.50))
				+ (int) (1024 * (0.05))
				+ (int) (1024 * (0.02))
				+ (int) (1024 * (0.02))
				+ (int) (1024 * (0.20)), 40, 80, (int)(600 * 0.55));

		JLabel label2 = new JLabel("Operandos");
		label2.setFont(new Font("Calibri", Font.ITALIC, 15));
		label2.setBounds((int) (1024 * (0.50))
				+ (int) (1024 * (0.05))
				+ (int) (1024 * (0.04))
				+ (int) (1024 * (0.26))
				+ (int) (1024 * (0.03)), 15, 80, 30);

		l2Result = new JTextArea("Resultado 10");
		l2Result.setFont(new Font("Calibri", Font.BOLD, 15));
		l2Result.setBounds((int) (1024 * (0.50))
				+ (int) (1024 * (0.05))
				+ (int) (1024 * (0.04))
				+ (int) (1024 * (0.26))
				+ (int) (1024 * (0.03)), 40, 80, (int)(600 * 0.55));
		
		l2Result.setOpaque(false);
		l1Result.setOpaque(false);

		JScrollPane operandosScrollPane = new JScrollPane(l2Result, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// operandosScrollPane.setViewportView(l2Result);

		add(lblArchivos);
		add(fileScrollPane);
		add(lblClases);
		add(classScrollPane);
		add(lblMetodos);
		add(methodScrollPane);
		add(lblMethodCode);
		add(classCodeScrollPane);
		add(title);
		add(totalLines);
		add(commentedLines);
		add(porcentLines);
		add(complex);
		add(fanIn);
		add(fanOut);
		add(Lenght);
		add(volume);
		add(label1);
		add(label2);
		add(tlResult);
		add(clResult);
		add(plResult);
		add(ccResult);
		add(fiResult);
		add(foResult);
		add(lResult);
		add(hResult);
		add(l1Result);
		add(l2Result);

		fileList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					obtenerClases();
				} catch (FileNotFoundException e) {

					e.printStackTrace();
				}

			}

		});

		classList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {

					if (clase == null || !clase.equals(classList.getSelectedIndex())) {
						obtenerMetodos();
					}

				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}

		});

		methodList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				cargarCampos();

			}

		});

	}

	protected void cargarCampos() {
		metodo = methodList.getSelectedValue();
		if (metodo == null) {
			return;
		}
		metodo.calcularLineas();
		metodo.calcularLineasComentadas();
		metodo.calcularComplejidadCiclomatica();
		metodo.calcularPorcentajeComentado();

		tlResult.setText(String.valueOf(metodo.getCantidadLineas()));
		clResult.setText(String.valueOf(metodo.getCantidadLineasComentadas()));
		plResult.setText(String.valueOf(metodo.getPorcentajeComentado()));
		ccResult.setText(String.valueOf(metodo.getComplejidadCiclomatica()));
		fiResult.setText(String.valueOf(metodo.getFanIn()));
		foResult.setText(String.valueOf(metodo.getFanOut()));
		lResult.setText(String.valueOf(metodo.calcularLongitudHalstead()));
		hResult.setText(String.valueOf(metodo.calcularVolumenHalstead()));
		llenarLineasDeCodigo();
		l2Result.setText("");
		l1Result.setText("");
		llenarOperadores();
		llenarOperandos();

	}

	private void llenarOperandos() {

		for (String string : metodo.getOperandos()) {
			l2Result.setText(l2Result.getText() + "\n" + string);
		}

	}

	private void llenarOperadores() {
		for (String string : metodo.getOperadores()) {
			l1Result.setText(l1Result.getText() + "\n" + string);
		}

	}

	private void llenarLineasDeCodigo() {
		classCodeTxtArea.setText(metodo.getFirma() + "\n");

		for (String linea : metodo.getLineasCodigo()) {
			classCodeTxtArea.append(linea + "\n");

		}

	}

	protected void obtenerMetodos() throws ClassNotFoundException {
		/*
		 * directory.selectClass(classList.getSelectedValue());
		 * directory.readClass();
		 * 
		 */
		clase = classList.getSelectedValue();
		if (clase == null) {
			return;
		}

		DefaultListModel<Metodo> modelo = new DefaultListModel<>();

		for (Metodo metodo : clase.getMetodos()) {
			modelo.addElement(metodo);
		}
		methodList.setModel(modelo);

	}

	protected void obtenerClases() throws FileNotFoundException {

		if(fileList.getSelectedValue()!=null){
		archivo = new Archivo(fileList.getSelectedValue());
		
			DefaultListModel<Clase> modelo = new DefaultListModel<>();

			for (Clase clase : archivo.getClases()) {
				modelo.addElement(clase);
			}
			classList.setModel(modelo);
		}
	}

}