import java.util.ArrayList;

public class Polynomial {

    // erstellen einer Arrayliste mit dem Namen coefficients
    private ArrayList<Double> coefficients = new ArrayList<>(); // index = exponent

    // ein leerer Konstruktor - damit wir leere Polynome erstellen können ohne Parameter im Konstruktor
    // zB wenn wir Polynome addieren und ein neuer Polynom entsteht ...
    public Polynomial() {}

// ... dieser Konstruktor kopiert Polynom p in neue Instanz (Copy-constructor)
    public Polynomial(Polynomial p) {
        for (int exp = 0; exp <= p.getDegree(); exp++) {
            setCoefficient(exp, p.getCoefficient(exp));
        }
    }

    // hiermit kann man sich den Grad des Polynoms geben lassen (zB durch: p.getDegree() )
    public int getDegree() {
        // 0 ist auch drin, deswegen ist der Grad des Polynoms eins kleiner als die ArrayList
        return coefficients.size() - 1;
    }

    // hiermit kann man sich den Koeffizienten des jeweiligen Exponenten geben lassen
    public double getCoefficient(int exp) {
        // wenn valider Exponent (index in ArrayList vorhanden), gib ihn zurück, sonst ist er 0
        if (exp >= 0 && exp <=getDegree()) {
            return coefficients.get(exp);
        }
        return (double) 0;
    }
    // hiermit kann man einen Koeffizienten von dem jeweilgen Exponenten setzten lassen
    public void setCoefficient(int exp, double coefficient) {
        // falls negativer Exponent, fail.
        // Falls ArrayList noch nicht groß genug, erweitere es, bis groß genug.
        // Setze Koeffizient.
        // verkleinere ggf das Array
        if (exp < 0) {
            throw new IllegalArgumentException("Negative exponents are not allowed!");
        }
        while (exp > getDegree()) {
            coefficients.add((double)0);
        }
        coefficients.set(exp, coefficient);
        while (getDegree() >= 0 && coefficients.get(getDegree())==0){
            coefficients.remove(getDegree());
        }
    }

    public boolean equals(Polynomial other) {
        int degree = Math.max(getDegree(), other.getDegree());
        for (int exp = 0; exp <= degree; exp++) {
            if (getCoefficient(exp) != other.getCoefficient(exp)) {
                return false;
            }
        }
        return true;
    }
// TODO: für alle Koeffizienten die 0 sind soll die variable + exponent nicht angezeigt werden
    public String toString() { // wird aufgerufen in System.out.println(polynom)
        // Anfang: leerer String, füge dann immer den richtigen String vorne an.
        StringBuilder output = new StringBuilder();
        for (int exp = getDegree(); exp >= 0; exp--) {
            double c = coefficients.get(exp);
            // ?: Ist c >= 0, dann füge + ein, sonst füge - ein
            if (!(exp == getDegree() && c > 0)) {
                output.append((c >= 0) ? " + " : " - ");
            }
            output.append(String.valueOf(Math.abs(c)));
            if (!(exp==0)) {
                output.append("x");
            }
            if (!(exp<=1)) {
                output.append("^").append(exp);
            }
        }
        return output.toString();
    }

    public double getY(double x) {
        // rechne das Ergebnis (coefficient * x^exp) für jede Stelle aus (Schleife) und addiere alles.
        double result = 0;
        for (int exp = 0; exp <= getDegree(); exp++) {
            result += coefficients.get(exp) * Math.pow(x, exp);
        }
        return result;
    }

    public Polynomial sum(Polynomial other) {
        // Finde raus, wie viele Koeffizienten du brauchst (Maximum aus beiden Inputs). Addiere immer beide Coeffizienten und schmeiß das Ergebnis in das Ergebnispolynom.
        int degree = Math.max(this.getDegree(), other.getDegree());
        Polynomial result = new Polynomial();
        for (int exp = 0; exp <= degree; exp++) {
            result.setCoefficient(exp, getCoefficient(exp) + other.getCoefficient(exp));
        }
        return result;
    }

    public Polynomial diff(Polynomial other) { // analog zu sum, nur halt mit -
        int degree = Math.max(this.getDegree(), other.getDegree());
        Polynomial result = new Polynomial();
        for (int exp = 0; exp <= degree; exp++) {
            result.setCoefficient(exp, getCoefficient(exp) - other.getCoefficient(exp));
        }
        return result;
    }

    public Polynomial derivative() {
        // mach ein neues Polynomial was 1 kürzer ist, berechne die Koeffizenten
        // (Koeffizient von einer Stelle höher * Exponent von einer Stelle höher) und schmeiß es in das Ergebnispolynom
        Polynomial result = new Polynomial();
        for (int exp = 0; exp < getDegree(); exp++) {
            result.setCoefficient(exp, getCoefficient(exp+1)*(exp+1));
        }
        return result;
    }

    public Polynomial product(Polynomial other) {
        Polynomial product = new Polynomial();
        for (int exp1 = 0; exp1 <= this.getDegree(); exp1++) {
            for (int exp2 = 0; exp2 <= this.getDegree(); exp2++) {
                product.setCoefficient(exp1 + exp2, product.getCoefficient(exp1 + exp2) + this.getCoefficient(exp1) * other.getCoefficient(exp2));
            }
        }
        return product;
    }
}

