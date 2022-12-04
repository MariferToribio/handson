//Regresion Logistica

package handson.handson7;

public class Datos{
    private double arreglo_x1[] = new double []{1,1}; 
    private double arreglo_x2[] = new double []{4,2};
    private double arreglo_x3[] = new double []{2,4};
    private double arreglo_y[] = new double []{1,0,0};

    public double[] getX1(){
        return arreglo_x1;
    }

    public double[] getX2(){
        return arreglo_x2;
    }

    public double[] getX3(){
        return arreglo_x3;
    }

    public double[] getY(){
        return arreglo_y;
    }

}