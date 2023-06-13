package view.custom.components;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class PlaceholderTextField extends JTextField {

	private static final long serialVersionUID = 1L;
	private String placeholder;

	public PlaceholderTextField(String placeholder) {
		this.placeholder = placeholder;
		setBorder(BorderFactory.createLineBorder(Color.GRAY));
		addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (getText().equals(placeholder)) {
					setText("");
					setForeground(Color.BLACK);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (getText().isEmpty()) {
					setText(placeholder);
					setForeground(Color.GRAY);
				}
			}
		});
		setText(placeholder);
		setForeground(Color.GRAY);
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
		setText(placeholder);
	}
}
