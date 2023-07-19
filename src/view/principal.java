package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;

public class principal extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	@SuppressWarnings("unused")
	private Connection con;
	
	

	private JPanel contentPane;
	private JLabel lblData;
	private JButton lblStatus;
	//esta label será alterada pela tela de Login (public)
	public JLabel lblUsuario;
	public JButton btnUsuarios;
	public JButton btnRelatorios;
	public JPanel panelRodape;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					principal frame = new principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public principal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				setarData();
			}
		});
		setTitle("Sistema da Mecânica CarMech");
		setIconImage(Toolkit.getDefaultToolkit().getImage(principal.class.getResource("/img/pngegg.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnUsuarios = new JButton("");
		btnUsuarios.setEnabled(false);
		btnUsuarios.setBounds(10, 11, 97, 96);
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				usuarioos Usuarioos = new usuarioos();
				Usuarioos.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		btnUsuarios.setToolTipText("Usuarios");
		btnUsuarios.setBorder(null);
		btnUsuarios.setContentAreaFilled(false);
		btnUsuarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUsuarios.setIcon(new ImageIcon(principal.class.getResource("/img/pngegg (2).png")));
		contentPane.add(btnUsuarios);
		
		JButton btnSobre = new JButton("");
		btnSobre.setBounds(428, 192, 55, 54);
		btnSobre.setIcon(new ImageIcon(principal.class.getResource("/img/pngwing.com.png")));
		btnSobre.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSobre.setContentAreaFilled(false);
		btnSobre.setBorder(null);
		btnSobre.setToolTipText("Sobre");
		btnSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sobre Sobre = new sobre();
				Sobre.setVisible(true);
			}
		});
		contentPane.add(btnSobre);
		
		panelRodape = new JPanel();
		panelRodape.setBackground(new Color(0, 0, 0));
		panelRodape.setBounds(0, 253, 483, 54);
		contentPane.add(panelRodape);
		panelRodape.setLayout(null);
		
		lblStatus = new JButton("");
		lblStatus.setToolTipText("Status");
		lblStatus.setContentAreaFilled(false);
		lblStatus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblStatus.setBorder(null);
		lblStatus.setIcon(new ImageIcon(principal.class.getResource("/img/dbon.png")));
		lblStatus.setBounds(-20, 0, 89, 54);
		panelRodape.add(lblStatus);
		
		lblData = new JLabel("New label");
		lblData.setBounds(289, 11, 194, 32);
		panelRodape.add(lblData);
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setBackground(new Color(255, 255, 255));
		
		JLabel lblNewLabel_1 = new JLabel("Usuário:");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(62, 20, 69, 14);
		panelRodape.add(lblNewLabel_1);
		
		lblUsuario = new JLabel("");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBounds(118, 20, 148, 14);
		panelRodape.add(lblUsuario);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(principal.class.getResource("/img/pngwing.com (2).png")));
		lblNewLabel.setBounds(0, 166, 97, 119);
		contentPane.add(lblNewLabel);
		
		btnRelatorios = new JButton("");
		btnRelatorios.setEnabled(false);
		btnRelatorios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				relatorios Relatorios = new relatorios();
				Relatorios.setVisible(true);
			}
		});
		btnRelatorios.setBorder(null);
		btnRelatorios.setContentAreaFilled(false);
		btnRelatorios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRelatorios.setToolTipText("Relatórios");
		btnRelatorios.setIcon(new ImageIcon(principal.class.getResource("/img/pngegg (4).png")));
		btnRelatorios.setBounds(259, 11, 97, 96);
		contentPane.add(btnRelatorios);
		
		JButton btnServicos = new JButton("");
		btnServicos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servicos Servicos = new servicos();
				Servicos.setVisible(true);
			}
		});
		btnServicos.setIcon(new ImageIcon(principal.class.getResource("/img/pngegg (5).png")));
		btnServicos.setContentAreaFilled(false);
		btnServicos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnServicos.setToolTipText("Serviços");
		btnServicos.setBorder(null);
		btnServicos.setBounds(359, 11, 97, 96);
		contentPane.add(btnServicos);
		
		JButton btnClientes = new JButton("");
		btnClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clientes Clientes = new clientes();
				Clientes.setVisible(true);
			}
		});
		btnClientes.setIcon(new ImageIcon(principal.class.getResource("/img/pngegg (13).png")));
		btnClientes.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnClientes.setContentAreaFilled(false);
		btnClientes.setBorder(null);
		btnClientes.setToolTipText("Cliente");
		btnClientes.setBounds(128, 11, 108, 96);
		contentPane.add(btnClientes);
		
		JButton btnFornecedor = new JButton("");
		btnFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fornecedor Fornecedor = new fornecedor();
				Fornecedor.setVisible(true);
			}
		});
		btnFornecedor.setIcon(new ImageIcon(principal.class.getResource("/img/pngwing.com (8).png")));
		btnFornecedor.setToolTipText("Usuarios");
		btnFornecedor.setContentAreaFilled(false);
		btnFornecedor.setBorder(null);
		btnFornecedor.setBounds(150, 136, 147, 96);
		contentPane.add(btnFornecedor);
		/**
		 * 
		 */
	}
	private void setarData() {
		Date data = new Date();
		DateFormat formatador = DateFormat.getDateInstance(DateFormat.FULL);
		lblData.setText(formatador.format(data));
	}
}// fim do código