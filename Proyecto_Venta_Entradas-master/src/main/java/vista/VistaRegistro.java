package vista;

import javax.swing.*;
import java.awt.*;

public class VistaRegistro extends JFrame {

    public JTextField     txtNombres;
    public JTextField     txtApellidos;
    public JTextField     txtDni;
    public JPasswordField txtClave;
    public JButton        btnRegistrar;
    public JButton        btnVolver;

    public VistaRegistro() {
        setTitle("Registro de Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel lblTitulo = new JLabel("Registro de Cliente");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));

        txtNombres   = new JTextField(15);
        txtApellidos = new JTextField(15);
        txtDni       = new JTextField(15);
        txtClave     = new JPasswordField(15);
        btnRegistrar = new JButton("Registrarme");
        btnVolver    = new JButton("Volver al Login");

        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; add(lblTitulo, gbc);
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1; add(new JLabel("Nombres:"), gbc);
        gbc.gridx = 1;                add(txtNombres, gbc);
        gbc.gridx = 0; gbc.gridy = 2; add(new JLabel("Apellidos:"), gbc);
        gbc.gridx = 1;                add(txtApellidos, gbc);
        gbc.gridx = 0; gbc.gridy = 3; add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1;                add(txtDni, gbc);
        gbc.gridx = 0; gbc.gridy = 4; add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;                add(txtClave, gbc);
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; add(btnRegistrar, gbc);
        gbc.gridy = 6;                                    add(btnVolver, gbc);

        pack();
    }
}
