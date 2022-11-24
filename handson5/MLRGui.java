package handson.handson5;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MLRGui extends JFrame {	
    private MLR myAgent;
	private JTextField valueX1Field, valueX2Field;
	
	MLRGui(MLR a) {
		
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

					myAgent.tecnica_vectorized(Double.parseDouble(valueX1), Double.parseDouble(valueX2));
					valueX1Field.setText("");
					valueX2Field.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(MLRGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );

		p = new JPanel();
		p.add(addButton);
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		/*addWindowListener(new	WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//myAgent.doDelete();
			}
		} );*/
		
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