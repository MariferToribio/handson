//Regresion Lineal Simple (SLR) via Gradiente Descendente (GD)

package handson.handson6;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SLRGui extends JFrame {	
    private SLR myAgent;
	private JTextField valueXField, priceField;
	
	SLRGui(SLR a) {
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Valor de X:"));
		valueXField = new JTextField(15);
		p.add(valueXField);

		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Agregar");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String valueX = valueXField.getText().trim();

          myAgent.gradiente_descendente(Double.parseDouble(valueX)); 
					valueXField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(SLRGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );

		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
				
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
