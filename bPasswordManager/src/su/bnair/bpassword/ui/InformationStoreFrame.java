package su.bnair.bpassword.ui;

import com.fasterxml.jackson.core.type.TypeReference;
import su.bnair.bpassword.Instances;
import su.bnair.bpassword.models.StoredInformation;
import su.bnair.bpassword.ui.models.NamedJFrame;
import su.bnair.bpassword.utils.DatabaseUtils;
import su.bnair.bpassword.utils.PasswordGenerator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class InformationStoreFrame extends NamedJFrame {

    private JPanel contentPane;
    private JTextField nameTextfield;
    private JTextField urlTextfield;
    private JTextField idTextfield;
    private JPasswordField passwordTextfield;
    private static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*_=+-/";

    public InformationStoreFrame() {
        super("Stoquage d'information");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
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

        Random rand = new Random();
        int n = rand.nextInt(56) + 8;


        JButton generateButton = new JButton("Générer");
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                passwordTextfield.setText(PasswordGenerator.generatePassword(n, true, true, true));
            }
        });

        generateButton.setBounds(15, 110, 150, 23);
        contentPane.add(generateButton);

        JProgressBar strongPassword = new JProgressBar();
        int x1 = this.getWidth() / 2;
        strongPassword.setBounds(200, 115, x1, 14);
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
                if (nameTextfield.getText().isEmpty() || urlTextfield.getText().isEmpty() || idTextfield.getText().isEmpty() || passwordTextfield.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tous les champs n'ont pas été remplis", "Erreur", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Création de l'objet StoredInformation
                    StoredInformation si = new StoredInformation(
                            nameTextfield.getText(),
                            urlTextfield.getText(),
                            idTextfield.getText(),
                            passwordTextfield.getText()
                    );

                    // Convertir l'objet en JSON avec Jackson
                    String json = null;
                    try {
                        // Utilisation de Jackson pour convertir l'objet en JSON
                        ObjectMapper objectMapper = new ObjectMapper();

                        // Vérifier si le fichier JSON existe
                        File file = new File("data.json");
                        List<StoredInformation> storedInfoList = new ArrayList<>();

                        if (file.exists()) {
                            // Lire le contenu actuel du fichier JSON
                            storedInfoList = objectMapper.readValue(file, new TypeReference<>() {
                            });
                        }

                        // Ajouter la nouvelle information
                        storedInfoList.add(si);

                        // Écrire la liste mise à jour dans le fichier JSON
                        objectMapper.writeValue(file, storedInfoList);

                        // Utilisation du JSON obtenu comme local storage

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

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

        passwordTextfield.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateProgressBar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateProgressBar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateProgressBar();
            }

            private void updateProgressBar() {
                String password = passwordTextfield.getText();
                int strength = 0;

                if (password.matches(".*[" + LOWER_CASE + "].*")) {
                    strength += 25;
                }
                if (password.matches(".*[" + UPPER_CASE + "].*")) {
                    strength += 25;
                }
                if (password.matches(".*[" + NUMBERS + "].*")) {
                    strength += 25;
                }
                if (password.matches(".*[" + SPECIAL_CHARACTERS + "].*")) {
                    strength += 25;
                }

                strongPassword.setValue(strength);
            }
        });

    }
}
