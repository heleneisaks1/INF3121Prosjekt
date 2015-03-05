/**
 * Klasse som utfører mesteparten av det GUI-relaterte i oppgaven.
 * Håper det er greit at dette ligger i den samme .java-filen som resten...
 * Denne siste delen av oppgaven er i stor grad basert på programmet 
 * av Christian Tryti og Stein Gjessing som ble lagt ut sammen med 
 * Obligen (http://folk.uio.no/inf1010/v13/oblig/5/SudokuGUI.java).
 * Jeg har gjort noen endringer, men mye er det samme.
 ************************************************************************/
class SudokuGUI extends JFrame implements ActionListener {
    private final int RUTE_STRELSE = 50;    
    private final int PLASS_TOPP = 50; 
    
    private SudokuGUI sg = this;
    private JTextField[][] brett;    
    private int dimensjon;		 
    private int vertikalAntall;
    private int horisontalAntall;
    private Sudokubeholder sb;
    private Brett b;
    private int losningteller;
    private JButton nesteKnapp;
    
    /**
     * Konstruktør for klassen SudokuGUI. Oppretter selve 
     * GUI-et.
     * @param Sudokubeholderen som opprettet GUIet, som igjen har 
     * pekere til aktuelt brett med verdier for størrelser. 
     **********************************************************/
    public SudokuGUI(Sudokubeholder sb) {
	this.sb = sb;
	this.b = sb.b;
	dimensjon = b.dim;
	vertikalAntall = b.radPerBoks;
	horisontalAntall = b.kolPerBoks;
	losningteller = sb.hentAntallLosninger();
	brett = new JTextField[dimensjon][dimensjon];
	setPreferredSize(new Dimension(dimensjon * RUTE_STRELSE, 
				       dimensjon  * RUTE_STRELSE + PLASS_TOPP));
	setTitle("Sudoku " + dimensjon +" x "+ dimensjon );
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setLayout(new BorderLayout());
	JPanel knappePanel = lagKnapper();
	JPanel brettPanel = lagBrettet();
	getContentPane().add(knappePanel,BorderLayout.NORTH);
	getContentPane().add(brettPanel,BorderLayout.CENTER);
	pack();
	setVisible(true);
    }
    
    
    /** 
     * Metode som lager et panel med alle rutene. 
     * @return en peker til panelet som er laget.
     ***************************************************************************/
    private JPanel lagBrettet() {
	int topp, venstre;
	JPanel brettPanel = new JPanel();
	brettPanel.setLayout(new GridLayout(dimensjon,dimensjon));
	brettPanel.setAlignmentX(CENTER_ALIGNMENT);
	brettPanel.setAlignmentY(CENTER_ALIGNMENT);
	setPreferredSize(new Dimension(new Dimension(dimensjon * RUTE_STRELSE, 
						     dimensjon * RUTE_STRELSE)));		
	for(int i = 0; i < dimensjon; i++) {
	    //finn ut om denne raden trenger en tykker linje paa toppen:
	    topp = (i % vertikalAntall == 0 && i != 0) ? 4 : 1;
	    for(int j = 0; j < dimensjon; j++) {
		//finn ut om denne ruten er en del av en kolonne 
		//som skal ha en tykkere linje til venstre:    
		venstre = (j % horisontalAntall == 0 && j != 0) ? 4 : 1;
		
		JTextField ruten = new JTextField();
		ruten.setBorder(BorderFactory.createMatteBorder
				(topp,venstre,1,1, Color.black));
		ruten.setHorizontalAlignment(SwingConstants.CENTER);
		ruten.setPreferredSize(new Dimension(RUTE_STRELSE, RUTE_STRELSE));
		char c = b.alleRuter[i][j].verdi;
		String verdien = Character.toString(c);
		ruten.setText(verdien);
		brett[i][j] = ruten;
		brettPanel.add(ruten);
	    }
	}
	return brettPanel;
    }
    

    /**
     * Metode som lager et knappepanel med tilhørende knapp ("Neste
     * løsning").
     * (Jeg forstod ikke hva som var vitsen med "Finn løsninger"-knappen,
     * ettersom det stod spesifisert i oppgaven at GUI-et ikke skulle 
     * opprettes før etter alle løsningene var funnet. Jeg valgte derfor
     * å fjerne denne fra knappepanelet. Håper jeg ikke har misforstått noe.)
     * @return en peker til knappepanelet.
     ********************************************************************/
    private JPanel lagKnapper() {
	JPanel knappPanel = new JPanel();
	knappPanel.setLayout(new BorderLayout());
	nesteKnapp = new JButton("Neste losning");
	Lytter knappelytter = new Lytter();
	nesteKnapp.addActionListener(this);
	knappPanel.add(nesteKnapp, BorderLayout.CENTER);
	return knappPanel;
    }

    /**
     * Metode for å oppdatere GUI-et når noen trykker på "neste"-knappen.
     * Metoden oppdaterer GUI-et for å samsvare med en løsning. 
     * @param Losningen som skal vises fram. 
     *****************************************************************/
    public void endreVerdierIRutene(Losning lo) {
	for(int i = 0; i < dimensjon; i++) {
	    for(int j = 0; j < dimensjon; j++) {
		char c = lo.alleVerdier[i][j];
		String s = Character.toString(c);
		brett[i][j].setText(s);
	    }
	}
    }
    
    /**
     * GUI-ets lytter-metode. Dersom det ikke er flere løsninger å se på
     * får brukeren beskjed via et pop-up vindu. 
     * @param e Hendelsen som kalte på metoden
     *******************************************************************/
    public void actionPerformed(ActionEvent e) {
	Object clicked = e.getSource();
	if(clicked == nesteKnapp) {
	    if(losningteller > 0) {
		Losning lo = sb.taUt(losningteller-1);
		losningteller--;
		endreVerdierIRutene(lo);
	    } else {
		JOptionPane.showMessageDialog(sg, "Ikke flere løsninger!", "Warning", JOptionPane.WARNING_MESSAGE);
	    }
	}
    }
}
