package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import model.DAO;
import utils.Validador;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;

public class usuarioos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;

	private JTextField textID;
	private JTextField textNome;
	private JTextField textLogin;
	private JPasswordField textSenha;
	private JButton btnPesquisar;
	private JButton btnAdicionar;
	private JButton btnLimparCampos;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JList listaUsuarios;
	private JScrollPane scrollPaneUsuarios;
	private JList listaUsuario;
	private JLabel lblPerfil;
	private JComboBox Combo;
	private JCheckBox chckSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					usuarioos dialog = new usuarioos();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public usuarioos() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// clicar no painel e sumir
				scrollPaneUsuarios.setVisible(false);
				textNome.setText(null);
			}
		});
		setTitle("Usuários");
		setIconImage(Toolkit.getDefaultToolkit().getImage(usuarioos.class.getResource("/img/pngegg.png")));
		setBounds(100, 100, 631, 387);

		scrollPaneUsuarios = new JScrollPane();
		scrollPaneUsuarios.setBounds(350, 13, 204, 80);
		scrollPaneUsuarios.setVisible(false);
		getContentPane().setLayout(null);
		scrollPaneUsuarios.setBorder(null);
		getContentPane().add(scrollPaneUsuarios);
		
				listaUsuario = new JList();
				scrollPaneUsuarios.setViewportView(listaUsuario);
				listaUsuario.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						BuscarUsuarioLista();
					}
				});

		JLabel txtid = new JLabel("ID");
		txtid.setBounds(12, 14, 23, 14);
		getContentPane().add(txtid);

		JLabel txtnome = new JLabel("NOME");
		txtnome.setBounds(12, 104, 34, 14);
		getContentPane().add(txtnome);

		JLabel txtlogin = new JLabel("LOGIN");
		txtlogin.setBounds(12, 49, 50, 14);
		getContentPane().add(txtlogin);

		JLabel txtsenha = new JLabel("SENHA");
		txtsenha.setBounds(12, 162, 50, 14);
		getContentPane().add(txtsenha);

		textID = new JTextField();
		textID.setBounds(68, 11, 34, 20);
		textID.setEditable(false);
		getContentPane().add(textID);
		textID.setColumns(10);

		textNome = new JTextField();
		textNome.setBounds(56, 98, 204, 20);
		textNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// pressionar uma tecla...

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// soltar uma tecla
				listarUsuarios();

			}

		});
		getContentPane().add(textNome);
		textNome.setColumns(10);
		textNome.setDocument(new Validador(20));

		textLogin = new JTextField();
		textLogin.setBounds(66, 46, 194, 20);
		getContentPane().add(textLogin);
		textLogin.setColumns(10);
		textLogin.setDocument(new Validador(10));

		textID = new JTextField();
		textID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}

			}

		});

		btnPesquisar = new JButton("");
		btnPesquisar.setBounds(374, 232, 80, 68);
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Buscar();
			}
		});
		btnPesquisar.setToolTipText("Pesquisar");
		btnPesquisar.setBorder(null);
		btnPesquisar.setContentAreaFilled(false);
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(usuarioos.class.getResource("/img/pngwing.com (4).png")));
		getContentPane().add(btnPesquisar);

		btnLimparCampos = new JButton("");
		btnLimparCampos.setBounds(283, 220, 69, 80);
		btnLimparCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LimparCampos();
			}
		});
		btnLimparCampos.setIcon(new ImageIcon(usuarioos.class.getResource("/img/pngegg (3).png")));
		btnLimparCampos.setToolTipText("LimparCampos");
		btnLimparCampos.setContentAreaFilled(false);
		btnLimparCampos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		getContentPane().add(btnLimparCampos);

		textSenha = new JPasswordField();
		textSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textSenha.setText(null);
				textSenha.requestFocus();
				textSenha.setBackground(Color.YELLOW);
			}
		});
		textSenha.setBounds(68, 159, 192, 20);
		getContentPane().add(textSenha);
		getRootPane().setDefaultButton(btnPesquisar);

		btnAdicionar = new JButton("");
		btnAdicionar.setBounds(24, 220, 80, 80);
		btnAdicionar.setEnabled(false);
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnAdicionar.setBorder(null);
		btnAdicionar.setContentAreaFilled(false);
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(usuarioos.class.getResource("/img/addblackcircularbutton_104741 (1).png")));
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.setBounds(114, 220, 69, 80);
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckSenha.isSelected()) {
					editarUsuario();
				} else {
					editarUsuarioExcetoSenha();
				}	
			}
		});
		btnEditar.setBorder(null);
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(
				usuarioos.class.getResource("/img/pencileditblackcircularbuttoninterfacesymbol_104762 (4).png")));
		btnEditar.setToolTipText("Editar");
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setBounds(193, 224, 69, 76);
		btnExcluir.setEnabled(false);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirContato();
			}
		});
		btnExcluir.setIcon(new ImageIcon(usuarioos.class.getResource("/img/crossdeleteblackcircularbutton_79721.png")));
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setBorder(null);
		btnExcluir.setToolTipText("Excluir");
		getContentPane().add(btnExcluir);

		listaUsuarios = new JList();
		listaUsuarios.setBounds(605, 174, -92, -58);
		getContentPane().add(listaUsuarios);
		listaUsuarios.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarUsuarioLista();
			}
		});
		listaUsuarios.setBorder(null);
		
		lblPerfil = new JLabel("Perfil:");
		lblPerfil.setBounds(283, 142, 46, 14);
		getContentPane().add(lblPerfil);
		
		Combo = new JComboBox();
		Combo.setModel(new DefaultComboBoxModel(new String[] {"", "admin", "usuario"}));
		Combo.setBounds(318, 138, 80, 22);
		getContentPane().add(Combo);
		
		chckSenha = new JCheckBox("Alterar Senha");
		chckSenha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckSenha.isSelected()) {
					textSenha.setText(null);
					textSenha.requestFocus();
					textSenha.setBackground(Color.YELLOW);
				} else {
					textSenha.setBackground(Color.WHITE);
		
			}
		}
	});
	chckSenha.setBounds(12, 202, 124, 23);
	getContentPane().add(chckSenha);
	}
	private void listarUsuarios() {
		// System.out.println("Teste");
		// a linha abaixo criar um objeto usando como referência um vetor dinâmico, este
		// objeto irá temporariamente armazenar os nomes
		DefaultListModel<String> modelo = new DefaultListModel<>();
		// setar o modelo (vetor na lista)
		listaUsuario.setModel(modelo);
		// Query (instrução sql)
		String readlista = "select * from usuarios where nome like '" + textNome.getText() + "%'" + "order by nome";
		try {
			// abrir conexão
			con = dao.conectar();
			// preparar a query (instrução sql)
			pst = con.prepareStatement(readlista);
			// executar a query e trazer o resultado para a lista
			rs = pst.executeQuery();
			// uso do while para trazer os usuarios enquanto existir
			while (rs.next()) {
				// mostrar a barra de rolagem
				scrollPaneUsuarios.setVisible(true);
				// adicionar os usuarios no vetor -> lista
				modelo.addElement(rs.getString(2));
				// esconder a lista se nenhuma leta for digitada
				if (textNome.getText().isEmpty()) {
					scrollPaneUsuarios.setVisible(false);
				}
			}
			// fechar a conexão
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	

	private void LimparCampos() {
		textID.setText(null);
		textNome.setText(null);
		textLogin.setText(null);
		textSenha.setText(null);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnPesquisar.setEnabled(true);
		btnAdicionar.setEnabled(false);
		scrollPaneUsuarios.setVisible(false);
		Combo.setSelectedItem("");
		textSenha.setBackground(Color.WHITE);
		chckSenha.setSelected(false);

	}

	private void Buscar() {

		String read = "select * from usuarios where login = ?";

		try {

			con = dao.conectar();
			pst = con.prepareStatement(read);
			pst.setString(1, textLogin.getText());
			rs = pst.executeQuery();

			if (rs.next()) {
				textID.setText(rs.getString(1));
				textNome.setText(rs.getString(2));
				textLogin.setText(rs.getString(3));
				textSenha.setText(rs.getString(4));
				Combo.setSelectedItem(rs.getString(5));
				btnExcluir.setEnabled(true);
				btnEditar.setEnabled(true);
				btnPesquisar.setEnabled(true);

			} else {
				JOptionPane.showMessageDialog(null, "Usuário inexistente");
				btnAdicionar.setEnabled(true);
				btnPesquisar.setEnabled(false);
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void adicionar() {
		if (textNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Usuário");
			textNome.requestFocus();
		} else if (textLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o login do Usuário");
			textLogin.requestFocus();
		} else if (textSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha a Senha do Usuário");
			textSenha.requestFocus();
		} else {
			String create = "insert into usuarios(nome,login,senha,perfil) values (?,?,md5(?),?)";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, textNome.getText());
				pst.setString(2, textLogin.getText());
				pst.setString(3, textSenha.getText());
				pst.setString(4, Combo.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Usuário adicionado");
				LimparCampos();
				con.close();
			} catch (Exception e) {
				System.out.println(e);

			}

		}

	}

	private void editarUsuario() {

			String update2 = "update usuarios set nome=?,login=?,senha=md5(?),perfil=? where id=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update2);
				pst.setString(1, textNome.getText());
				pst.setString(2, textLogin.getText());
				pst.setString(3, textSenha.getText());
				pst.setString(4, Combo.getSelectedItem().toString());
				pst.setString(5, textID.getText());
				pst.executeUpdate();
				JOptionPane.showInternalMessageDialog(null, "Dados do Usuário editados com Sucesso");
				LimparCampos();
				con.close();

			} catch (Exception e) {
				System.out.println(e);

			}
		}
	private void editarUsuarioExcetoSenha() {

		String update2 = "update usuarios set nome=?,login=?,perfil=? where id=?";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(update2);
			pst.setString(1, textNome.getText());
			pst.setString(2, textLogin.getText());
			pst.setString(3, Combo.getSelectedItem().toString());
			pst.setString(4, textID.getText());
			pst.executeUpdate();
			JOptionPane.showInternalMessageDialog(null, "Dados do Usuário editados com Sucesso");
			LimparCampos();
			con.close();

		} catch (Exception e) {
			System.out.println(e);

		}
	}
	

	private void excluirContato() {
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from usuarios where id=?";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, textID.getText());
				pst.executeUpdate();
				LimparCampos();
				JOptionPane.showMessageDialog(null, "Usuário Excluído");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}
	private void BuscarUsuarioLista() {
		int linha = listaUsuario.getSelectedIndex();
		if (linha > 0) {
			String readlistaUsuario = "select * from usuarios where nome like '" + textNome.getText() + "%'"
					+ "order by nome limit " + (linha) + ", 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaUsuario);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPaneUsuarios.setVisible(false);
					textID.setText(rs.getString(1));
					textNome.setText(rs.getString(2));
					textLogin.setText(rs.getString(3));
					textSenha.setText(rs.getString(4));
					Combo.setSelectedItem(rs.getString(5));
				} else {
					JOptionPane.showMessageDialog(null, "Usuário Inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			scrollPaneUsuarios.setVisible(false);

		}

	}
}// FIM DO CÓDIGO
