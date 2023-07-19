package view;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import model.DAO;
import utils.Validador;

public class clientes extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtID;
	private JTextField txtNome;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtFone;

	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JButton btnExcluir;
	private JButton btnAdd;
	private JButton btnLimpar;
	private JButton btnEditar;
	private JButton btnBuscarCep;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JTextField txtBairro;
	private JTextField txtComplemento;
	private JTextField txtNumero;
	private JTextField txtCidade;
	private JLabel lblNewLabel_9;
	private JComboBox cboUf;
	private JScrollPane scrollPaneNome;
	private JList listaNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientes dialog = new clientes();
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
	public clientes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(clientes.class.getResource("/img/pngegg.png")));
		setTitle("Clientes");
		setBounds(100, 100, 637, 450);

		txtID = new JTextField();
		txtID.setBounds(129, 11, 46, 20);
		txtID.setEnabled(false);
		txtID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		getContentPane().setLayout(null);

		scrollPaneNome = new JScrollPane();
		scrollPaneNome.setVisible(false);
		scrollPaneNome.setBorder(null);
		scrollPaneNome.setBounds(129, 76, 240, 65);
		getContentPane().add(scrollPaneNome);
		
				listaNome = new JList();
				scrollPaneNome.setViewportView(listaNome);
				listaNome.setBorder(null);
				listaNome.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						BuscarNomeCliente();
					}
				});
		getContentPane().add(txtID);
		txtID.setColumns(10);

		txtNome = new JTextField();
		txtNome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				ListarCliente();
			}
		});
		txtNome.setBounds(129, 58, 240, 20);
		getContentPane().add(txtNome);
		txtNome.setColumns(10);
		txtNome.setDocument(new Validador(30));

		txtCep = new JTextField();
		txtCep.setBounds(129, 192, 125, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);
		txtCep.setDocument(new Validador(10));
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(129, 161, 240, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		txtFone = new JTextField();
		txtFone.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				

				String caracteres = "0123456789.";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
				}
			}
			
		});
		txtFone.setBounds(129, 121, 125, 20);
		getContentPane().add(txtFone);
		txtFone.setColumns(10);
		txtFone.setDocument(new Validador(12));

		btnAdd = new JButton("");
		btnAdd.setBounds(38, 346, 89, 54);
		btnAdd.setHideActionText(true);
		btnAdd.setIcon(new ImageIcon(clientes.class.getResource("/img/addblackcircularbutton_104741 (1).png")));
		btnAdd.setToolTipText("Adicionar");
		btnAdd.setContentAreaFilled(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setBorder(null);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add();
			}
		});
		getContentPane().add(btnAdd);

		btnEditar = new JButton("");
		btnEditar.setBounds(139, 346, 89, 54);
		btnEditar.setIcon(new ImageIcon(clientes.class.getResource("/img/pencileditblackcircularbuttoninterfacesymbol_104762 (4).png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setBorder(null);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.setBounds(238, 346, 89, 54);
		btnExcluir.setIcon(new ImageIcon(clientes.class.getResource("/img/crossdeleteblackcircularbutton_79721.png")));
		btnExcluir.setBorder(null);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Excluir();
			}
		});
		getContentPane().add(btnExcluir);

		btnBuscarCep = new JButton("Buscar CEP");
		btnBuscarCep.setBounds(264, 191, 89, 23);
		btnBuscarCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCep();
				
			}
		});
		getContentPane().add(btnBuscarCep);

		btnLimpar = new JButton(""); 
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.setBounds(337, 346, 89, 54);
		btnLimpar.setBorder(null);
		btnLimpar.setIcon(new ImageIcon(clientes.class.getResource("/img/pngegg (3).png")));
		btnLimpar.setToolTipText("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		getContentPane().add(btnLimpar);

		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(57, 14, 46, 14);
		getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Nome");
		lblNewLabel_1.setBounds(57, 82, 46, 14);
		getContentPane().add(lblNewLabel_1);

		lblNewLabel_2 = new JLabel("Cep");
		lblNewLabel_2.setBounds(57, 189, 46, 34);
		getContentPane().add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("Endereço");
		lblNewLabel_3.setBounds(57, 164, 62, 14);
		getContentPane().add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Fone");
		lblNewLabel_4.setBounds(57, 124, 46, 14);
		getContentPane().add(lblNewLabel_4);
		getRootPane().setDefaultButton(btnBuscarCep);

		txtBairro = new JTextField();
		txtBairro.setBounds(129, 225, 125, 20);
		getContentPane().add(txtBairro);
		txtBairro.setColumns(10);
		txtBairro.setDocument(new Validador(20));

		JLabel lblNewLabel_5 = new JLabel("Bairro");
		lblNewLabel_5.setBounds(57, 231, 46, 14);
		getContentPane().add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Complemento");
		lblNewLabel_6.setBounds(38, 256, 89, 14);
		getContentPane().add(lblNewLabel_6);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(129, 253, 125, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);
		txtComplemento.setDocument(new Validador(15));

		JLabel lblNewLabel_7 = new JLabel("N°");
		lblNewLabel_7.setBounds(57, 287, 46, 14);
		getContentPane().add(lblNewLabel_7);

		txtNumero = new JTextField();
		txtNumero.setBounds(129, 284, 46, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		txtNumero.setDocument(new Validador(13));

		txtCidade = new JTextField();
		txtCidade.setBounds(130, 315, 113, 20);
		getContentPane().add(txtCidade);
		txtCidade.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Cidade");
		lblNewLabel_8.setBounds(57, 321, 46, 14);
		getContentPane().add(lblNewLabel_8);

		lblNewLabel_9 = new JLabel("UF");
		lblNewLabel_9.setBounds(281, 288, 46, 14);
		getContentPane().add(lblNewLabel_9);

		cboUf = new JComboBox();
		cboUf.setBounds(281, 313, 46, 22);
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		getContentPane().add(cboUf);

	}
	
	private void ListarCliente() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaNome.setModel(modelo);

		String readlistaCliente = "select * from clientes where nome like '" + txtNome.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readlistaCliente);
			rs = pst.executeQuery();
			while (rs.next()) {
				scrollPaneNome.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtNome.getText().isEmpty()) {
					scrollPaneNome.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	private void BuscarNomeCliente() {
		int linha = listaNome.getSelectedIndex();
		if (linha >= 0) {
			String readlistaCliente = "select * from clientes where nome like '" + txtNome.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaCliente);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPaneNome.setVisible(false);
					txtID.setText(rs.getString(1));
					txtNome.setText(rs.getString(2));
					txtFone.setText(rs.getString(3));
					txtCep.setText(rs.getString(4));
					txtEndereco.setText(rs.getString(5));
					txtNumero.setText(rs.getString(6));
					txtComplemento.setText(rs.getString(7));
					txtBairro.setText(rs.getString(8));
					txtCidade.setText(rs.getString(9));
					cboUf.setSelectedItem(rs.getString(10));
					btnAdd.setEnabled(false);
					btnEditar.setEnabled(true);
					btnExcluir.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			scrollPaneNome.setVisible(false);
		}
	}


	private void Limpar() {
		txtID.setText(null);
		txtNome.setText(null);
		txtFone.setText(null);
		txtEndereco.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCep.setText(null);
		txtNumero.setText(null);
		txtCidade.setText(null);
		txtBairro.setText(null);
		cboUf.setSelectedItem("");
		btnAdd.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		scrollPaneNome.setVisible(false);

	}
	

	private void Add() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Telefone do Cliente");
			txtFone.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cep do Cliente");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereço do Cliente");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero do Cliente");
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro do Cliente");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cidade do Cliente");
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o UF do Cliente");
			cboUf.requestFocus();
		} else {
			String create = "insert into clientes (nome,fone,cep,endereco,numero,complemento,bairro,cidade,uf) values (?,?,?,?,?,?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente adicionado");
				Limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	private void Editar() {
		if (txtNome.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o nome do Cliente");
			txtNome.requestFocus();
		} else if (txtFone.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Telefone do Cliente");
			txtFone.requestFocus();
		} else if (txtCep.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cep do Cliente");
			txtCep.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Endereço do Cliente");
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Numero do Cliente");
			txtNumero.requestFocus();

		} else if (txtComplemento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Complemento Cliente");
			txtComplemento.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Bairro do Cliente");
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Cidade do Cliente");
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Insira o UF do Cliente");
			cboUf.requestFocus();
		} else {
			String update = "update clientes set nome=?,fone=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idcli=?";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(update);
				pst.setString(1, txtNome.getText());
				pst.setString(2, txtFone.getText());
				pst.setString(3, txtCep.getText());
				pst.setString(4, txtEndereco.getText());
				pst.setString(5, txtNumero.getText());
				pst.setString(6, txtComplemento.getText());
				pst.setString(7, txtBairro.getText());
				pst.setString(8, txtCidade.getText());
				pst.setString(9, cboUf.getSelectedItem().toString());
				pst.setString(10, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dados do cliente editados com sucesso!");
				Limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		}
	}

	private void Excluir() {
		int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirmar == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from clientes where idcli=?";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				Limpar();
				JOptionPane.showMessageDialog(null, "Cliente Excluído");
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException e1) {
				JOptionPane.showMessageDialog(null, "Não foi Possível Excluir o Cliente!\nHá um Serviço Pendente.");
			} catch (Exception e) {
				System.out.println(e);
			}
			

		}
	}

	private void BuscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}

				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						System.out.println("OK");

					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}

			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
}// fim do código
