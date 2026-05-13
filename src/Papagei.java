public class Papagei extends Haustier {

    private String[] woerter = {
            "Hallo!",
            "Keks!",
            "Du bist lustig!",
            "Chaos!",
            "Ich bin der Boss!"
    };

    public Papagei(String name) {
        super(name);
    }

    @Override
    public String getTierart() {
        return "Papagei";
    }

    @Override
    public void spezialAktion() {
        int index = random.nextInt(woerter.length);
        System.out.println(name + " sagt: \"" + woerter[index] + "\"");
        laune += 10;
        energie -= 5;
        hunger += 5;
        werteBegrenzen();
    }
}