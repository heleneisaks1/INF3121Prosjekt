/**
 * Klasse som representerer et sudokubrett. Et brett holder orden på alle rutene,
 * radene, kolonnene og boksene som befinner seg i brettet. Rutene ordnes ved en
 * to-dimensjonalarray, resten ved en vanlig array. I tillegg har et brett en
 * dimensjon (dvs. et brett er dim * dim ruter stor) samt antall rader og kolonner
 * som skal finnes per boks. 
 ******************************************************************************/
class Brett {
    Rute alleRuter[][];
    Rad alleRader[];
    Kolonne alleKolonner[];
    Boks alleBokser[];
    int dim;
    int radPerBoks;
    int kolPerBoks;
    
    Brett(int dim, Rute[][] alleRuter, int radPerBoks, int kolPerBoks) {
	this.dim = dim;
	this.alleRuter = alleRuter;
	this.radPerBoks = radPerBoks;
	this.kolPerBoks = kolPerBoks;
	alleRader = new Rad[dim];
	alleKolonner = new Kolonne[dim];
	alleBokser = new Boks[dim];
	
	for(int i = 0; i < dim; i++) {
	    alleRader[i] = new Rad(dim);
	    alleKolonner[i] = new Kolonne(dim);
	    alleBokser[i] = new Boks(dim);
	}

	fyllInnBrett();
    }

    /**
     * Metode for å strukturere brettet, dvs. legge de rette rutene
     * inn i de rette kolonne, rad og boks-objektene. Koden for boks-
     * objektene ble litt uoversiktelig, så jeg kommenterer litt underveis. 
     *********************************************************************/
    public void fyllInnBrett() {
	int teller = 0;
	int boksteller = 0;
	int radteller = 1;
	int kolonneteller = 0;
	int forsteBoksIRad = boksteller;
	
	for(int i = 0; i < dim; i++) {
	    for(int j = 0; j < dim; j++) {
		if(teller == dim) { // Dersom telleren er like lang som antall ruter per rad
		    teller = 0;
		    if(radteller == radPerBoks) { // Dersom det er like mange rader nå som det skal være i en boks...
			boksteller++; // ...hopper man til neste boks og radtelleren settes lik én...
			radteller = 1;
			forsteBoksIRad = boksteller; // ...og denne  boksen settes lik forste boks i bokseraden
		    } else {
			boksteller = forsteBoksIRad; // Dersom dette ikke er den siste raden i boksen...
			radteller++; // ...oppdateres radtelleren...
			kolonneteller = 0; // ...og kolonnetelleren settes lik 0
		    }
		}
		
		if(kolonneteller == kolPerBoks && teller == 0) { // Dersom telleren står på 0 og alle radene i denne boksen er talt...
		    kolonneteller = 1; // ...settes kolonneteller lik en og boksen har allerede blitt skiftet oven
		} else if (kolonneteller == kolPerBoks) { // Hvis man er igjennom alle kolonnene...
		    kolonneteller = 1; // ...settes kolonnteller lik én og...
		    boksteller++; // ...boksetelleren oppdateres (dvs. man hopper til neste boks i bokseraden
		} else {
		    kolonneteller++; // Ellers plusses én på kolonnetelleren
		}
		
		teller++; // Telleren oppdateres. 
		// Ruten skal legges inn i alle de aktuelle SudokuObjektene
		Boks box = alleBokser[boksteller]; // Henter ut den riktige boksen
		int antallElementer = box.getAntall(); // Henter ut antall elementer i boksen for å unngå overskriving i boksens rutearray
		box.alleRuterIDenne[antallElementer] = alleRuter[i][j];
		alleRuter[i][j].boks = box;
		box.oppdaterAntall(); // Antall elementer i boksen oppdateres
		alleRader[i].alleRuterIDenne[j] = alleRuter[i][j];
		alleRuter[i][j].rad = alleRader[i];
		alleKolonner[j].alleRuterIDenne[i] = alleRuter[i][j];
		alleRuter[i][j].kol = alleKolonner[j];
		alleRuter[i][j].b = this;
	    }
	}
    }
}
