package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui extends JFrame {
	final JFileChooser fc = new JFileChooser();
	private final byte r = 0, g = 1, b = 2, a = 3;
	JButton openButton = new JButton("Open file");
	JButton saveButton = new JButton("Save compressed file");
	JButton decomButton = new JButton("Save decompressed file");
	JLabel fileName = new JLabel("No file!");
	
	Gui(ImageReader ir){
		this.setBounds(10, 10, 200, 200);
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		fileName.setPreferredSize(new Dimension(130, 30));
		fileName.setHorizontalAlignment(JLabel.CENTER);
		add(openButton);
		add(fileName);
		add(saveButton);
		add(decomButton);
		fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fc.setFileFilter(new FileNameExtensionFilter("Bitmap picture files","bmp","bmp"));
		fc.setFileFilter(new FileNameExtensionFilter("Bitmap compressed picture files","bmpc","bmpc"));
		openButton.addActionListener(e -> {
			fc.setAcceptAllFileFilterUsed(false);
			int returnValue = fc.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				ir.file = fc.getSelectedFile();
				fileName.setText(fc.getSelectedFile().getName());
			} else {
				System.out.println("lag");
			}
		});
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fc.setAcceptAllFileFilterUsed(true);
				int returnValue = fc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					ir.saveFile(fc.getSelectedFile().toString()+".bmpc", true);
				} else {
					System.out.println("lag");
				}
			}
		});
		decomButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				fc.setAcceptAllFileFilterUsed(false);
				int returnValue = fc.showSaveDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					ir.saveFile(fc.getSelectedFile().toString()+".bmp", false);
				} else {
					System.out.println("lag");
				}
			}
		});
	}
}
