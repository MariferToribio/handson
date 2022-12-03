//Regresion Logistica

package handson.handson7;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LRGui extends JFrame {	
  private LR myAgent;
	private JTextField valueX1Field, valueX2Field;
	
	LRGui(LR a) {
		
		myAgent = a;
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 2));
		p.add(new JLabel("Valor de X1:"));
		valueX1Field = new JTextField(15);
		p.add(valueX1Field);
        p.add(new JLabel("Valor de X2:"));
		valueX2Field = new JTextField(15);
		p.add(valueX2Field);
		getContentPane().add(p, BorderLayout.CENTER);

		getContentPane().add(p, BorderLayout.CENTER);
		
		JButton addButton = new JButton("Agregar");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String valueX1 = valueX1Field.getText().trim();
                    String valueX2 = valueX2Field.getText().trim();

					myAgent.regresion_logistica(Double.parseDouble(valueX1), Double.parseDouble(valueX2));
					valueX1Field.setText("");
					valueX2Field.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(LRGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
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