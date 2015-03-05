/**
 * Klasse for tomme ruter, dvs. de ruter som er markert ved '.' ved innlesning.
 * Disse rutene gis verdien '0' ved innlesning.  
 ******************************************************************************/
class TomRute extends Rute {
    
    TomRute(int dim, char verdi) {
	super(dim, verdi);
    }

    /**
     * Rekursiv metode for å fylle ut resten av brettet. Sjekker først om ruten er 
     * den siste i sin rad, og sjekker så om den også er den siste i sin kolonne
     * (dvs. om den er den siste ruten i brettet). Så går den i gjennom den 
     * aktuelle char-arrayen og prøver å sette inn de ulike verdiene.
     * Dersom dette er sisteRute legges løsningen inn i Sudokubeholderen. 
     **************************************************************************/
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
	
	for(int i = 0; i < aktueltCharsett.length; i++)  {
	    char c = aktueltCharsett[i];
	    if(erOK(c)) {
		this.verdi = c;
		if(sisteRute) {
		    sb.settInn(b);
		    sisteRute = false;
		} else {
		    b.alleRuter[radInt][kolInt].fyllUtRestenAvBrettet();
		}
	    }
	}
	this.verdi = '0';	
    }

    /**
     * Hjelpemetode for å sjekke om en char-verdi finnes i rutens rad, 
     * kolonne og boks fra før av. 
     * @return true dersom verdien ikke finnes i verken rad, kol eller boks
     **********************************************************************/
    public boolean erOK(char c) {
	if(rad.finnesIkke(c) && kol.finnesIkke(c) && boks.finnesIkke(c)) {
	    return true;
	} else {
	    return false;
	}
    }
}
