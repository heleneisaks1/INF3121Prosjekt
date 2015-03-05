/**
 * Klasse for Sudokubeholder. Hver beholder har styr på et brett som
 * leses inn fra fil, antall løsninger for dette brettet og en array som
 * lagrer alle løsningene.
 **********************************************************************/
class Sudokubeholder {
    String oppgavefil;
    String utfil;
    Brett b;
    int antallLosninger;
    Losning[] losning = new Losning[500];
    

    Sudokubeholder(String oppgavefil) {
	this.oppgavefil = oppgavefil;
	antallLosninger = 0;
    }

    Sudokubeholder(String oppgavefil, String utfil) {
	this.oppgavefil = oppgavefil;
	this.utfil = utfil;
	antallLosninger = 0;
    }

 /**
     * Metode for å sette en løsning inn i løsnings-arrayen. 
     * Øker antall løsninger med én og skriver til fil.
     * @param br Det ferdig utfylte brettet (løsningen) som skal
     * settes inn i løsningsamlingen. 
     ***********************************************************/
    public void settInn(Brett br) {
	if(antallLosninger < 500) {
	    final Losning lo = new Losning(br);
	    losning[antallLosninger] = lo;
	    antallLosninger++;
	} else {
	    System.out.println("Beholderen er full!");
	}
    }
    
    /**
     * Metode som returnerer en løsning. 
     * @param i Løsningen som skal returneres sin indeks. 
     * @return Løsningen. 
     **************************************************************/
    public Losning taUt(int i) {
	return losning[i];
    }
    
    /**
     * Metode som returnerer antall løsninger.
     * @return antall løsninger i beholderen.
     *************************************************************/
    public int hentAntallLosninger() {
	return antallLosninger;
    }

    /**
     * Metode som leser inn fra fil. Er ikke helt sikker på om dette er 
     * riktig plassering for denne, men dette virket mest logisk for meg...
     ********************************************************************/
    public void lesInn() {
	In innfil = new In(oppgavefil);
	int dim = innfil.inInt();
	int radPerBoks = innfil.inInt();
	int kolPerBoks = innfil.inInt();
	Rute[][] alleRutene = new Rute[dim][dim];
	innfil.skipWhite();

	for(int i = 0; i < dim; i++) {
	    for(int j = 0; j < dim; j++) {
		innfil.skipWhite();
		char c = innfil.inChar();
		Rute r;
		if(c != '.') { 
		    r = new UtfyltRute(dim, c);
		} else {
		    r = new TomRute(dim, '0');
		}
		r.radIndex = i;
		r.kolIndex = j;
		r.sb = this;
		alleRutene[i][j] = r;
	    }
	}
	b = new Brett(dim, alleRutene, radPerBoks, kolPerBoks);
    }

    /**
     * Metode for å skrive løsninger til fil. 
     **************************************************************/
    public void skrivInn() {
	Out ut = new Out(utfil);
	int x = 0;
	while(x < antallLosninger) {
	    ut.out((x+1) + ". ");
	    for(int i = 0; i < b.dim; i++) {
		for(int j = 0; j < b.dim; j++) {
		    ut.out(losning[x].alleVerdier[i][j]);
		}
		ut.out("// ");
	    }
	    ut.outln();
	    x++;
	}
	ut.close();
    }
    

    /**
     * Metode som oppretter et GUI. Sender med denne beholderen som 
     * parameter til GUIets konstruktør. 
     ****************************************************************/
    public void opprettGUI() {
	SudokuGUI sg = new SudokuGUI(this);
    }
}
