package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class sobre extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5512666712820487201L;
	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			sobre dialog = new sobre();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public sobre() {
		setTitle("Sistema da Mecânica CarMech");
		setIconImage(Toolkit.getDefaultToolkit().getImage(sobre.class.getResource("/img/pngegg.png")));
		setBounds(100, 100, 489, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mecânica de Carro");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(143, 11, 242, 30);
		contentPanel.add(lblNewLabel);
		{
			JLabel lblNewLabel_1 = new JLabel("Autor : Gustavo Cavalcante Moreira");
			lblNewLabel_1.setBounds(130, 140, 264, 14);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_2 = new JLabel("Sob a Licença MIT");
			lblNewLabel_2.setBounds(365, 163, 98, 14);
			contentPanel.add(lblNewLabel_2);
		}
		{
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setIcon(new ImageIcon(sobre.class.getResource("/img/mit-icon.png")));
			lblNewLabel_3.setBounds(345, 179, 128, 128);
			contentPanel.add(lblNewLabel_3);
		}
		
		JLabel lblNewLabel_4 = new JLabel("Mecânica CarMech");
		lblNewLabel_4.setBounds(168, 41, 128, 14);
		contentPanel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(sobre.class.getResource("/img/pngegg (1).png")));
		lblNewLabel_5.setBounds(109, 26, 211, 103);
		contentPanel.add(lblNewLabel_5);
		{
			JLabel lblNewLabel_6 = new JLabel("Redes Sociais");
			lblNewLabel_6.setFont(new Font("Tw Cen MT", Font.ITALIC, 20));
			lblNewLabel_6.setBounds(33, 199, 121, 14);
			contentPanel.add(lblNewLabel_6);
		}
		{
			JLabel lblNewLabel_7 = new JLabel("carmech_oficial");
			lblNewLabel_7.setIcon(new ImageIcon(sobre.class.getResource("/img/3228551_app_b_w_instagram_logo_media_icon (1).png")));
			lblNewLabel_7.setBounds(33, 199, 115, 70);
			contentPanel.add(lblNewLabel_7);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(null);
			{
				JButton okButton = new JButton("OK");
				okButton.setBounds(312, 5, 47, 23);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(364, 5, 65, 23);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
