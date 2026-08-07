import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindow() {
        setTitle("Flappy Bird - Login / Signup");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 25);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(120, 30, 160, 25);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 25);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(120, 70, 160, 25);
        add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(30, 120, 130, 30);
        add(loginButton);

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(170, 120, 110, 30);
        add(signupButton);

        // Login Action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                if (DatabaseHelper.loginUser(user, pass)) {
                    JOptionPane.showMessageDialog(null, "Login Successful!");
                    dispose(); // Close login window
                    
                    // Launch Flappy Bird game window
                    JFrame frame = new JFrame("Flappy Bird");
                    frame.setSize(360, 640);
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    FlappyBird flappyBird = new FlappyBird();
                    frame.add(flappyBird);
                    frame.pack();
                    flappyBird.requestFocus();
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Signup Action
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = usernameField.getText();
                String pass = new String(passwordField.getPassword());

                if (user.isEmpty() || pass.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (DatabaseHelper.registerUser(user, pass)) {
                    JOptionPane.showMessageDialog(null, "Signup Successful! You can now log in.");
                } else {
                    JOptionPane.showMessageDialog(null, "Username already exists or error occurred.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}