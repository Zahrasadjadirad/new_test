import java.util.Random;

public class Haustier {
    protected String name;
    protected int hunger;
    protected int energie;
    protected int laune;
    protected Random random;

    public Haustier(String name) {
        this.name = name;
        this.hunger = 50;
        this.energie = 50;
        this.laune = 50;
        this.random = new Random();
    }

    public void essen() {
        int ereignis = random.nextInt(4) + 1;

        switch (ereignis) {
            case 1:
                System.out.println(name + " frisst brav sein Futter.");
                hunger -= 20;
                laune += 10;
                energie += 5;
                break;
            case 2:
                System.out.println(name + " verschmäht das Futter und schaut dich enttäuscht an.");
                hunger += 5;
                laune -= 10;
                break;
            case 3:
                System.out.println(name + " klaut einen Snack vom Tisch!");
                hunger -= 15;
                laune += 15;
                break;
            case 4:
                System.out.println(name + " frisst zu schnell und bekommt Schluckauf.");
                hunger -= 10;
                laune -= 5;
                break;
        }

        werteBegrenzen();
    }

    public void schlafen() {
        int ereignis = random.nextInt(4) + 1;

        switch (ereignis) {
            case 1:
                System.out.println(name + " schläft tief und fest.");
                energie += 25;
                hunger += 10;
                laune += 10;
                break;
            case 2:
                System.out.println(name + " wacht viel zu früh auf.");
                energie += 5;
                hunger += 5;
                laune -= 10;
                break;
            case 3:
                System.out.println(name + " wird vom Staubsauger geweckt!");
                energie += 10;
                hunger += 5;
                laune -= 15;
                break;
            case 4:
                System.out.println(name + " träumt davon, ein Superheld zu sein.");
                energie += 20;
                hunger += 10;
                laune += 15;
                break;
        }

        werteBegrenzen();
    }

    public void spielen() {
        int ereignis = random.nextInt(4) + 1;

        switch (ereignis) {
            case 1:
                System.out.println(name + " spielt glücklich mit einem Ball.");
                laune += 15;
                energie -= 10;
                hunger += 10;
                break;
            case 2:
                System.out.println(name + " verliert plötzlich das Interesse und läuft weg.");
                laune -= 5;
                energie -= 5;
                hunger += 5;
                break;
            case 3:
                System.out.println(name + " verursacht komplettes Chaos im Wohnzimmer!");
                laune += 10;
                energie -= 15;
                hunger += 15;
                break;
            case 4:
                System.out.println(name + " spielt so lange, bis es müde, aber glücklich ist.");
                laune += 20;
                energie -= 20;
                hunger += 10;
                break;
        }

        werteBegrenzen();
    }

    public void statusAnzeigen() {
        System.out.println();
        System.out.println("Name: " + name);
        System.out.println("Tierart: " + getTierart());
        System.out.println("Hunger: " + hunger);
        System.out.println("Energie: " + energie);
        System.out.println("Laune: " + laune);
        System.out.println();
    }

    public void spezialAktion() {
        System.out.println(name + " macht etwas Besonderes.");
    }

    public String getTierart() {
        return "Haustier";
    }

    protected void werteBegrenzen() {
        if (hunger < 0) hunger = 0;
        if (hunger > 100) hunger = 100;

        if (energie < 0) energie = 0;
        if (energie > 100) energie = 100;

        if (laune < 0) laune = 0;
        if (laune > 100) laune = 100;
    }

}