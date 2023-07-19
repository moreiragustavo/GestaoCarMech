package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import model.DAO;
import utils.Validador;

import javax.swing.border.TitledBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.Toolkit;

public class servicos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	DAO dao = new DAO();
	private Connection con;
	private PreparedStatement pst;
	private ResultSet rs;
	private JTextField txtOS;
	private JTextField txtData;
	private JTextField txtVeiculo;
	private JTextField txtProblema;
	private JTextField txtValor;
	private JTextField txtID;
	private JButton btnBuscar;
	private JButton btnAdd;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnLimpar;
	private JTextField txtCliente;
	private JList listaCliente;
	private JScrollPane scrollPane;
	private JButton btnOS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			servicos dialog = new servicos();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public servicos() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(servicos.class.getResource("/img/pngegg.png")));
		setTitle("Ordem de Serviço");
		setBounds(100, 100, 642, 383);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("OS");
		lblNewLabel.setBounds(23, 22, 46, 14);
		contentPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(23, 70, 46, 14);
		contentPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Veículo");
		lblNewLabel_2.setBounds(23, 127, 79, 14);
		contentPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Problema");
		lblNewLabel_3.setBounds(23, 187, 79, 14);
		contentPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_5 = new JLabel("Valor");
		lblNewLabel_5.setBounds(23, 245, 46, 14);
		contentPanel.add(lblNewLabel_5);
		
		btnAdd = new JButton("");
		btnAdd.setBorder(null);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdd.setIcon(new ImageIcon(servicos.class.getResource("/img/addblackcircularbutton_104741 (1).png")));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add();
			}
		});
		btnAdd.setBounds(89, 270, 89, 63);
		contentPanel.add(btnAdd);
		
		btnEditar = new JButton("");
		btnEditar.setBorder(null);
		btnEditar.setIcon(new ImageIcon(servicos.class.getResource("/img/pencileditblackcircularbuttoninterfacesymbol_104762 (4).png")));
		btnEditar.setContentAreaFilled(false);
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Editar();
			}
		});
		btnEditar.setBounds(205, 270, 89, 63);
		contentPanel.add(btnEditar);
		
		btnExcluir = new JButton("");
		btnExcluir.setIcon(new ImageIcon(servicos.class.getResource("/img/crossdeleteblackcircularbutton_79721.png")));
		btnExcluir.setBorder(null);
		btnExcluir.setContentAreaFilled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Excluir();
			}
		});
		btnExcluir.setBounds(316, 270, 89, 63);
		contentPanel.add(btnExcluir);
		
		txtOS = new JTextField();
		txtOS.setEditable(false);
		txtOS.setBounds(52, 19, 50, 20);
		contentPanel.add(txtOS);
		txtOS.setColumns(10);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(62, 67, 159, 20);
		contentPanel.add(txtData);
		txtData.setColumns(10);
		
		txtVeiculo = new JTextField();
		txtVeiculo.setBounds(108, 124, 134, 20);
		contentPanel.add(txtVeiculo);
		txtVeiculo.setColumns(10);
		txtVeiculo.setDocument(new Validador(30));
		
		txtProblema = new JTextField();
		txtProblema.setBounds(95, 184, 185, 20);
		contentPanel.add(txtProblema);
		txtProblema.setColumns(10);
		
		txtValor = new JTextField();
		txtValor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String caracteres = "0123456789.";

				if (!caracteres.contains(e.getKeyChar() + "")) {

					e.consume();
					
				}
			}
		});
		txtValor.setBounds(79, 242, 86, 20);
		contentPanel.add(txtValor);
		txtValor.setColumns(10);
		txtValor.setDocument(new Validador(10));
		
		btnBuscar = new JButton("");
		btnBuscar.setBorder(null);
		btnBuscar.setIcon(new ImageIcon(servicos.class.getResource("/img/pngwing.com (4).png")));
		btnBuscar.setContentAreaFilled(false);
		btnBuscar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(177, 11, 89, 45);
		contentPanel.add(btnBuscar);
		
		btnLimpar = new JButton("");
		btnLimpar.setIcon(new ImageIcon(servicos.class.getResource("/img/pngegg (3).png")));
		btnLimpar.setBorder(null);
		btnLimpar.setContentAreaFilled(false);
		btnLimpar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Limpar();
			}
		});
		btnLimpar.setBounds(436, 270, 89, 63);
		contentPanel.add(btnLimpar);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEFT, TitledBorder.TOP, null, null));
		panel.setBounds(333, 22, 270, 119);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		txtCliente = new JTextField();
		txtCliente.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtCliente.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				ListarCliente();
			}
		});
		txtCliente.setBounds(10, 21, 250, 20);
		panel.add(txtCliente);
		txtCliente.setColumns(10);
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setBounds(47, 91, 86, 20);
		panel.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("ID");
		lblNewLabel_4.setBounds(10, 94, 46, 14);
		panel.add(lblNewLabel_4);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 38, 250, 50);
		panel.add(scrollPane);
		scrollPane.setVisible(false);
		scrollPane.setBorder(null);
		
		listaCliente = new JList();
		scrollPane.setViewportView(listaCliente);
		listaCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BuscarNomeCliente();
			}
		});
		listaCliente.setBorder(null);
		
		btnOS = new JButton("OS");
		btnOS.setAutoscrolls(true);
		btnOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				imprimirOS();
				
			}
		});
		btnOS.setBounds(409, 200, 89, 23);
		contentPanel.add(btnOS);
	}
	
	private void ListarCliente() {
		DefaultListModel<String> modelo = new DefaultListModel<>();
		listaCliente.setModel(modelo);

		String readlistaCliente = "select * from clientes where nome like '" + txtCliente.getText() + "%'" + "order by nome";
		try {
			con = dao.conectar();
			pst = con.prepareStatement(readlistaCliente);
			rs = pst.executeQuery();
			while (rs.next()) {
				scrollPane.setVisible(true);
				modelo.addElement(rs.getString(2));
				if (txtCliente.getText().isEmpty()) {
					scrollPane.setVisible(false);
				}
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}
	private void BuscarNomeCliente() {
		int linha = listaCliente.getSelectedIndex();
		if (linha >= 0) {
			String readlistaServico = "select * from clientes where nome like '" + txtCliente.getText() + "%'"
					+ "order by nome limit " + (linha) + " , 1";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(readlistaServico);
				rs = pst.executeQuery();
				if (rs.next()) {
					scrollPane.setVisible(false);
					txtID.setText(rs.getString(1));
					
				} else {
					JOptionPane.showMessageDialog(null, "Cliente Inexistente");
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} else {
			scrollPane.setVisible(false);
		}
	}
	private void buscar() {
		//captura do número da OS sem usar a Caixa de Texto
		String numOS = JOptionPane.showInputDialog("Número da OS");

		String read = "select * from servicos where os=?";
		try {
			con = dao.conectar();
			
			pst = con.prepareStatement(read);
			
			pst.setString(1, numOS);
			rs = pst.executeQuery();
			if (rs.next()) {
				txtOS.setText(rs.getString(1));
				txtData.setText(rs.getString(2));
				txtVeiculo.setText(rs.getString(3));
				txtProblema.setText(rs.getString(4));
				txtValor.setText(rs.getString(5));
				txtID.setText(rs.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "Serviço Inexistente");
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void Add() {
		
		if (txtVeiculo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Veículo do Cliente");
			txtVeiculo.requestFocus();
		} else if (txtProblema.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Problema do Veículo do Cliente");
			txtProblema.requestFocus();
		} else if (txtValor.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Insira o Valor do Serviço");
			txtValor.requestFocus();
		} else {
			String create = "insert into servicos (veiculo,problema,valor,idcli) values (?,?,?,?)";
			try {
				con = dao.conectar();
				pst = con.prepareStatement(create);
				pst.setString(1, txtVeiculo.getText());
				pst.setString(2, txtProblema.getText());
				pst.setString(3, txtValor.getText());
				pst.setString(4, txtID.getText());
				pst.executeUpdate();
				JOptionPane.showMessageDialog(null, "Serviço Registrado");
				Limpar();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
	private void Limpar() {
		txtID.setText(null);
		txtOS.setText(null);
		txtData.setText(null);
		txtVeiculo.setText(null);
		txtProblema.setText(null);
		txtValor.setText(null);
		txtCliente.setText(null);
		
	
	}
	
	private void Excluir() {
		int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste Serviço?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirmar == JOptionPane.YES_OPTION) {
			// CRUD - Delete
			String delete = "delete from servicos where idcli=?";

			try {
				con = dao.conectar();
				pst = con.prepareStatement(delete);
				pst.setString(1, txtID.getText());
				pst.executeUpdate();
				Limpar();
				JOptionPane.showMessageDialog(null, "Serviço Excluído");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
		private void Editar() {
			if (txtOS.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha o OS do Cliente");
				txtOS.requestFocus();
			} else if (txtData.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insira a Data do Serviço");
				txtData.requestFocus();
			} else if (txtVeiculo.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insira o Veículo do Cliente");
				txtVeiculo.requestFocus();
			} else if (txtProblema.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insira o Problema do Veículo do Cliente");
				txtProblema.requestFocus();
			} else if (txtValor.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Insira o Valor do Serviço");
				txtValor.requestFocus();
			} else {
				String update = "update servicos set os=?,dataOS=?,veiculo=?,problema=?,valor=? where os=?";
				try {
					con = dao.conectar();
					pst = con.prepareStatement(update);
					pst.setString(1, txtOS.getText());
					pst.setString(2, txtData.getText());
					pst.setString(3, txtVeiculo.getText());
					pst.setString(4, txtProblema.getText());
					pst.setString(5, txtValor.getText());
					pst.setString(6, txtID.getText());
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Dados do Serviço editados com sucesso!");
					Limpar();
					con.close();
				} catch (Exception e) {
					System.out.println(e);
				}

			}
			
			}
			
			/**
			 * Impressão da OS
			 */
			private void imprimirOS() {
				// instanciar objeto para usar os métodos da biblioteca
				Document document = new Document();
				// documento pdf
				try {
					// criar um documento em branco (pdf) de nome clientes.pdf
					PdfWriter.getInstance(document, new FileOutputStream("os.pdf"));
					// abrir o documento (formatar e inserir o conteúdo)
					document.open();
					String readOS = "select * from servicos where os=?";
					// conexão com o banco
					try {
						// abrir a conexão
						con = dao.conectar();
						// preparar a execução da query (instrução sql)
						pst = con.prepareStatement(readOS);
						pst.setString(1, txtOS.getText());
						// executar a query
						rs = pst.executeQuery();
						// se existir a OS
						if (rs.next()) {					
							//document.add(new Paragraph("OS: " + rs.getString(1)));
							Paragraph os = new Paragraph ("OS: " + rs.getString(1));
							os.setAlignment(Element.ALIGN_RIGHT);
							document.add(os);
								
							Paragraph veiculo = new Paragraph ("Veículo: " + rs.getString(3));
							veiculo.setAlignment(Element.ALIGN_LEFT);
							document.add(veiculo);
							
							Paragraph problema = new Paragraph ("Problema: " + rs.getString(4));
							problema.setAlignment(Element.ALIGN_LEFT);
							document.add(problema);
							
							Paragraph valor = new Paragraph ("Valor R$: " + rs.getString(5));
							valor.setAlignment(Element.ALIGN_LEFT);
							document.add(valor);
							
							Paragraph dataOS = new Paragraph ("DataOS: " + rs.getString(2));
							dataOS.setAlignment(Element.ALIGN_LEFT);
							document.add(dataOS);
						
							//imprimir imagens
							Image imagem = Image.getInstance(servicos.class.getResource("/img/range.jpg"));
							imagem.scaleToFit(500,350);
							//(x,y)
							imagem.setAbsolutePosition(10, 330);
							document.add(imagem);					
						}
						// fechar a conexão com o banco
						con.close();
						} catch (Exception e) {
							System.out.println(e);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				// fechar o documento (pronto para "impressão" (exibir o pdf))
				document.close();
				// Abrir o desktop do sistema operacional e usar o leitor padrão
				// de pdf para exibir o documento
				try {
					Desktop.getDesktop().open(new File("os.pdf"));
				} catch (Exception e) {
				}
	}
}
