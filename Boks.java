/**
 * Klasse for bokser (subklasse av SudokuObjekt). Bokser har litt tilleggs-
 * struktur som kun er relevante for bokser, og jeg valgte derfor å ha disse
 * inne i boksklassen heller enn i SudokuObjekt-klassen. 
 ************************************************************************/
class Boks extends SudokuObjekt {
    int antallElementerIBoks = 0;

    Boks(int dim) {
	super(dim);
    }

    /**
     * Hjelpemetode for å hente ut antall elementer som ligger i boksen.
     * @return antall elementer i boksen. 
     *******************************************************************/
    public int getAntall() {
	return antallElementerIBoks;
    }

    /**
     * Metode for å oppdatere antall elementer i boksen når noe legges inn. 
     *********************************************************************/
    public void oppdaterAntall() {
	antallElementerIBoks++;
    }
}
