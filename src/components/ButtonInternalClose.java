package components;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import views.base.BaseInternalView;

public class ButtonInternalClose {
	
	private JButton button;

	public ButtonInternalClose() {
		button = new JButton("Close");
	}

	public JButton getButton() {
		return button;
	}

	public void addListener(BaseInternalView view) {
		button.addActionListener(
				new AbstractAction() {
					private static final long serialVersionUID = 1L;
					
					@Override
					public void actionPerformed(ActionEvent e) {
						view.dispose();
					}
				}
			);
	}
	
}
