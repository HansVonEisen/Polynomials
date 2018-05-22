import java.util.ArrayList;
import static Prog1Tools.IOTools.*;

public class Menu {

    private ArrayList<Polynomial> polynomials = new ArrayList<>();
    void run() {
        int eingabe;

        do {
            System.out.println("\n *****          Benutzer Menü:          ***** \n");
            System.out.println("1. Polynom erstellen: ");
            System.out.println("2. Alle Polynome ausgeben: ");
            System.out.println("3. Polynom verändern: ");
            System.out.println("4. Funktionswert an einer Stelle x berechnen: ");
            System.out.println("5. Summe zweier Polymone: ");
            System.out.println("6. Differenz zweier Polynome: ");
            System.out.println("7. Erste Ableitung: ");
            System.out.println("8. Multiplikation zweier Polynome: ");
            System.out.println("9. Programm verlassen!");
            eingabe = readInteger();

            switch (eingabe) {
                case 1:
                    addPoly();
                    break;
                case 2:
                    printPoly();
                    break;
                case 3:
                    changePoly();
                    break;
                case 4:
                    insertX();
                    break;
                case 5:
                    calculatePolySum();
                    break;
                case 6:
                    calculatePolyDiff();
                    break;
                case 7:
                    calcDerivativePoly();
                    break;
                case 8:
                    prodPoly();
                    break;
            }
        }
        while(eingabe != 9);
        System.out.println("\n *** Programm beendet ! *** ");
    }

    private void printPoly() {
        for (int i=0; i < polynomials.size(); i++) {
            System.out.println((i+1) + ". " + polynomials.get(i));
        }
    }

    private void addPoly() {
        Polynomial p = new Polynomial();
        System.out.println("Gib bitte den Grad ein: ");
        int degreeInput = readInteger();

        for (int exp = degreeInput; exp >= 0; exp--) {
            System.out.println("Bitte gib den Koeffizienten für x^" + exp + " ein: ");
            double coeffInput = readDouble();
            p.setCoefficient(exp, coeffInput);
        }

        // Fügt den erstellten Polynom der Polynomliste hinzu
        polynomials.add(p);
        System.out.println("Zur Ihrer Liste hinzugefügt.");

    }

    private void prodPoly() {
        Polynomial p1 = choosePoly("Bitte wählen Sie das erste Faktorpolynom aus: ");
        Polynomial p2 = choosePoly("Bitte wählen Sie das zweite Faktorpolynom aus: ");
        Polynomial product = p1.product(p2);
        System.out.println("Das Ergebnispolynom lautet:\n" + product);
        savePoly(product);
    }

    private void calcDerivativePoly() {
        Polynomial p = choosePoly("Bitte wählen Sie ein Polynom aus: ");
        Polynomial derirative = p.derivative();
        System.out.println("Die erste Ableitung lautet: \n" + p.derivative());
        savePoly(derirative);
    }

    private void savePoly(Polynomial p) {
        System.out.println("Möchten Sie das Ergebnispolynom speichern? (j/n)");
        String input;
        while (!((input = readLine().trim()).equals("j") || input.equals("n"))) {
            System.out.println("Bitte geben sie j oder n ein!");
        }
        if (input.equals("j")) {
            polynomials.add(p);
            System.out.println("Das Ergebnispolynom wurde erfolgreich in Ihre Liste hinzugefügt.");
        }
    }

    private void calculatePolyDiff() {
        Polynomial p1 = choosePoly("Bitte wählen Sie den Minuendpolynom aus: ");
        Polynomial p2 = choosePoly("Bitte wählen Sie den Subtrahendpolynom aus: ");
        Polynomial diff = p1.diff(p2);
        System.out.println("Das Ergebnispolynom lautet:\n" + diff);
        savePoly(diff);
    }

    private void calculatePolySum() {
        Polynomial p1 = choosePoly("Bitte wählen Sie den ersten Summanden aus: ");
        Polynomial p2 = choosePoly("Bitte wählen Sie den zweiten Summanden aus: ");
        Polynomial sum = p1.sum(p2);
        System.out.println("Das Ergebnispolynom lautet:\n" + sum);
        savePoly(sum);
    }

    private void insertX() {
        Polynomial p = choosePoly("Bitte wählen Sie ein Polynom aus: ");
        System.out.println("Bitte geben Sie einen x-Wert ein: ");
        double x;
        while (true) {
            String input = readLine().trim();
            try {
                x = Double.parseDouble(input);
                break;
            } catch (NumberFormatException ignored) { }
            System.out.println("Das war keine valide Eingabe für einen x-Wert. Bitte versuchen Sie es erneut.");
        }
        System.out.println("Funktionswert an der Stelle " + x + " lautet: " + p.getY(x));
    }

    private Polynomial choosePoly(String message) {
        String errorMessage = "";
        int index;
        do {
            System.out.print(errorMessage);
            System.out.println(message);
            printPoly();
            String input = readLine().trim();
            try {
                index = Integer.parseInt(input) - 1;
            } catch (NumberFormatException e) {
                index = - 1;
            }
            errorMessage = input + " ist kein valider Index! \n";
        } while (index < 0 || index >= polynomials.size());
        return polynomials.get(index);
    }

    private void changePoly() {
        Polynomial p = choosePoly("Welches Polynom willst du verändern: ");
        int degree = p.getDegree();
        System.out.println("Welchen Grad soll das Polynom haben? Default: " + degree);
        String input = readLine().trim();
        try {
            int degreeNeu = Integer.parseInt(input);
            if (degreeNeu < degree){
                for (int exp = degreeNeu +1; exp <= degree ; exp++) {
                    p.setCoefficient(exp, 0);
                }
            }
            degree = degreeNeu;
        } catch (NumberFormatException ignored) {}

        for (int exp = degree; exp >= 0 ; exp--) {
            // ich hole mir das Polynom mit dem ausgwähltem Index und davon den Koeffizienten an der Stelle ´exp´
            double coefficient = p.getCoefficient(exp);
            System.out.println("Welcher Koeffizient soll für x^"+exp+" eingetragen werden? Default: " + coefficient);
            input = readLine().trim();
            try {
                coefficient = Double.parseDouble(input);
                p.setCoefficient(exp, coefficient);
            } catch (NumberFormatException ignored) {}
        }

        //polynomials.get(index).setCoefficient();
        System.out.println("");
    }




}
