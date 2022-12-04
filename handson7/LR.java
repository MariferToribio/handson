//Regresion Logistica

package handson.handson7;

public class LR{
    private LRGui myGui;
    private Datos datos;
    private double tasa_aprendizaje;

    public LR(){
        datos = new Datos();
        tasa_aprendizaje = 0.1;
    }

    public void getX(){ 
        myGui = new LRGui(this);
        myGui.showGui();
    }

    public void regresion_logistica(double input_x1, double input_x2){
        double arreglo_y[] = datos.getY();
        int filas_x = 3, columnas_en_x = 3;
        int correcto[] = new int[filas_x];
        double iteraciones = 0;
        double w0 = 0, w1 = 0, w2 = 0, costo = 0;
        double [][]x = crear_matrizX(filas_x, columnas_en_x);
        boolean incorrecto_costo = true;

        costo = calcular_costo(w0, w1, w2, arreglo_y, x, filas_x);

        while(incorrecto_costo){

            for(int j = 0; j < columnas_en_x; j++){
                double costo_par = calcular_costo_xy(w0, w1, w2, arreglo_y[j], x[j][1], x[j][2]);
                if(costo_par > 0.0001) {
                    correcto[j] = 0;
                    break;
                } else {
                    correcto[j] = 1;
                }
            }

            w0 = calcular_peso(w0, w1, w2, w0, 0, arreglo_y, x, columnas_en_x);
            w1 = calcular_peso(w0, w1, w2, w1, 1, arreglo_y, x, columnas_en_x);
            w2 = calcular_peso(w0, w1, w2, w2, 2, arreglo_y, x, columnas_en_x);

            if(buscar_correcto(correcto) == 0){
                incorrecto_costo = false;
            } else {
                incorrecto_costo = true;
            }

            iteraciones++;
        }

        //PREDECIR Y
        //We want to predict whether this is a championship team
        double y_pred = calcular_sigmoide(w0, w1, w2, input_x1, input_x2);

        mostrar_resultados(w0, w1, w2, input_x1, input_x2, y_pred);
        clasificacion(y_pred);
        System.out.println();
    }

    private double[][] crear_matrizX(int filas_x, int columnas_en_x){
        double [][] x = new double[filas_x][columnas_en_x];

        for(int i = 0; i < filas_x; i++){
            x[i][0] = 1;
        }

        for(int i = 1; i < columnas_en_x; i++){
            x[0][i] = datos.getX1()[i-1]; 
            x[1][i] = datos.getX2()[i-1];
            x[2][i] = datos.getX3()[i-1];  
        }

        return x;
    }
    
    private double calcular_costo_xy(double w0, double w1, double w2, double y, double x1, double x2){
        double sigmoide = calcular_sigmoide(w0, w1, w2, x1, x2);
        double costo = 0;

        if(y == 1){
            costo = -Math.log(sigmoide);
        }else if(y == 0){
            costo = -Math.log(1 - sigmoide);
        }

        return costo;
    }

    private double calcular_peso(double w0, double w1, double w2, double peso_calcular, int numero_peso, double []arreglo_y, double [][]x, int columnas_en_x){
        double sumatoria = 0;
        double sigmoide = 0;

        for(int i = 0; i < columnas_en_x; i++){
            sigmoide = calcular_sigmoide(w0, w1, w2, x[i][1], x[i][2]);
            sumatoria += (sigmoide - arreglo_y[i]) * x[numero_peso][i];
        }

        return peso_calcular - tasa_aprendizaje * sumatoria;
    }

    // 1 / (1 + e^-(w0 + w1 * x1 + w2 * x2))
    private double calcular_sigmoide(double w0, double w1, double w2, double x1, double x2){
        double exponente = (w0 + (w1 * x1) + (w2 * x2)) * -1; 
        double euler = Math.pow(2.71828,exponente); 
        double fx = 1 / (1 + euler); 

        return fx;
    }

    //CALCULAR COSTO TOTAL
    private double calcular_costo(double w0, double w1, double w2, double []arreglo_y, double [][]x, int filas_x){
        double sigmoide = 0, sumatoria = 0;

        for(int i = 0; i < filas_x; i++){
            sigmoide = calcular_sigmoide(w0, w1, w2, x[i][1], x[i][2]);
            sumatoria += arreglo_y[i] * Math.log(sigmoide) + (1 - arreglo_y[i]) * Math.log(1 - sigmoide);
        }

        return -sumatoria / filas_x; 
    }

    private int buscar_correcto(int []correcto){
        for(int i = 0; i < correcto.length; i++){
            if(correcto[i] == 0){
                return -1;
            }
        }

        return 0;
    }

    private void clasificacion(double y_pred){
        if(y_pred >= 0.5){
            System.out.println("EQUIPO GANARA CAMPEONATO");
            System.out.println("Y = " + 1);
        } else {
            System.out.println("EQUIPO NO GANARA CAMPEONATO");
            System.out.println("Y = " + 0);
        }
    }

    private void mostrar_resultados(double w0, double w1, double w2, double input_x1, double input_x2, double y_pred){
        System.out.println("---------------------------------------------------");
        System.out.println("VALOR DE X1 = " + input_x1);
        System.out.println("VALOR DE X2 = " + input_x2);
        System.out.println();
        System.out.println("PROBABILIDAD = " + y_pred);
    }
}
