package graphics.shapes.ui.menu.project_manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;

import javax.swing.JOptionPane;

import graphics.shapes.SCollection;
import graphics.shapes.Shape;
import graphics.shapes.ui.ShapesView;

public class ProjectSaver {

	private ShapesView sview;
	
	public ProjectSaver(ShapesView sview) {
		this.sview = sview;
	}
	
	public void save(File fileToSave) {
		SCollection model = (SCollection) this.sview.getModel();
		Iterator<Shape> iterator = model.iterator();
		
		try(BufferedWriter ow = new BufferedWriter(Files.newBufferedWriter(fileToSave.toPath()))){
			
			/* write sview settings */
			ow.write(String.valueOf(this.sview.getWidth()) + " " + String.valueOf(this.sview.getHeight()));
			ow.newLine();
			
			/* write all shapes settings */
			Shape s;
			while(iterator.hasNext()) {
				s = iterator.next();
				ow.write(s.toString());
				ow.newLine();
			}
			
			// flush + close
			ow.close();
		}
		catch(IOException e) {
			System.err.println("Error on write: " + e.getMessage());
			JOptionPane.showMessageDialog(null, "Unable to save into specified file.", "IO Error", JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
