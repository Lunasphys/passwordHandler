package su.bnair.bpassword.ui.models;

import su.bnair.bpassword.Instances;

import javax.swing.*;

public class NamedJFrame extends JFrame {
	
	private String title;
	
	public NamedJFrame(String title) {
		setTitle(Instances.NAME + " | " + Instances.VERSION + " - " + title);
	}
}
