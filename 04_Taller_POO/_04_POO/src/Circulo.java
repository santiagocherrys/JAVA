public class Circulo extends Figura{

    private double diametro;

    public Circulo(double diametro) {
        this.diametro = diametro;
    }

    @Override
    public double calcularArea() {
        return (Math.PI/4)*this.diametro;
    }
}
