/**
 * Superklasse for alle Sudokuobjekter, dvs. rader, kolonner og bokser.
 * Alle Sudokuobjekter har en array av Ruter som holder styr på hvilke
 * objekter denne ruten inngår i. 
 ***********************************************************************/
abstract class SudokuObjekt {
    Rute[] alleRuterIDenne;
    int dim;

    SudokuObjekt(int dim) {
	alleRuterIDenne = new Rute[dim];
	this.dim = dim;
    }	

    /**
     * Hjelpemetode for å finne ut om char-verdien finnes i objektets
     * array av Ruter.
     * @param c Char-verdien det skal letes etter
     * @return true dersom verdien ikke finnes fra før
     *****************************************************************/
    boolean finnesIkke(char c) {
	for(int i = 0; i < dim; i++) {
	    if(c == alleRuterIDenne[i].verdi) {
		return false;
	    }
	} 

	return true;
    } 
}
