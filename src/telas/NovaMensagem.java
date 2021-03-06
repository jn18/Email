package telas;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import CadastroUser.Cadastro;
import Serializador.Serializador;
import Serializador.SerializadorUser;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;

import mensagem.Cliente;

import javax.swing.JTextArea;

public class NovaMensagem extends JPanel {
	private JTextField txtRemetente;
	private JTextField txtDestinatario;
	private JTextField txtTitulo;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings("deprecation")
	public NovaMensagem(final TelaPrincipal tp) {
		setLayout(null);
		
		JLabel lblNovaMensagem = new JLabel("Nova Mensagem");
		lblNovaMensagem.setBounds(193, 4, 175, 26);
		lblNovaMensagem.setFont(new Font("Arial Black", Font.BOLD, 18));
		add(lblNovaMensagem);
		
		txtRemetente = new JTextField();
		txtRemetente.setBounds(10, 66, 191, 20);
		txtRemetente.setText(tp.getUsuarioLogado());
		txtRemetente.enable(false);
		add(txtRemetente);
		txtRemetente.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Remetente:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 41, 73, 14);
		add(lblNewLabel);
		
		JLabel lblDestinatrio = new JLabel("Destinat\u00E1rio:");
		lblDestinatrio.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDestinatrio.setBounds(233, 41, 73, 14);
		add(lblDestinatrio);
		
		txtDestinatario = new JTextField();
		txtDestinatario.setBounds(230, 66, 210, 20);
		add(txtDestinatario);
		txtDestinatario.setColumns(10);
		
		JLabel lblMensagem = new JLabel("Mensagem:");
		lblMensagem.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMensagem.setBounds(10, 153, 73, 14);
		add(lblMensagem);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(482, 328, 89, 23);
		add(btnCancelar);
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setBounds(383, 328, 89, 23);
		add(btnEnviar);
		
		JLabel lblTtulo = new JLabel("T\u00EDtulo");
		lblTtulo.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTtulo.setBounds(10, 97, 46, 14);
		add(lblTtulo);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(10, 122, 302, 20);
		add(txtTitulo);
		txtTitulo.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 189, 569, 128);
		add(textArea);
		
		btnCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tp.getCancelarNovaMensagem();
			}
		});

		btnEnviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String remetente, destinatario, corpo, titulo, dominio;
				
				remetente = txtRemetente.getText();
				destinatario = txtDestinatario.getText();
				corpo = textArea.getText();
				titulo = txtTitulo.getText();
				
				String retorno = "";
				
				if (!destinatario.isEmpty() && !corpo.isEmpty() && !titulo.isEmpty())
				{
					
					int status = 0;
					
					String splitEmailDest [] = destinatario.split("@");
					
					if (splitEmailDest[1].equals("apocalipse"))
					{
						dominio = "apocalipse";
						
						ArrayList<Cadastro> cadast = new ArrayList<Cadastro>();
						
						Cliente c1 = new Cliente();
						try {
							cadast = c1.carregarUsers(dominio);
						} catch (ClassNotFoundException | IOException
								| InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						for (int i=0; i< cadast.size(); i++)
						{
							if (destinatario.equals(cadast.get(i).getEmail()))
							{
								try {
									Cliente c = new Cliente();
									retorno = c.cadastrarEmail(remetente, destinatario, corpo, titulo, dominio);
									status = 1;
									tp.getCancelarCaixaEntrada();
									break;
								} catch (ClassNotFoundException | IOException
										| InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					else
					{
						dominio = "ikinho";
						
						ArrayList<Cadastro> cadast = new ArrayList<Cadastro>();
						
						Cliente c1 = new Cliente();
						try {
							cadast = c1.carregarUsers(dominio);
						} catch (ClassNotFoundException | IOException
								| InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						for (int i=0; i< cadast.size(); i++)
						{
							if (destinatario.equals(cadast.get(i).getEmail()))
							{
								try {
									Cliente c = new Cliente();
									retorno = c.cadastrarEmail(remetente, destinatario, corpo, titulo, dominio);
									status = 1;
									tp.getCancelarCaixaEntrada();
									break;
								} catch (ClassNotFoundException | IOException
										| InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
					}
					
					if (status == 0)
						JOptionPane.showMessageDialog(null, "Destinatario Desconhecido, forne�a um destinat�rio v�lido!");
					else{
						if(retorno.equals("Ok"))
							JOptionPane.showMessageDialog(null, "Mensagem enviada com sucesso!");
						else
							JOptionPane.showMessageDialog(null, "Mensagem j� existe!");
					}
				}
				else
					JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente!");
			}
		});
		
	}

}
