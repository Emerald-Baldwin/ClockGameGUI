import java.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.applet.*;
import java.io.*;
import java.net.*;

/** This program serves as the audio portion of the CLockGame program **/
public class Audio extends JApplet {
	private AudioClip mainMarioTheme, marioStageClear, marioTimeWarning, marioGameOver;
	private File file1, file2, file3, file4;
	private URI uri1, uri2, uri3, uri4;
	private URL url1, url2, url3, url4; 
	
	public Audio() throws MalformedURLException { // We needed to throw an 
		// exception for Malformed URLs
		// get audio clips
		file1 = new File("mainMarioTheme.mid");
		uri1 = file1.toURI();
		url1 = uri1.toURL();
		mainMarioTheme = Applet.newAudioClip(url1);
		file2 = new File("marioStageClear.wav");
		uri2 = file2.toURI();
		url2 = uri2.toURL();
		marioStageClear = Applet.newAudioClip(url2);
		file3 = new File("marioTimeWarning.wav");
		uri3 = file3.toURI();
		url3 = uri3.toURL();
		marioTimeWarning = Applet.newAudioClip(url3);
		file4 = new File("marioGameOver.mid");
		uri4 = file4.toURI();
		url4 = uri4.toURL();
		marioGameOver = Applet.newAudioClip(url4);
	}
	
	/** mainThemeStart is an accessor method that runs the main theme when
	    called
	*/	
	public void mainThemeStart() {
		mainMarioTheme.play();
	}
	
	/** mainThemeStop is an accessor method that runs the main theme when
	    called
	*/	
	public void mainThemeStop() {
		mainMarioTheme.stop();
	}
	
	/** stageClearStart is an accessor method that runs the main theme when
	    called
	*/	
	public void stageClearStart() {
		marioStageClear.play();
	}
	
	/** timeWarningStart is an accessor method that runs the main theme when
	    called
	*/	
	public void timeWarningStart() {
		marioTimeWarning.play();
	}
	
	/** gameOverStart is an accessor method that runs the main theme when
	    called
	*/	
	public void gameOverStart() {
		marioGameOver.play();
	}
}
		
