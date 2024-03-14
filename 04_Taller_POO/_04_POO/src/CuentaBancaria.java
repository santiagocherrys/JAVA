import java.util.Scanner;

public class CuentaBancaria {
    private String titular;
    private double balance;

    //constructor
    public CuentaBancaria(String titular, double balance){
        this.titular = titular;
        this.balance = balance;
    }

    public String getTitular(){
        return this.titular;
    }

    public void setTitular(String titular){
        this.titular = titular;
    }

    public double getBalance(){
        return this.balance;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "titular='" + titular + '\'' +
                ", balance=" + balance +
                '}';
    }

    //depositar dinero
    public void depositarDinero(){
        Scanner leer = new Scanner(System.in);
        try{
            System.out.println("Ingrese la cantidad de dinero a depositar");
            double dinero = leer.nextDouble();
            this.balance += dinero;
        }catch(Exception e){
            System.out.println("Invalido");
        }
    }

    //Retirar dinero
    public void retirarDinero(){
        Scanner leer = new Scanner(System.in);
        System.out.println("Su balance es: " + this.balance);

        try{
            System.out.println("Ingrese la cantidad de dinero a retirar");
            double retirar = leer.nextDouble();

            if(retirar > this.balance){
                System.out.println("La cantidad a retirar supera el balance");
            }else{
                this.balance -= retirar;
                System.out.println("Su nuevo balance es " + this.balance);
            }
        }catch(Exception e){
            System.out.println("Invalido");
        }


    }
}
