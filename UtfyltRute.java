/**
 * Klasse for ferdig utfylte ruter, dvs. de ruter som allerede er utfylte ved 
 * innlesningen av filen. Rutene tilordnes verdi ettersom hva verdien er ved
 * innlesning.
 ****************************************************************************/
class UtfyltRute extends Rute {
    UtfyltRute(int dim, char verdi) {
	super(dim, verdi);
    }

    /**
     * Rekursiv metode for å fylle ut resten av brettet. Metoden endrer ikke 
     * på verdien i de rutene som allerede er utfylte fra starten av, men 
     * kaller bare videre på neste rute (eller legge løsningen i beholderen
     * dersom dette er siste rute).
     ************************************************************************/
    public void fyllUtRestenAvBrettet() {
	int kolInt = kolIndex+1;
	int radInt = radIndex;
	boolean sisteRute = false;
	if(kolInt == dim) {
	    kolInt = 0;
	    radInt = radIndex+1;
	    
	    if(radInt == dim) {
		sisteRute = true;
	    }
	}
	
	if(sisteRute) {
	    sb.settInn(b);
	    sisteRute = false;
	} else {
	    b.alleRuter[radInt][kolInt].fyllUtRestenAvBrettet();
	}
    }
}
