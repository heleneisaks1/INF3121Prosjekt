import easyIO.*;
import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JOptionPane;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Klasse med main-tråden. Sjekker om det er parametere til åpningen.
 * Skriver løsningene til fil dersom det er to parametere; oppretter
 * GUI ellers. 
 ********************************************************************/

class Oblig5 {
    public static void main(String[] args) {
	Sudokubeholder sb;
	boolean toFiler = false;

	if(args.length == 1) {
	    sb = new Sudokubeholder(args[0]);
	} else if (args.length == 2) {
	    sb = new Sudokubeholder(args[0], args[1]);
	    toFiler = true;
	} else {
	    String filnavn = "";
	    JFileChooser fc = new JFileChooser("/Users/lothe/Documents");// Adressen må nødvendigvis
	    // endres på en annen PC, men på min Mac ville ikke FileChooseren fungere uten parameter. 
	    int resultat = fc.showOpenDialog(null);
	    if(resultat == JFileChooser.APPROVE_OPTION) {
		File file = fc.getSelectedFile();
		filnavn = file.getName();
	    }
	    sb = new Sudokubeholder(filnavn);
	}

	sb.lesInn();
	sb.b.alleRuter[0][0].fyllUtRestenAvBrettet();
	
	if(toFiler) {
	    sb.skrivInn();
	} else {
	    sb.opprettGUI();
	}
    }
}	    
