package fecha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Josué Sánchez Merino
 */
public class Fecha {
    InputStreamReader flujo = new InputStreamReader(System.in);
    BufferedReader teclado = new BufferedReader(flujo);
    
    private int dia;
    private int mes;
    private int year;
    
    static private int [] diasMesYear={31,28,31,30,31,30,31,31,30,31,30,31};
    
   
    public int getDia() {
        return dia;
    }

    
    public void setDia(int dia) {
        this.dia = dia;
    }

   
    public int getMes() {
        return mes;
    }

    
    public void setMes(int mes) {
        this.mes = mes;
    }

    
    public int getYear() {
        return year;
    }

    
    public void setAnno(int anno) {
        this.year = year;
    }

   
    public int bisiesto() {
        int dias = 28;
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            dias = 29;
        }
        return dias;
    }

    public int bisiesto(int y) {
        int dias = 28;
        if ((y % 4 == 0 && y % 100 != 0) || (y % 400 == 0)) {
            dias = 29;
        }
        return dias;
    }

    
    public boolean comprobarFecha(int d, int m, int y) {
        boolean resultado = true;
        int[] diasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (y < 0) {
            resultado = false;
        } else {
            diasMes[1] = bisiesto(y);
            if (m < 1 || m > 12) {
                resultado = false;
            } else {
                if (d < 1 || d > diasMes[m - 1]) {
                    resultado = false;
                }
            }
        }
        return resultado;
    }
 public int  calcularNumeroOrden(){
          int orden=0;
          int [] diasMes={31,28,31,30,31,30,31,30,31,30,31,30,31};
          diasMes[1]=bisiesto();
          for (int m=0; m< mes-1;m++)
              orden=orden+diasMes[m];
          orden=orden+dia;
          return orden;
      }     
 
 public void calcularNuevaFechaDiasVencimiento(Fecha fecha) throws IOException {
        int[] diasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        diasMes[1] = bisiesto();

        int diasVencimiento;
        System.out.println("Dias de vencimiento: ");
        diasVencimiento = Integer.parseInt(teclado.readLine());

        int newDia;//nuevo dia
        int newMes = mes;//nuevo mes
        int newYear = year;//nuevo año

        newDia = dia + diasVencimiento;
        while (newDia > diasMes[newMes - 1]) {
            newDia = newDia - diasMes[newMes - 1];
            newMes =newMes + 1;
            if (newMes > diasMes.length) {
                newMes = newMes - diasMes.length;
                newYear = year + 1;
            } 
        }
    
        System.out.println("Fecha nueva: " + newDia + "\t" + newMes + "\t" + newYear);
    }

 
  public int diasPasanYear()
    {
        int diasHastaFecha=0;
        int mes;
        diasMesYear[1]=bisiesto(year);
        for (mes=1;mes<this.mes;mes++)
        {
            diasHastaFecha=diasHastaFecha+diasMesYear[mes-1];
        }
        diasHastaFecha=diasHastaFecha+dia;
        return diasHastaFecha;
    }
    
   
    public int diasQuedanYear()
    {
        int diasDesdeFecha=0;
        int mes;
        diasMesYear[1]=bisiesto(year);
        diasDesdeFecha=diasMesYear[this.mes-1]-dia;
        for (mes=this.mes+1;mes<=12;mes++)
        {
            diasDesdeFecha=diasDesdeFecha+diasMesYear[mes-1];
        }
        return diasDesdeFecha;   
    }
 
 public int compararFechas(Fecha fecha){
     int resultado;
     
     if(year < fecha.getYear())
         resultado=1;
     else
         if(this.year >fecha.getYear())
             resultado=2;
     else
             if(mes< fecha.getMes())
                 resultado=1;
     else
                 if(mes> fecha.getMes())
                     resultado=2;
     else
                     if(dia< fecha.getDia())
                         resultado=1;
     else
                         if(dia> fecha.getDia())
                             resultado=2;
     else
                             resultado=0;
     return resultado;
 }
 
   public int distanciaFechas(Fecha fecha2 )
 {
     
   int dias=0,m;
   int annoDos=year+1;
   int febrero=0; 
   
   if(year==fecha2.getYear())
    {
       if(mes==fecha2.getMes())
       {
           dias=fecha2.getDia()-dia;
       }else{
       dias=diasMesYear[mes-1]-dia;
       
       for(m=mes;m<fecha2.getMes()-1;m++)
        {
         dias=dias + diasMesYear[m];
        }
         dias=dias+fecha2.getDia();
        }
       
   }else{
       
        dias=diasQuedanYear();
        dias=dias+fecha2.diasPasanYear();
           
        while(annoDos < fecha2.getYear()){
            
           febrero=bisiesto(annoDos);
            
           if(febrero==29){
               
               dias=dias+366;
               
           } else{
               
               dias=dias+365;        
           }
           
           annoDos++;
        }
        
   }
    return dias;   
 }
   /**
     * Método que calcular la fecha a partir del orden (número de días transcurridos desde
     * principio de año) y un año.
     *
     * @throws IOException
     */
   public void calcularFecha() throws IOException {
        int diasTranscurridos;
                
        System.out.println("Dias transcurridos ");
        diasTranscurridos = Integer.parseInt(teclado.readLine());
        int year;
        System.out.println("Año ");
        year = Integer.parseInt(teclado.readLine());

        int[] diasMes = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        diasMes[1] = bisiesto();
        int newDia = diasTranscurridos;

        int mes = 0;
        while (newDia > diasMes[mes]) {
            newDia = diasTranscurridos - diasMes[mes];
            mes++;
        }

        if (mes + 1 > diasMes.length) {
            mes = mes - diasMes.length;
            year = year + 1;
        }

        System.out.println("Fecha " + newDia + "\t" + (mes+1) + "\t" + year);
    }
   

  
  public String formatoFecha(){
      
     String []meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
         "Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
     
     return meses[mes-1];
      
      
      }
  public void formatoFechaEntero(){
      String []meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio",
         "Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
  
      System.out.println("Fecha: " + dia + "\t" + meses[mes] + "\t" + year);
  }
  
  public void fechaActual(){
      Calendar c= new GregorianCalendar();
      dia=c.get(Calendar.DATE);
      mes=c.get(Calendar.MONTH);
      dia=c.get(Calendar.YEAR);
  }
  
}
