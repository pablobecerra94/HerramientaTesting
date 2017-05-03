package herramientaTesting;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;

public class HerramientaTesting extends JFrame{
	public HerramientaTesting() {
		
		JScrollPane fileScrollPane = new JScrollPane();
		
		JLabel lblArchivos = new JLabel("Archivos");
		lblArchivos.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lblClases = new JLabel("Clases");
		lblClases.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JScrollPane classScrollPane = new JScrollPane();
		
		JScrollPane methodScrollPane = new JScrollPane();
		
		JLabel lblMetodos = new JLabel("Metodos");
		lblMetodos.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JLabel lblCodigoArchivo = new JLabel("Codigo Archivo");
		lblCodigoArchivo.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JScrollPane fileCodeScrollPane = new JScrollPane();
		
		JLabel lblClassCode = new JLabel("Codigo Clase");
		lblClassCode.setFont(new Font("Calibri", Font.BOLD, 15));
		
		JScrollPane classCodeScrollPane = new JScrollPane();
		
		JLabel lblMethodCode = new JLabel("Codigo Metodo");
		lblMethodCode.setFont(new Font("Calibri", Font.BOLD, 15));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblArchivos, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
								.addComponent(fileScrollPane, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
							.addGap(55)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblClases, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
								.addComponent(classScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(methodScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMetodos)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(fileCodeScrollPane, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCodigoArchivo))
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblClassCode, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
									.addGap(225)
									.addComponent(lblMethodCode, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
								.addComponent(classCodeScrollPane, GroupLayout.PREFERRED_SIZE, 309, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(308, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblArchivos)
						.addComponent(lblClases)
						.addComponent(lblMetodos))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(fileScrollPane, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(classScrollPane, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(methodScrollPane, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCodigoArchivo, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblClassCode, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblMethodCode, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(fileCodeScrollPane, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)
						.addComponent(classCodeScrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(90, Short.MAX_VALUE))
		);
		
		JTextArea classCodeTxtArea = new JTextArea();
		classCodeScrollPane.setViewportView(classCodeTxtArea);
		
		JTextArea fileCodeTxtArea = new JTextArea();
		fileCodeScrollPane.setViewportView(fileCodeTxtArea);
		
		JList methodList = new JList();
		methodScrollPane.setViewportView(methodList);
		
		JList classList = new JList();
		classScrollPane.setViewportView(classList);
		
		JList fileList = new JList();
		fileScrollPane.setViewportView(fileList);
		getContentPane().setLayout(groupLayout);
	}

	private static final long serialVersionUID = 1L;
}
