package go.view.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class OutputPanel extends JScrollPane implements ActionListener {
	
	private JTextArea textArea;
	
	/**
	 * A Panel to output User Input for debugging purposes.
	 */
	public OutputPanel()
	{
		this.textArea = new JTextArea(20, 20);
		this.setViewportView(textArea);
		textArea.setEditable(false);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.append("MouseClick Event at X: " + e.getX() + " Y: " +
						e.getY() + " from " + e.getSource().getClass().toString() + "\n");
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		textArea.append("ActionEvent " + ((ActionEvent) e).getActionCommand() +
				" in " + getClass().toString() + "\n");
	}
	
}
