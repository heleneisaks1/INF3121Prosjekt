/**
 * Klasse for løsninger. Alle løsninger har en todimensjonal array med char-
 * verdier. Jeg valgte å opprette en egen klasse for å ta vare på løsningene
 * ettersom det skar seg litt da jeg i Sudokubeholder.settInn() prøvde meg på 
 * en "losning[antallLosninger] = b" bare for å senere oppdage at alle   
 * "løsningene" pekte på det samme brettet...
 **************************************************************************/
class Losning {
    char[][] alleVerdier;
    int dim;

    Losning(Brett b) {
	this.dim = b.dim;
	alleVerdier = new char[dim][dim];
	for(int i = 0; i < b.dim; i++) {
	    for(int j = 0; j < b.dim; j++) {
		alleVerdier[i][j] = b.alleRuter[i][j].verdi;
	    }
	}
    }
}
