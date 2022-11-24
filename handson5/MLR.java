//Regresion Lineal Multiple (MLR)

package handson.handson5;

public class MLR{
    private MLRGui myGui;
    private Datos datos;
    private OperacionesMatrices operaciones;
    private double b0;
    private double b1;
    private double b2;
    private double y;

    public MLR(){
        datos = new Datos();
        operaciones = new OperacionesMatrices();
    }
    
    public void getX(){ 
        myGui = new MLRGui(this);
        myGui.showGui();
    }

    public void tecnica_vectorized(double input_x1, double input_x2){
        int cont = 0, cantidad_x = 2, cantidad_y = 1;

        double matrizX[][] = getMatrizX(cantidad_x);
        double matrizY[][] = getMatrizY(cantidad_y);
        
        //CALCULAR MATRIZX TRASPUESTA
        int ordenMatriz = matrizX.length;
        double [][] matrizXTraspuesta = operaciones.traspuesta(matrizX, ordenMatriz);

        //MULTIPLICAR TRASPUESTA DE X POR X
        double [][] multiplicacion_XX = operaciones.multiplicacionMatrices(matrizXTraspuesta, matrizX);

        //MULTIPLICAR TRASPUESTA DE X POR Y
        double [][] multiplicacion_XY = operaciones.multiplicacionMatrices(matrizXTraspuesta, matrizY);
        
        //INVERSA DE MULTIPLICACION DE TRASPUESTA DE X POR X
        double [][] inversa_TrasXX = operaciones.matrizInversa(multiplicacion_XX);

        //MULTIPLICACION DE INVERSA POR MULTIPLICACION TRASPUESTA DE X POR Y
        double [][] multiplicacion_InvXY = operaciones.multiplicacionMatrices(inversa_TrasXX, multiplicacion_XY);

        b0 = multiplicacion_InvXY[0][0];
        b1 = multiplicacion_InvXY[1][0];
        b2 = multiplicacion_InvXY[2][0];

        double y = multiplicacion_InvXY[0][0] + (multiplicacion_InvXY[1][0] * input_x1) + (multiplicacion_InvXY[2][0] * input_x2);
        
        predecir_y(input_x1, input_x2);
        mostrar_resultado(input_x1, input_x2,y);

        myGui.dispose();
    }

    private void predecir_y(double input_x1, double input_x2){
        y = b0 + (b1 * input_x1) + (b2 * input_x2);
    }

    private double[][] getMatrizX(int cantidad_x){
        double datos_x1[] = datos.getX1();
        double datos_x2[] = datos.getX2();
        double matrizX[][] = new double[datos_x1.length][cantidad_x + 1];


        for(int i = 0; i < matrizX.length; i++){
            matrizX[i][0] = 1;
            matrizX[i][1] = datos_x1[i];  
            matrizX[i][2] = datos_x2[i];
        }

        return matrizX;
    }

    private double[][] getMatrizY(int cantidad_y){
        double datos_y[] = datos.getY();
        double matrizY[][] = new double[datos_y.length][cantidad_y];

        for(int i = 0; i < matrizY.length; i++){
            matrizY[i][0] = datos_y[i];  
        }

        return matrizY;
    }

    private void mostrar_resultado(double input_x1, double input_x2, double Y){
        System.out.println("---------------------------------------------------");
        System.out.println("VALOR DE X1 = " + input_x1);
        System.out.println("VALOR DE X2 = " + input_x2);
        System.out.println();
        System.out.println("B0 = " + b0);
        System.out.println("B1 = " + b1);
        System.out.println("B2 = " + b2);
        System.out.println();
        System.out.println("Y = B0 + B1x1 + B2x2");
        System.out.print("Y = " + b0);
        System.out.print(" + (" + b1 + " * " + input_x1 + ")");
        System.out.println(" + (" + b2 + " * " + input_x2 + ")");
        System.out.println("Y = " +  Y);
        System.out.println();
    }
 
}