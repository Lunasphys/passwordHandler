package su.bnair.bpassword.ui;

import su.bnair.bpassword.ui.models.NamedJFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Signup extends NamedJFrame {

    private JPanel contentPane;
    private JTextField nameTextfield;
    private JPasswordField passwordTextfield;

    public Signup() {
        super("Signup");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                int confirm = JOptionPane.showOptionDialog(null, "Voulez vous quitter l'application?", getTitle(), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(confirm == 0)
                    System.exit(0);
            }
        });
        setBounds(100, 100, 450, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel nameLabel = new JLabel("Nom:");
        nameLabel.setBounds(10, 11, 85, 14);
        contentPane.add(nameLabel);

        JLabel passLabel = new JLabel("Mot de passe:");
        passLabel.setBounds(10, 86, 102, 14);
        contentPane.add(passLabel);

        nameTextfield = new JTextField();
		nameTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextfield.setBounds(52, 8, 372, 20);
		contentPane.add(nameTextfield);
		nameTextfield.setColumns(10);

        passwordTextfield = new JPasswordField();
		passwordTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTextfield.setBounds(106, 86, 318, 20);
		contentPane.add(passwordTextfield);

        JButton addButton = new JButton("Se connecter");
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(nameTextfield.getText().isEmpty() || passwordTextfield.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tous les champs n'ont pas été remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
        } else {

				}
			}
		});
		addButton.setBounds(10, 135, 414, 23);
		contentPane.add(addButton);
    }
}
