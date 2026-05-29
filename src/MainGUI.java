import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class MainGUI extends JFrame {

    private Haustier haustier;
    private int stunde = 0;

    private JLabel titleLabel;
    private JLabel timeLabel;
    private JLabel imageLabel;
    private JTextArea outputArea;

    private JButton essenButton;
    private JButton schlafenButton;
    private JButton spielenButton;
    private JButton statusButton;
    private JButton spezialButton;
    private JButton beendenButton;

    private static final String DATEI = "users.txt";

    //LoginFenster()
    private void loginFenster() {

        String[] optionen = {"Login", "Registrieren"};

        int auswahl = JOptionPane.showOptionDialog(
                this,
                "Willkommen im Tierheim System",
                "Login",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                optionen,
                optionen[0]
        );

        if (auswahl == 0) {

            String username = JOptionPane.showInputDialog(
                    this,
                    "Benutzername eingeben:"
            );

            String password = JOptionPane.showInputDialog(
                    this,
                    "Passwort eingeben:"
            );

            boolean erfolgreich = login(username, password);

            if (erfolgreich) {

                JOptionPane.showMessageDialog(
                        this,
                        "Login erfolgreich"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Falscher Benutzername oder Passwort"
                );

                System.exit(0);
            }

        } else {
            registrieren();
        }
    }

    private void registrieren() {

        String username = JOptionPane.showInputDialog(
                this,
                "Benutzername eingeben:"
        );

        String password = JOptionPane.showInputDialog(
                this,
                "Passwort eingeben:"
        );

        try {

            FileWriter writer = new FileWriter("users.txt", true);

            writer.write(username + ";" + password + "\n");

            writer.close();

            JOptionPane.showMessageDialog(
                    this,
                    "Registrierung erfolgreich"
            );

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void registrieren(String username, String password) {

        try {
            //Datei user.txt öffnen
            FileWriter writer = new FileWriter("users.txt", true);

            // username + ";" + password speichern
            writer.write(username + ";" + password + "\n");

            writer.close();


        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private boolean login(String username, String password) {

        File file = new File("users.txt");

        // WENN Datei users.txt nicht existiert
        if (!file.exists()) {
            return false;
        }

        boolean gefunden = false;

        try {

            // Datei öffnen
            Scanner reader = new Scanner(file);

            // SOLANGE Datei noch Zeilen hat
            while (reader.hasNextLine()) {

                // zeile lesen
                String zeile = reader.nextLine();

                // zeile bei ";" trennen
                String[] teile = zeile.split(";");

                // gespeicherterUser = erster Teil
                String gespeicherterUser = teile[0];

                // gespeichertesPasswort = zweiter Teil
                String gespeichertesPasswort = teile[1];

                // vergleichen
                if (username.equals(gespeicherterUser)
                        && password.equals(gespeichertesPasswort)) {

                    gefunden = true;
                }
            }

            // Datei schließen
            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }

        // RETURN gefunden
        return gefunden;
    }


    public MainGUI() {

        loginFenster(); // Login methode
        setTitle("Haustier-Simulator");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        haustierErstellen();
        guiAufbauen();

        setVisible(true);
    }

    private void haustierErstellen() {

        ImageIcon originalIcon = new ImageIcon(
                getClass().getResource("/images/pet_icon.png")
        );

        Image scaledImage = originalIcon.getImage()
                .getScaledInstance(80, 80, Image.SCALE_SMOOTH);

        ImageIcon icon = new ImageIcon(scaledImage);

        String name = (String) JOptionPane.showInputDialog(
                this,
                "Wie soll dein Haustier heißen?",
                "Haustier benennen",
                JOptionPane.PLAIN_MESSAGE,
                icon,
                null,
                ""
        );


        if (name == null || name.trim().isEmpty()) {
            name = "Fluffy";
        }

        String[] optionen = {"Katze", "Hund", "Papagei"};

        int auswahl = JOptionPane.showOptionDialog(
                this,
                "Welches Haustier möchtest du?",
                "Haustier auswählen",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionen,
                optionen[0]
        );

        switch (auswahl) {
            case 0:
                haustier = new Katze(name);
                break;
            case 1:
                haustier = new Hund(name);
                break;
            case 2:
                haustier = new Papagei(name);
                break;
            default:
                haustier = new Katze(name);
                break;
        }
    }

    private void guiAufbauen() {
        setLayout(new BorderLayout());

        JPanel headerPanel = new JPanel(new GridLayout(2, 1));
        headerPanel.setBackground(new Color(60, 100, 160));

        titleLabel = new JLabel("Haustier-Simulator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.WHITE);

        timeLabel = new JLabel("Stunde: 0 / 24", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        timeLabel.setForeground(Color.WHITE);

        headerPanel.add(titleLabel);
        headerPanel.add(timeLabel);
        add(headerPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(245, 245, 245));

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(280, 300));
        imageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        bildSetzen("default");

        centerPanel.add(imageLabel, BorderLayout.WEST);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Consolas", Font.PLAIN, 15));
        outputArea.setLineWrap(true);
        outputArea.setWrapStyleWord(true);
        outputArea.setBackground(Color.WHITE);
        outputArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(outputArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 12, 12));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        buttonPanel.setBackground(new Color(225, 235, 245));

        essenButton = buttonErstellen("Essen");
        schlafenButton = buttonErstellen("Schlafen");
        spielenButton = buttonErstellen("Spielen");
        statusButton = buttonErstellen("Status anzeigen");
        spezialButton = buttonErstellen("Spezialaktion");
        beendenButton = buttonErstellen("Beenden");

        buttonPanel.add(essenButton);
        buttonPanel.add(schlafenButton);
        buttonPanel.add(spielenButton);
        buttonPanel.add(statusButton);
        buttonPanel.add(spezialButton);
        buttonPanel.add(beendenButton);

        add(buttonPanel, BorderLayout.SOUTH);

        essenButton.addActionListener(e -> aktionAusfuehren("eat"));
        schlafenButton.addActionListener(e -> aktionAusfuehren("sleep"));
        spielenButton.addActionListener(e -> aktionAusfuehren("play"));
        statusButton.addActionListener(e -> statusAnzeigen());
        spezialButton.addActionListener(e -> aktionAusfuehren("special"));
        beendenButton.addActionListener(e -> spielBeenden());

        outputArea.append("Willkommen im Haustier-Simulator!\n\n");
        outputArea.append("Name: " + haustier.name + "\n");
        outputArea.append("Tierart: " + haustier.getTierart() + "\n\n");
        outputArea.append("Wähle unten eine Aktion aus.\n");
    }

    private JButton buttonErstellen(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setFocusPainted(false);
        button.setBackground(new Color(80, 130, 190));
        button.setForeground(Color.WHITE);
        return button;
    }

    private void aktionAusfuehren(String aktion) {
        if (stunde >= 24) {
            tagesEnde();
            return;
        }

        outputArea.append("\n----------------------------------------\n");

        switch (aktion) {
            case "eat":
                bildSetzen("eat");
                outputArea.append(haustier.name + " isst etwas.\n");
                haustier.essen();
                break;

            case "sleep":
                bildSetzen("sleep");
                outputArea.append(haustier.name + " schläft.\n");
                haustier.schlafen();
                break;

            case "play":
                bildSetzen("play");
                outputArea.append(haustier.name + " spielt.\n");
                haustier.spielen();
                break;

            case "special":
                bildSetzen("special");
                outputArea.append("Spezialaktion von " + haustier.name + ".\n");
                haustier.spezialAktion();
                break;
        }

        stunde++;
        timeLabel.setText("Stunde: " + stunde + " / 24");

        statusAnzeigen();

        if (stunde >= 24) {
            tagesEnde();
        }
    }

    private void bildSetzen(String aktion) {
        String tier = getTierNameFuerDatei();
        String dateiname;

        if (aktion.equals("default")) {
            dateiname = "default.png";
        } else {
            dateiname = tier + "_" + aktion + ".png";
        }

        try {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource("/images/" + dateiname)
                    //getclass = mainGUI.class -> suche die Datei relativ zur Klasse "MainGUI"
                    //.getResource = file:/C:/Projekt/src/images/cat_eat.png
            );

            Image image = icon.getImage();
            Image scaledImage = image.getScaledInstance(230, 230, Image.SCALE_SMOOTH);

            imageLabel.setIcon(new ImageIcon(scaledImage));

        } catch (Exception e) {
            imageLabel.setText("Bild nicht gefunden: " + dateiname);
        }
    }

    private String getTierNameFuerDatei() {
        if (haustier instanceof Katze) {   // haustier = new Katze("Mimi"); -> true
            return "cat";
        } else if (haustier instanceof Hund) {
            return "dog";
        } else if (haustier instanceof Papagei) {
            return "parrot";
        } else {
            return "default";
        }
    }

    private void statusAnzeigen() {
        outputArea.append("\nStatus:\n");
        outputArea.append("Name: " + haustier.name + "\n");
        outputArea.append("Tierart: " + haustier.getTierart() + "\n");
        outputArea.append("Hunger: " + haustier.hunger + "\n");
        outputArea.append("Energie: " + haustier.energie + "\n");
        outputArea.append("Laune: " + haustier.laune + "\n");
    }

    private void tagesEnde() {
        outputArea.append("\nDer Tag ist vorbei!\n");
        outputArea.append("Endstatus:\n");
        statusAnzeigen();

        essenButton.setEnabled(false);
        schlafenButton.setEnabled(false);
        spielenButton.setEnabled(false);
        spezialButton.setEnabled(false);
    }

    private void spielBeenden() {
        int antwort = JOptionPane.showConfirmDialog(
                this,
                "Möchtest du das Programm wirklich beenden?",
                "Programm beenden",
                JOptionPane.YES_NO_OPTION
        );

        if (antwort == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {

        new MainGUI();
    }

}
