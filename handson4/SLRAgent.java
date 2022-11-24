//Regresion Simple Lineal (SLR)

package handson.handson4;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SLRAgent extends Agent {

  protected void setup() {
    System.out.println("Agent "+getLocalName()+" started.");
    addBehaviour(new MyGenericBehaviour());
  } 

  private class MyGenericBehaviour extends Behaviour {
    int cont = 0;

    public void action() {
      System.out.println("Agent's action method executed");

      Regresion regresion = new Regresion();
      regresion.getX();

      cont+=1;
    } 
    
    public boolean done() {
      return cont == 1;
    }
   
    public int onEnd() {
      myAgent.doDelete();
      return super.onEnd();
    } 
  }    // END of inner class ...Behaviour
}


class Regresion {
    private RegresionSimpleGui myGui;

    public void getX(){ 
      myGui = new RegresionSimpleGui(this);
      myGui.showGui();
    }

    public void tecnicaRegresion(double input_x){
      double suma_x = 0, suma_y = 0, suma_xy = 0, suma_xcuadrado = 0, B1 = 0, B0 = 0, Y = 0;

      double arreglo_x[];
      //R&D SPEND -> VARIABLE INDEPENDIENTE
      //arreglo_x = new double []{165349.2,162597.7,153441.51,144372.41,142107.34,131876.9,134615.46,130298.13,120542.52,123334.88,101913.08,100671.96,93863.75,91992.39,119943.24,114523.61,78013.11,94657.16,91749.16,86419.7,76253.86,78389.47,73994.56,67532.53,77044.01,64664.71,75328.87,72107.6,66051.52,65605.48,61994.48,61136.38,63408.86,55493.95,46426.07,46014.02,28663.76,44069.95,20229.59,38558.51,28754.33,27892.92,23640.93,15505.73,22177.74,1000.23,1315.46,0,542.05,0};
      arreglo_x = new double []{1,2,3,4,5,6,7,8,9};

      double arreglo_y[];
      //PROFIT -> VARIABLE DEPENDIENTE
      //arreglo_y = new double []{192261.83,191792.06,191050.39,182901.99,166187.94,156991.12,156122.51,155752.6,152211.77,149759.96,146121.95,144259.4,141585.52,134307.35,132602.65,129917.04,126992.93,125370.37,124266.9,122776.86,118474.03,111313.02,110352.25,108733.99,108552.04,107404.34,105733.54,105008.31,103282.38,101004.64,99937.59,97483.56,97427.84,96778.92,96712.8,96479.51,90708.19,89949.14,81229.06,81005.76,78239.91,77798.83,71498.49,69758.98,65200.33,64926.08,49490.75,42559.73,35673.41,14681.4};
      arreglo_y = new double []{2,4,6,8,10,12,14,16,18};

      double [] arreglo_xy = multiplicarArreglos(arreglo_x, arreglo_y); //MULTIPLICAR VALORES DE X POR VALORES DE Y
      double [] arreglo_xcuadrado = multiplicarArreglos(arreglo_x, arreglo_x); //MULTIPLICAR VALORES DE X POR VALORES DE X

      suma_x = sumatoriaArreglo(arreglo_x);  //SUMATORIA DE X
      suma_y = sumatoriaArreglo(arreglo_y); //SUMATORIA DE Y
      suma_xy = sumatoriaArreglo(arreglo_xy); //SUMATORIA DE ARREGLO DE X * Y
      suma_xcuadrado = sumatoriaArreglo(arreglo_xcuadrado); //SUMATORIA DE ARREGLO X * X 

      //CALCULAR BETA 1
      B1 = ((arreglo_x.length * suma_xy) - ( suma_x * suma_y)) / ((arreglo_x.length * suma_xcuadrado) - (suma_x * suma_x));

      //CALCULAR BETA 0
      B0 = (suma_y - (B1 * suma_x)) / arreglo_x.length;

      //CALCULAR VALOR PREDECIDO DE Y
      Y = B0 + B1 * input_x;

      System.out.println("---------------------------------------------------");
      System.out.println("VALOR DE X = " + input_x);
      System.out.println();
      System.out.println("B0 = " + B0);
      System.out.println("B1 = " + B1);
      System.out.println();
      System.out.println("Y = B0 + B1x");
      System.out.println("Y = " + B0 + " + (" + B1 + " * " + input_x + ")");
      System.out.println("Y = " +  Y);
      System.out.println();

      myGui.dispose();
    }

    //METODO PARA MULTIPLICAR VALORES DE DOS ARREGLOS
    public static double[] multiplicarArreglos(double[] arreglo_1, double[] arreglo_2){ 
      double arreglo_3[] = new double[arreglo_1.length];

      for(int i=0; i < arreglo_1.length; i++){
        arreglo_3[i] = arreglo_1[i] * arreglo_2[i];
      }

      return arreglo_3;
    }

    //METODO PARA SUMAR VALORES DE UN ARREGLO
    public static double sumatoriaArreglo(double[] arreglo){ 
      double suma = 0;

      for(int i=0; i < arreglo.length; i++){
        suma += arreglo[i];
      }

      return suma;
    }
}

class RegresionSimpleGui extends JFrame {	
  private Regresion myAgent;
	private JTextField valueXField, priceField;
	
	RegresionSimpleGui(Regresion a) {
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

          myAgent.tecnicaRegresion(Double.parseDouble(valueX)); 
					valueXField.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(RegresionSimpleGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
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
