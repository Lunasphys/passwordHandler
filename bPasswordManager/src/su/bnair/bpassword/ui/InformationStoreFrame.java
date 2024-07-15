package su.bnair.bpassword.ui;

import su.bnair.bpassword.Instances;
import su.bnair.bpassword.models.StoredInformation;
import su.bnair.bpassword.ui.models.NamedJFrame;
import su.bnair.bpassword.utils.DatabaseUtils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class InformationStoreFrame extends NamedJFrame {

	private JPanel contentPane;
	private JTextField nameTextfield;
	private JTextField urlTextfield;
	private JTextField idTextfield;
	private JPasswordField passwordTextfield;

	public InformationStoreFrame() {
		super("Stoquage d'information");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	Instances.hide(Instances.getIdStoreFrame());
				Instances.open(Instances.getMainFrame());
            }
        });
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel nameLabel = new JLabel("Nom:");
		nameLabel.setBounds(10, 11, 85, 14);
		contentPane.add(nameLabel);

		JLabel urlLabel = new JLabel("URL:");
		urlLabel.setBounds(10, 36, 85, 14);
		contentPane.add(urlLabel);
		
		JLabel idLabel = new JLabel("Identifiant: ");
		idLabel.setBounds(10, 61, 85, 14);
		contentPane.add(idLabel);
		
		JLabel passLabel = new JLabel("Mot de passe:");
		passLabel.setBounds(10, 86, 102, 14);
		contentPane.add(passLabel);

		JProgressBar strongPassword = new JProgressBar();
		int x1 = this.getWidth()/2;
		int x2 = this.getWidth()/4;
		strongPassword.setBounds(x2,115,x1,14);
		contentPane.add(strongPassword);
		
		nameTextfield = new JTextField();
		nameTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextfield.setBounds(52, 8, 372, 20);
		contentPane.add(nameTextfield);
		nameTextfield.setColumns(10);
		
		urlTextfield = new JTextField();
		urlTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		urlTextfield.setBounds(52, 33, 372, 20);
		contentPane.add(urlTextfield);
		urlTextfield.setColumns(10);
		
		idTextfield = new JTextField();
		idTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		idTextfield.setBounds(85, 58, 339, 20);
		contentPane.add(idTextfield);
		idTextfield.setColumns(10);
		
		JButton addButton = new JButton("Ajouter");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameTextfield.getText().isEmpty() || urlTextfield.getText().isEmpty() || idTextfield.getText().isEmpty() || passwordTextfield.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tous les champs n'ont pas été remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {
					StoredInformation si = new StoredInformation(
							nameTextfield.getText(),
							urlTextfield.getText(),
							idTextfield.getText(),
							passwordTextfield.getText());
					Instances.addElementToIdList(si);
					DatabaseUtils.addStoredInformation(si);
					Instances.hide(Instances.getIdStoreFrame());
					Instances.open(Instances.getMainFrame());
				}
			}
		});
		addButton.setBounds(10, 135, 414, 23);
		contentPane.add(addButton);
		
		passwordTextfield = new JPasswordField();
		passwordTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTextfield.setBounds(106, 86, 318, 20);
		contentPane.add(passwordTextfield);
	}
}
