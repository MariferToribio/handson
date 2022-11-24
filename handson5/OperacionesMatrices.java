//Regresion Lineal Multiple (MLR)

package handson.handson5;

public class OperacionesMatrices{
        //CALCULAR TRASPUESTA
    public double[][] traspuesta(double [][]matriz, int ordenMatriz){
        double matrizTraspuesta[][]= new double[matriz[0].length][matriz.length];

        //CALCULAR TRASPUESTA DE MATRIZ
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[0].length; j++){
                matrizTraspuesta[j][i] = matriz[i][j];
            }
        }

        return matrizTraspuesta;
    }

    //CALCULAR DETERMINANTE DE MATRIZ 
    public double determinante(double [][]matriz){
        double determinante = 0;
 
        if (matriz.length == 1) {
            return matriz[0][0];
        } else {
            for (int columna = 0; columna < matriz.length; columna++) {
                determinante += matriz[0][columna] * cofactor(matriz, 0, columna);
            }
        }
 
        return determinante;        
    }

    public double cofactor(double matriz[][], int fila, int columna) {
        int n = matriz.length - 1, a = 0, b = 0;
        double submatriz[][] = new double[n][n];
        double signo = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (i != fila && j != columna) {
                    submatriz[a][b] = matriz[i][j];
                    b++;
                    if (b == n) {
                        b = 0;
                        a++;
                    }
                }
            }
        }

        signo = Math.pow(-1.0, fila + columna);
        
        return signo * determinante(submatriz);
    }

    //MULTIPLICACION DE MATRICES
    public double[][] multiplicacionMatrices(double [][]matriz1, double [][]matriz2){
        double matrizProducto[][]= new double[matriz1.length][matriz2[0].length];
        double suma = 0;
        
        for (int i = 0; i < matriz2[0].length; i++) {
            for (int j = 0; j < matriz1.length; j++) {
                suma = 0;
                for (int k = 0; k < matriz1[0].length; k++) {
                    suma += matriz1[j][k] * matriz2[k][i];
                }
                matrizProducto[j][i] = suma;
            }
        }

        return matrizProducto;
    }

    //CALCULAR MATRIZ INVERSA
    public double[][] matrizInversa(double [][]matriz){
        double matrizInversa[][]= new double[matriz.length][matriz.length];

        //CALCULAR DETERMINANTE
        double determinante = 0;
        determinante = determinante(matriz);

        //COMPROBAR QUE DETERMINANTE NO SEA IGUAL A CERO
        if(determinante == 0){
            System.out.println("MATRIZ NO TIENE INVERSA");
            return matrizInversa;
            
        } else { 
            //CALCULAR TRASPUESTA DE MATRIZ ORIGINAL
            int ordenMatriz = matriz.length;
            double [][] matrizTraspuesta = traspuesta(matriz, ordenMatriz);

            //CALCULAR MATRIZ ADJUNTA
            double matrizAdjunta[][]= new double[matriz.length][matriz.length];
            double signo = 0;

            for(int i = 0; i < ordenMatriz; i++){
                for(int j = 0; j < ordenMatriz; j++){
                    //COFACTOR - CALCULAR COFACTOR DE CADA POSICION DE MATRIZ 
                    matrizAdjunta[i][j] = cofactor(matrizTraspuesta, i, j);
                } 
            }

            //DIVIDIR MATRIZ ADJUNTA ENTRE DETERMINANTE 
            for(int i = 0; i < ordenMatriz; i++){
                for(int j = 0; j < ordenMatriz; j++){
                    matrizInversa[i][j] = matrizAdjunta[i][j] / determinante;
                }
            }

            return matrizInversa;
        }
    }

}