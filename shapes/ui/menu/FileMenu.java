package graphics.shapes.ui.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu implements ActionListener {
	
	private static final String FILE = "File";
	private static final String OPEN = "open";
	private static final String SAVE = "save";
	private static final String QUIT = "quit";
	
	private JMenuItem open;
	private JMenuItem save;
	private JMenuItem quit;
	
	public FileMenu() {
		super(FILE);
		this.buildMenu();
	}
	
	private void buildMenu() {
		this.open = new JMenuItem(OPEN);
		this.save = new JMenuItem(SAVE);
		this.quit = new JMenuItem(QUIT);
		
		this.quit.addActionListener(this);
		
		this.add(this.open);
		this.add(this.save);
		this.addSeparator();
		this.add(this.quit);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(this.quit))
			System.exit(0);
	}

}
