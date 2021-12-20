import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.border.Border;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * Aplicacion para manejar todas las opciones de ventana de dialogos
 * de OptionPane
 */
public class VentanaDialogos {

	public static void main(String[] args) {
		Marco marco= new Marco();
		marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

class Marco extends JFrame{
	public Marco() {
		setTitle("Ventanas de dialogo");
		setBounds(100,100,600,500);
		add(new Panel());
		setVisible(true);
	}
}

class Panel extends JPanel{
	public Panel() {
		setLayout(new BorderLayout());
		panelNorte = new JPanel(new GridLayout(2,3));
		panelSur = new JPanel();//TODO revisar, no se si hace falta  este panel
		
		//Inicializacion grupoRB
		grupoRB1 = new ButtonGroup();
		grupoRB2 = new ButtonGroup();
		grupoRB3 = new ButtonGroup();
		grupoRB4 = new ButtonGroup();
		grupoRB5 = new ButtonGroup();
		grupoRB6 = new ButtonGroup();
		
		//Cajas
		Box CajaV1 = Box.createVerticalBox();
		Box CajaV2 = Box.createVerticalBox();
		Box CajaV3 = Box.createVerticalBox();
		Box CajaV4 = Box.createVerticalBox();
		Box CajaV5 = Box.createVerticalBox();
		Box CajaV6 = Box.createVerticalBox();
		
		add(panelNorte,BorderLayout.CENTER);
		add(panelSur,BorderLayout.SOUTH);//TODO revisar, no se si hace falta  este panel
		
		panelNorte.add(CajaV1);
		panelNorte.add(CajaV2);
		panelNorte.add(CajaV3);
		panelNorte.add(CajaV4);
		panelNorte.add(CajaV5);
		panelNorte.add(CajaV6);
		
		//Opciones iniciales marcadas en los RadioButton
		tipo="Mensaje";
		tipoMensaje=JOptionPane.ERROR_MESSAGE;
		mensaje="Cadena Mensaje de prueba";
		icono= new ImageIcon("src/icono.jpg");
		combo= new JComboBox(textoTipoRB);
		fecha = new Date();
		Object[] mensajeArray = {mensaje,icono,combo,fecha};
		
		for (int i = 0; i < textoTipoRB.length; i++) {
			CreaRadioButtons(textoTipoRB[i],CajaV1,grupoRB1,i);
		}
		
		for (int i = 0; i < textoTipoMensajeRB.length; i++) {
			CreaRadioButtons(textoTipoMensajeRB[i],CajaV2,grupoRB2,i);
		}
		
		for (int i = 0; i < textoMensajeRB.length; i++) {
			CreaRadioButtons(textoMensajeRB[i],CajaV3,grupoRB3,i);
		}
		
		for (int i = 0; i < textoConfirmarRB.length; i++) {
			CreaRadioButtons(textoConfirmarRB[i],CajaV4,grupoRB4,i);
		}
		for (int i = 0; i < textoOpcionRB.length; i++) {
			CreaRadioButtons(textoOpcionRB[i],CajaV5,grupoRB5,i);
		}
		for (int i = 0; i < textoEntradaRB.length; i++) {
			CreaRadioButtons(textoEntradaRB[i],CajaV6,grupoRB6,i);
		}

		
		JButton Aceptar = new JButton("Aceptar");
		Aceptar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (tipo) {
				//TODO Establecer opciones boton
				case "Mensaje":
					if(mensaje.equals(1) ) {
						JOptionPane.showMessageDialog(panelSur, mensajeArray, "Titulo: "+tituloCaja, tipoMensaje);
					} else JOptionPane.showMessageDialog(panelSur, mensaje, "Titulo: "+tituloCaja, tipoMensaje);
					break;
				case "Confirmar":
					JOptionPane.showConfirmDialog(panelSur, mensaje, "Titulo: "+tituloCaja, tipoMensaje);
					break;

				default:
					break;
				}
			}
			
		});
		panelSur.add(Aceptar);
	}
	
	public void CreaRadioButtons(String nombre,Box caja,ButtonGroup grupoRB, int i) {
		if(i==0) {
			caja.setBorder(BorderFactory.createTitledBorder(nombre));//Establecemos titulo Caja
			tituloCaja=nombre;
		}else {
			if(i==1) {
				botonRB = new JRadioButton(nombre,true);//Primer RadioButton marcado
			}else {
				botonRB = new JRadioButton(nombre,false);
			}
			grupoRB.add(botonRB);
			botonRB.addActionListener(new ActionListener() {
				//TODO Opciones RadioButtons
				@Override
				public void actionPerformed(ActionEvent e) {
					//seleccion de tipo
					for (int j = 0; j < textoTipoRB.length; j++) {
						if (textoTipoRB[j].equalsIgnoreCase(e.getActionCommand())){
							System.out.println(e.getActionCommand());
							tipo=e.getActionCommand();
						}
					}
					
					//Seleccion de tipo de mensaje
					for (int j = 0; j < textoTipoMensajeRB.length; j++) {
						if (textoTipoMensajeRB[j].equalsIgnoreCase(e.getActionCommand())){
							System.out.println("JOptionPane."+(e.getActionCommand()));
							tipoMensaje=valorTipoMensaje[j-1];
						}
					}
					
					//Seleccion de Mensaje
					for (int j = 0; j < textoMensajeRB.length; j++) {
						if (textoMensajeRB[j].equalsIgnoreCase(e.getActionCommand())){
							System.out.println(e.getActionCommand());
							switch (textoMensajeRB[j]) {
							
							case "Cadena":
								mensaje="Establecida opcion Cadena de texto de prueba";
								break;
							case "Icono":
								mensaje=icono;
								break;
							case "Componente":
								mensaje=combo;
								break;
							case "Otros":
								mensaje=fecha;
								break;
							case "Object[]":
								mensaje = 1;
								break;
								
							default:
								mensaje=e.getActionCommand();
								break;
							}
						}
					}
					
				}
				
			});
			caja.add(botonRB);
			grupoRB.add(botonRB);
			caja.add(botonRB);
		}
	}
	
	//Paneles
	JPanel panelNorte;
	JPanel panelSur;
	
	ButtonGroup grupoRB1;
	ButtonGroup grupoRB2;
	ButtonGroup grupoRB3;
	ButtonGroup grupoRB4;
	ButtonGroup grupoRB5;
	ButtonGroup grupoRB6;
	//Grupo RadioButton
	private JRadioButton botonRB;
	//Menus RadioButtons
	private String [] textoTipoRB  = {"Tipo","Mensaje","Confirmar","Opcion","Entrada"};
	private String [] textoTipoMensajeRB  = {"Tipo Mensaje","ERROR_MESSAGE","INFORMATION_MESSAGE","WARNING_MESSAGE","QUESTION_MESSAGE","PLAIN_MESSAGE"};
	private int [] valorTipoMensaje  = {0,1,2,3,-1};
	private String [] textoMensajeRB  = {"Mensaje","Cadena","Icono","Componente","Otros","Object[]"};
	private String [] textoConfirmarRB  = {"Confirmar","DEFAULT_OPTION","YES_NO_OPTION","YES_NO_CANCEL_OPTION","OK_CANCEL_OPTION"};
	private String [] textoOpcionRB  = {"Opcion","String[]","Icon[]","Object[]"};
	private String [] textoEntradaRB  = {"Entrada","Campo de texto","Combo"};
		
	private String tituloCaja;
	private String tipo;
	private int tipoMensaje;
	private Object mensaje;
	private ImageIcon icono;
	private JComboBox combo;
	private Date fecha;
	private Object[] mensajeArray;
	
}
