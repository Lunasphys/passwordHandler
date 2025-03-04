package su.bnair.bpassword;

import su.bnair.bpassword.models.StoredInformation;
import su.bnair.bpassword.ui.*;

import javax.swing.*;

public class Instances {
	public static final String NAME = "PasswordManager";
	public static final String VERSION = "1.0.1";
	public static MainFrame MAIN_FRAME = new MainFrame();
	public static PassGenFrame PASS_GEN_FRAME = new PassGenFrame();
	public static InformationStoreFrame ID_STORE_FRAME = new InformationStoreFrame();
	public static DefaultListModel<StoredInformation> listModel;
	public static String DATABASE_URL;
	public static String USERNAME;
	public static String PASSWORD;
	public static Login LOGIN = new Login();
	public static Signup SIGNUP = new Signup();
	
	public static void hide(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(false);
	}
	
	public static void open(JFrame frame) {
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static JFrame getLogin() {
		return LOGIN;
	}

	public static void setLogin(Login login) {
		LOGIN = login;
	}

	public static Signup getSignup() {
		return SIGNUP;
	}

	public static void setSignup(Signup signup) {
		SIGNUP = signup;
	}

	public static MainFrame getMainFrame() {
		return MAIN_FRAME;
	}
	
	public static void setMainFrame(MainFrame mainFrame) {
		MAIN_FRAME = mainFrame;
	}
	
	public static PassGenFrame getPassGenFrame() {
		return PASS_GEN_FRAME;
	}
	
	public static InformationStoreFrame getIdStoreFrame() {
		return ID_STORE_FRAME;
	}
	
	public static void addElementToIdList(StoredInformation info) {
		listModel.addElement(info);
	}
	
	public static void deleteElementFromIdList(int index) {
		listModel.remove(index);
	}

	public static DefaultListModel<StoredInformation> getIdList() {
		if(listModel == null) {
			listModel = new DefaultListModel<StoredInformation>();
			/*
			listModel.addElement(new StoredInformation("Cloudflare", "https://cloudflare.com/", "van.bellinghen.brian@gmail.com", "azertyuiop"));
			listModel.addElement(new StoredInformation("Facebook", "https://facebook.com/", "van.bellinghen.brian@gmail.com", "azertyuiop"));
			listModel.addElement(new StoredInformation("Youtube", "https://youtube.com/", "van.bellinghen.brian@gmail.com", "azertyuiop"));
			listModel.addElement(new StoredInformation("Twitter", "https://twitter.com/", "van.bellinghen.brian@gmail.com", "azertyuiop"));
			*/
		}
		return listModel;
	}
}
