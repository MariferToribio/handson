//Regresion Logistica

package handson.handson7;

public class LR{
    private LRGui myGui;
    private Datos datos;
    private double tasa_aprendizaje;
    private int iteraciones;


    public LR(){
        datos = new Datos();
        tasa_aprendizaje = 0.1;
        iteraciones = 100;
    }

    public void getX(){ 
        myGui = new LRGui(this);
        myGui.showGui();
    }

    public void regresion_logistica(double input_x1, double input_x2){
        double arreglo_y[] = datos.getY();
        int filas_x = 3, datos_en_x = 3;
        int correcto[] = new int[filas_x];
        double iteraciones_totales = 0;
        double w0 = 0, w1 = 0, w2 = 0, costo = 0;
        double [][]x = obtener_valoresX(filas_x, datos_en_x);

        costo = calcular_costo(w0, w1, w2, arreglo_y, x, filas_x);

        for(int i = 0; i < iteraciones; i++){

            w0 = calcular_peso(w0, w1, w2, w0, 0, arreglo_y, x, datos_en_x);
            w1 = calcular_peso(w0, w1, w2, w1, 1, arreglo_y, x, datos_en_x);
            w2 = calcular_peso(w0, w1, w2, w2, 2, arreglo_y, x, datos_en_x);

        }

        //PREDECIR Y
        //We want to predict whether this is a championship team
        double y_pred = calcular_sigmoide(w0, w1, w2, input_x1, input_x2);

        mostrar_resultados(w0, w1, w2, input_x1, input_x2, y_pred);
        clasificacion(y_pred);

        System.out.println();
    }

    private double[][] obtener_valoresX(int filas_x, int datos_en_x){
        double [][] x = new double[filas_x][datos_en_x];
        for(int i = 0; i < datos_en_x; i++){
            x[0][i] = datos.getX1()[i]; 
            x[1][i] = datos.getX2()[i];
            x[2][i] = datos.getX3()[i];  
        }

        return x;
    }
    
    private double calcular_peso(double w0, double w1, double w2, double peso_calcular, int numero_peso, double []arreglo_y, double [][]x, int datos_en_x){
        double sumatoria = 0;
        double sigmoide = 0;

        for(int i = 0; i < datos_en_x; i++){
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

    //CALCULAR ERROR
    private double calcular_error(double w0, double w1, double w2, double y, double x1, double x2){
        double sigmoide = calcular_sigmoide(w0, w1, w2, x1, x2);
        double error = y - sigmoide;

        return error;
    }

    private void clasificacion(double y_pred){
        if(y_pred > 0.5){
            System.out.println("EQUIPO GANARA CAMPEONATO");
        } else {
            System.out.println("EQUIPO NO GANARA CAMPEONATO");
        }
    }

    private void mostrar_resultados(double w0, double w1, double w2, double input_x1, double input_x2, double y_pred){
        System.out.println("---------------------------------------------------");
        System.out.println("VALOR DE X1 = " + input_x1);
        System.out.println("VALOR DE X2 = " + input_x2);
        System.out.println();
        System.out.println("Y = " + y_pred);
        System.out.println();
    }
}
