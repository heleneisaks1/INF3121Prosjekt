/**
 * Superklasse for Ruteobjekter. Alle ruter har en verdi, et brett, en beholder,
 * en rad, en kolonne og en boks de tilhører, samt en metode for å fylle ut resten 
 * av brettet. I tillegg til dette har de noen hjelpevariabler for hvor i raden
 * og kolonnen de ligger (radIndex og kolIndex). I tillegg har de to charsett:
 * et for det største mulige brettet (16x16) og et aktuelt (størrelsen på dette
 * brettet) for å vite hvilke verdier som kan prøves (håper dette var lov...)
 *****************************************************************************/
abstract class Rute {
    char verdi;
    Sudokubeholder sb;
    Brett b;
    Rad rad;
    Kolonne kol;
    Boks boks;
    int radIndex;
    int kolIndex;
    int dim;
    final char[] charsett = new char[] {'1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G'}; // alle de lovlige verdiene i max brett (16x16)
    final char[] aktueltCharsett;
    
    /**
     * Konstruktør for Ruter. Aktuelt charsett opprettes og fylles inn i 
     * henhold til dimensjonen.
     **********************************************************************/
    Rute(int dim, char verdi) {
	this.dim = dim;
	this.verdi = verdi;
	aktueltCharsett = new char[dim];
	for(int i = 0; i < dim; i++) {
	    aktueltCharsett[i] = charsett[i];
	}
    }
    abstract void fyllUtRestenAvBrettet();
}
 
