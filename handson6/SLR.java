//Regresion Lineal Simple (SLR) via Gradiente Descendente (GD)

package handson.handson6;

public class SLR {
  private SLRGui myGui;
  private Datos datos;
  private double n_elementosD;
  private int num_elementos;

  public SLR(){
    datos = new Datos();
  }

  public void getX(){ 
    myGui = new SLRGui(this);
    myGui.showGui();
  }

  public void gradiente_descendente(double input_x){
    double arreglo_y[] = datos.getY();
    double arreglo_x1[] = datos.getX1();
    num_elementos = arreglo_x1.length;
    n_elementosD  = Double.valueOf(num_elementos);
    double tasa_aprendizaje = 0.001, b0 = 0, b1 = 0, y_pred = 0, costo = 0;
    int iteraciones = 400000, iteraciones_totales = 0;

    //CALCULAR COSTO INICIAL
    System.out.println("----------------------");
    System.out.println("INICIAL");
    costo = calcular_costo(arreglo_x1,arreglo_y,b0,b1); 
    mostrar_costo_betas(b0, b1, costo);

    for(int j = 0; j < iteraciones; j++){
      
      b0 = calcular_beta0(b0,b1,tasa_aprendizaje,arreglo_x1,arreglo_y);
      b1 = calcular_beta1(b0,b1,tasa_aprendizaje,arreglo_x1,arreglo_y);
      costo = calcular_costo(arreglo_x1,arreglo_y,b0,b1);

      if(j == iteraciones - 1){
        iteraciones_totales = j + 1;
      }

      if(costo == 0){
        j =  iteraciones;
        iteraciones_totales = j + 1;
      }
    }

    System.out.println("----------------------");
    System.out.println("FINAL DESPUES DE " + iteraciones_totales + " ITERACIONES Y TASA DE APRENDIZAJE " + tasa_aprendizaje);
    costo = calcular_costo(arreglo_x1,arreglo_y,b0,b1);
    mostrar_costo_betas(b0, b1, costo);
    mostrar_ypred(b0, b1, input_x);
    System.out.println();

  }

  private double calcular_ypred(double b0, double b1, double x1){
    return b0 + (b1 * x1);
  }

  private double calcular_costo(double []arreglo_x1, double []arreglo_y, double b0, double b1){
    double sumatoria_ypred_y = 0, y_pred = 0;

    for(int i = 0; i < num_elementos; i++){
      y_pred = calcular_ypred(b0,b1,arreglo_x1[i]); 
      sumatoria_ypred_y += ((arreglo_y[i] - y_pred) * (arreglo_y[i] - y_pred));
    }

    return sumatoria_ypred_y / (2.0 * n_elementosD);
  }

  private double calcular_beta0(double b0, double b1, double tasa_aprendizaje, double []arreglo_x1, double []arreglo_y){
    double Db0 = 0, y_pred = 0;

    for(int i = 0; i < num_elementos; i++){
      y_pred = calcular_ypred(b0,b1,arreglo_x1[i]); 
      Db0 += ((y_pred - arreglo_y[i]) * 1);
    }

    return b0 - (Db0 / n_elementosD) * tasa_aprendizaje;
  }
  
  private double calcular_beta1(double b0, double b1, double tasa_aprendizaje, double []arreglo_x1, double []arreglo_y){
    double Db1 = 0, y_pred = 0;

    for(int i = 0; i < num_elementos; i++){
      y_pred = calcular_ypred(b0,b1,arreglo_x1[i]); 
      Db1 += ((y_pred - arreglo_y[i]) * arreglo_x1[i]);
    }

    return b1 - (Db1 / n_elementosD) * tasa_aprendizaje;
  }

  private void mostrar_costo_betas(double b0, double b1, double costo){
    System.out.println("B0: " + b0);
    System.out.println("B1: " + b1);
    System.out.println("COSTO: " + costo);
  }

  private void mostrar_ypred(double b0, double b1, double input_x){
    System.out.println("------------------------");
    System.out.println("Y = B0 + B1x");
    System.out.println("Y = " + b0 + " + (" + b1 + " * " + input_x + ")");
    System.out.println("Y = " +  calcular_ypred(b0, b1, input_x));
  }

}


