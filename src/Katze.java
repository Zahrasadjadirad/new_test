public class Katze extends Haustier {

    public Katze(String name) {
        super(name);
    }

    @Override
    public String getTierart() {
        return "Katze";
    }

    @Override
    public void spezialAktion() {
        System.out.println(name + " kratzt am Sofa und schaut dich unschuldig an.");
        laune += 10;
        energie -= 5;
        hunger += 5;
        werteBegrenzen();
    }

    public void miauen() {
        System.out.println(name + " miaut beleidigt: Miau!");
    }
}