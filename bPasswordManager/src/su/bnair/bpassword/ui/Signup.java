package su.bnair.bpassword.ui;

import org.yaml.snakeyaml.Yaml;
import su.bnair.bpassword.Instances;
import su.bnair.bpassword.ui.models.NamedJFrame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.Map;

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
        setBounds(100, 100, 300, 200);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel titleLabel = new JLabel("Inscription");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        titleLabel.setBounds(100, 0, 100, 20);
        contentPane.add(titleLabel);

        JLabel nameLabel = new JLabel("Nom:");
        nameLabel.setBounds(25, 40, 85, 14);
        contentPane.add(nameLabel);

        JLabel passLabel = new JLabel("Mot de passe:");
        passLabel.setBounds(10, 80, 102, 14);
        contentPane.add(passLabel);

        nameTextfield = new JTextField();
		nameTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		nameTextfield.setBounds(106, 40, 150, 20);
		contentPane.add(nameTextfield);
		nameTextfield.setColumns(10);

        passwordTextfield = new JPasswordField();
		passwordTextfield.setHorizontalAlignment(SwingConstants.CENTER);
		passwordTextfield.setBounds(106, 80, 150, 20);
		contentPane.add(passwordTextfield);

        JButton addButton = new JButton("S'inscrire'");
		addButton.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        try {
            File file = new File("config.yaml");
            Yaml yaml = new Yaml();

            try (InputStream input = new FileInputStream(file)) {
                Map<String, Object> data = yaml.load(input);

                String enteredUsername = nameTextfield.getText();
                String enteredPassword = passwordTextfield.getText();

                if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tous les champs n'ont pas été remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    data.put("USERNAME", enteredUsername);
                    data.put("PASSWORD", enteredPassword);

                    try (Writer output = new FileWriter(file)) {
                        yaml.dump(data, output);
                    }

                    JOptionPane.showMessageDialog(null, "Instances du fichier de configuration mises à jour avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
                    Instances.hide(Instances.getSignup());
                    Instances.open(Instances.getLogin());
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
});

		addButton.setBounds(10, 120, 250, 23);
		contentPane.add(addButton);
    }
}
