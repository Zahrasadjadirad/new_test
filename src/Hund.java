public class Hund extends Haustier {

    public Hund(String name) {
        super(name);
    }

    @Override
    public String getTierart() {
        return "Hund";
    }

    @Override
    public void spezialAktion() {
        System.out.println(name + " apportiert einen Stock und wedelt stolz mit dem Schwanz.");
        laune += 15;
        energie -= 10;
        hunger += 10;
        werteBegrenzen();
    }

    public void bellen() {
        System.out.println(name + " bellt fröhlich: Wuff!");
    }
}
