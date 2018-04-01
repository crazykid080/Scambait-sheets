package consoleAPI.crazykid080.main.adminControls;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


@SuppressWarnings("serial")
public class AdminConsole extends JFrame {
	private String consoleOut = "";
	private String consoleIn = "";
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JTextField field1 = new JTextField();
	private JButton buttonDone = new JButton();
	private JButton buttonClear = new JButton();
	private JTextArea textArea1 = new JTextArea();
	/**
	 * @author crazykid080
	 * @since 0.0.1
	 * This is how you create a new console.
	 * 
	 */
	public AdminConsole(){

		this.setTitle("Admin Console");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);


		buttonDone = new JButton("Done");
		buttonClear = new JButton("Clear");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		Dimension test = new Dimension(width - 250, height - 250);


		buttonDone.addActionListener(e -> buttonDoneClick());

		buttonClear.addActionListener(e -> buttonClearClick());

		
		panel1.add(textArea1);

		panel2.add(field1);
		panel2.add(buttonClear);
		panel2.add(buttonDone);

		Dimension field = new Dimension((int) (screenSize.getWidth() / 1.5), 25);

		KeyListener keyListener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent keyEvent) {
				//keyPressAction(keyEvent);
			}
			@Override
			public void keyReleased(KeyEvent keyEvent) {
				keyPressAction(keyEvent);
			}
			@Override
			public void keyTyped(KeyEvent keyEvent) {
				//keyPressAction(keyEvent);
			}
		};

		field1.setPreferredSize(field);
		field1.setFocusable(true);
		field1.addKeyListener(keyListener);

		Dimension area = new Dimension((int) (screenSize.getWidth() / 1.5), (int) (screenSize.getHeight() / 1.5));
		textArea1.setPreferredSize(area);
		textArea1.setMinimumSize(area);
		textArea1.setMaximumSize(screenSize);
		textArea1.setLineWrap(true);
		textArea1.setEditable(false);

		this.add(panel1, BorderLayout.NORTH);
		panel1.setVisible(true);

		this.add(panel2, BorderLayout.SOUTH);
		panel2.setVisible(true);

		//Dimension d = new Dimension(400, 400);
		this.setPreferredSize(test);

		ConsoleControl.registerConsole(this);

		this.pack();
		ConsoleControl.write("Console initialized.", LogLevels.Info);
	}

	private void buttonClearClick() {
		field1.setText("");
		textArea1.setText("");
	}
	private void buttonDoneClick() {
		consoleIn = field1.getText();
		write(consoleIn);
		AdminCommandController.runCommand(consoleIn);
		field1.setText("");
	}

	//Externally called methods under here.

	public void write(String text){
		consoleOut = textArea1.getText() + text + "\n";
		textArea1.setText(consoleOut);
	}

	private void keyPressAction(KeyEvent key){
		if(key.getKeyChar() == KeyEvent.VK_ENTER){
			buttonDoneClick();
		}
	}

	/**
	 * @author Crazykid080
	 * @param text The message you wish to send
	 * @param type The alert level the message will be.
	 */
	public void write(String text, LogLevels type){
		String level = "ISSUE!!!";
		switch(type){
		case Info:
			level = "[Info]";
			break;
		case CRITICAL:
			level = "[***CRITICAL***]";
			break;
		case Error:
			level = "[*Error*]";
			break;
		case Warning:
			level = "[Warning!]";
			break;
		default:
			level = "ISSUE!!!";
			break;
		}
		consoleOut = textArea1.getText() + level + " " + text + "\n";
		textArea1.setText(consoleOut);
	}
}
