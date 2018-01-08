package com.hynu.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public class ImageUtil {
	
	public static void setImageAdapte(Shell shell, Composite cmp, String filePath){
		ImageData newimage=null;
//		FileDialog fd=new FileDialog(shell, SWT.None);
//		String filepath=fd.open();
//		text_3.setText(filepath);
		try {
			InputStream iis=new FileInputStream(new File(filePath));
			ImageData ima=new ImageData(iis);
			newimage=ima.scaledTo(cmp.getBounds().width, cmp.getBounds().height);
			Image image=new Image(shell.getDisplay(), newimage);
			cmp.setBackgroundImage(image);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	
}
