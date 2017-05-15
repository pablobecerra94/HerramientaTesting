package testingTool;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JToolBar;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.List;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuDragMouseListener;

public class HerramientaTesting extends JFrame {

	JFileChooser chooser;
	File file;
	JTextArea classCodeTxtArea;
	JList<File> fileList = new JList<>();
	Directory directory = new Directory();

	public HerramientaTesting() {

		this.add(new myPanel(fileList, directory));

		// automatiza la vista del frame a la pantalla del computador

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

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
		System.out.println(file.getAbsolutePath());

	}

	private static final long serialVersionUID = 1L;

	private void openShowDialog(ActionEvent arg0) {
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.showOpenDialog(this);
	}
}

class myPanel extends JPanel {
	JList<File> fileList;
	Directory directory;
	JList<ClaseDamian> classList;
	JList<Code> methodList;

	public myPanel(JList<File> fileList, Directory directory) {
		this.fileList = fileList;
		this.directory = directory;

		setLayout(null);

		JScrollPane fileScrollPane = new JScrollPane();
		fileScrollPane.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 55,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66)),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)));

		JLabel lblArchivos = new JLabel("Archivos");
		lblArchivos.setFont(new Font("Calibri", Font.BOLD, 15));
		lblArchivos.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.02)), 100, 30);

		JLabel lblClases = new JLabel("Clases");
		lblClases.setFont(new Font("Calibri", Font.BOLD, 15));
		lblClases.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)),
				55 + (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)) + 10, 100, 50);

		JScrollPane classScrollPane = new JScrollPane();
		classScrollPane.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)),
				55 + (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)) + 10 + 50,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.32)),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.25)));

		JScrollPane methodScrollPane = new JScrollPane();
		methodScrollPane.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.30))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.06)),
				55 + (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)) + 10 + 50,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.32)),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.25)));

		JLabel lblMetodos = new JLabel("Metodos");
		lblMetodos.setFont(new Font("Calibri", Font.BOLD, 15));
		lblMetodos.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.30))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.06)),
				55 + (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)) + 10, 100, 50);

		JLabel lblCodigoArchivo = new JLabel("Codigo Archivo");
		lblCodigoArchivo.setFont(new Font("Calibri", Font.BOLD, 15));

		JScrollPane fileCodeScrollPane = new JScrollPane();

		JLabel lblClassCode = new JLabel("Codigo Clase");
		lblClassCode.setFont(new Font("Calibri", Font.BOLD, 15));

		JScrollPane classCodeScrollPane = new JScrollPane();
		classCodeScrollPane.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)),
				55 + (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)) + 10 + 50
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.25)) + 10 + 50,
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.95)),
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.225)));

		JLabel lblMethodCode = new JLabel("Codigo Metodo");
		lblMethodCode.setFont(new Font("Calibri", Font.BOLD, 15));
		lblMethodCode
				.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)),
						55 + (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.15)) + 10 + 50
								+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * (0.25)) + 10,
						100, 50);

		JTextArea classCodeTxtArea = new JTextArea();
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
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				5, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 50);

		JLabel totalLines = new JLabel("Lineas de Código Totales");
		totalLines.setFont(new Font("Calibri", Font.ITALIC, 15));
		totalLines.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				40, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel tlResult = new JLabel("Resultado 1");
		tlResult.setFont(new Font("Calibri", Font.BOLD, 15));
		tlResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 60, 100, 30);

		JLabel commentedLines = new JLabel("Lineas de Código Comentadas");
		commentedLines.setFont(new Font("Calibri", Font.ITALIC, 15));
		commentedLines.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				80, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel clResult = new JLabel("Resultado 2");
		clResult.setFont(new Font("Calibri", Font.BOLD, 15));
		clResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 100, 100, 30);

		JLabel porcentLines = new JLabel("Porcentaje de Lineas de Código Comentadas");
		porcentLines.setFont(new Font("Calibri", Font.ITALIC, 15));
		porcentLines.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				120, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel plResult = new JLabel("Resultado 3");
		plResult.setFont(new Font("Calibri", Font.BOLD, 15));
		plResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 140, 100, 30);

		JLabel complex = new JLabel("Complejidad Ciclomática");
		complex.setFont(new Font("Calibri", Font.ITALIC, 15));
		complex.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				160, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel ccResult = new JLabel("Resultado 4");
		ccResult.setFont(new Font("Calibri", Font.BOLD, 15));
		ccResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 180, 100, 30);

		JLabel fanIn = new JLabel("Fan In");
		fanIn.setFont(new Font("Calibri", Font.ITALIC, 15));
		fanIn.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				200, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel fiResult = new JLabel("Resultado 5");
		fiResult.setFont(new Font("Calibri", Font.BOLD, 15));
		fiResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 220, 100, 30);

		JLabel fanOut = new JLabel("Fan Out");
		fanOut.setFont(new Font("Calibri", Font.ITALIC, 15));
		fanOut.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				240, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel foResult = new JLabel("Resultado 6");
		foResult.setFont(new Font("Calibri", Font.BOLD, 15));
		foResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 260, 100, 30);

		JLabel Lenght = new JLabel("Hastead Longitud");
		Lenght.setFont(new Font("Calibri", Font.ITALIC, 15));
		Lenght.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				280, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel lResult = new JLabel("Resultado 7");
		lResult.setFont(new Font("Calibri", Font.BOLD, 15));
		lResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 300, 100, 30);

		JLabel volume = new JLabel("Hastead Volumen");
		volume.setFont(new Font("Calibri", Font.ITALIC, 15));
		volume.setBounds(
				(int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
						+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)),
				320, (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.28)), 30);

		JLabel hResult = new JLabel("Resultado 8");
		hResult.setFont(new Font("Calibri", Font.BOLD, 15));
		hResult.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 340, 100, 30);

		JLabel label1 = new JLabel("Label 1");
		label1.setFont(new Font("Calibri", Font.ITALIC, 15));
		label1.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)), 360, 150, 30);

		JLabel l1Result = new JLabel("Resultado 9");
		l1Result.setFont(new Font("Calibri", Font.BOLD, 15));
		l1Result.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 380, 100, 30);

		JLabel label2 = new JLabel("Label 2");
		label2.setFont(new Font("Calibri", Font.ITALIC, 15));
		label2.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05)), 400, 150, 30);

		JLabel l2Result = new JLabel("Resultado 10");
		l2Result.setFont(new Font("Calibri", Font.BOLD, 15));
		l2Result.setBounds((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.66))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.05))
				+ (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * (0.02)), 420, 100, 30);

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
				obtenerClases(directory.listClasses(fileList.getSelectedValue()));
			}

		});

		classList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				try {
					obtenerMetodos();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});

	}

	protected void obtenerMetodos() throws ClassNotFoundException {
		directory.selectClass(classList.getSelectedValue());
		directory.readClass();

		DefaultListModel<Code> modelo = new DefaultListModel<>();

		for (Code clase : classList.getSelectedValue().getMethods()) {
			modelo.addElement(clase);
		}
		methodList.setModel(modelo);

	}

	protected void obtenerClases(List<ClaseDamian> list) {

		DefaultListModel<ClaseDamian> modelo = new DefaultListModel<>();

		for (ClaseDamian clase : list) {
			modelo.addElement(clase);
		}
		classList.setModel(modelo);

	}

}
