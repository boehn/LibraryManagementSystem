package program;

import java.awt.EventQueue;
import java.awt.FontFormatException;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import view.login.BaseLoginScreen;
import view.login.SignInPage;

public class Main {

	public static void main(String[] args) throws IOException, FontFormatException {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
						if ("Nimbus".equals(info.getName())) {
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException ex) {
					System.err.println("Error setting look and feel: " + ex.getMessage());
				}

				BaseLoginScreen frame = null;
				try {
					frame = new SignInPage();
				} catch (IOException | FontFormatException e) {
					e.printStackTrace();
				}
				frame.setVisible(true);
			}
		});
	}
}
