/**
 * 
 */
package org.intuition_engine.ai.intuition.imageprocessing;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * @author bhagvan.kommadi
 *
 */
public class SubImageRecognizer {

	private List<String> baseURLs;
	
	public void init(List<String> baseURLs)
	{
	  this.baseURLs = baseURLs;	
	}
	
	public double compareImages(BufferedImage image, BufferedImage baseImage)
	{
		double variation = 0.0;
		
		for(int x=0; x < image.getWidth(); x++)
		{
			for(int y=0; y < image.getHeight(); y++)
			{
				variation += compareARGB(image.getRGB(x, y),baseImage.getRGB(x, y))/Math.sqrt(3);
				
			}
		}
		return variation/(image.getWidth()*image.getHeight());
	}
	
	public double compareARGB(int imageRGB, int baseImageRGB)
	{
		double imager1 = ((imageRGB >> 16) & 0xFF)/255.0;
		
		double imager2 = ((baseImageRGB >> 16) & 0xFF)/255.0;
		
		double imageg1 = ((imageRGB >> 8) & 0xFF)/255.0;
		
		double imageg2 = ((baseImageRGB >> 8) & 0xFF)/255.0;
		
		double imageb1 = (imageRGB & 0xFF)/255.0;
		
		double imageb2 = (baseImageRGB & 0xFF)/255.0;
		
		double imagea1 = ((imageRGB >> 24) & 0xFF)/255.0;
		
		double imagea2 = ((baseImageRGB >> 24) & 0xFF)/255.0;
		
		double argb = imagea1*imagea2*Math.sqrt(((imager1-imager2)*(imager1-imager2)) +((imageg1-imageg2)*(imageg1-imageg2))+((imageb1-imageb2)*(imageb1-imageb2)));
		
		return argb;
		
	}
	
	public void processImageRecognition(BufferedImage baseImage,BufferedImage image)
	{
		 int width = baseImage.getWidth();
		 int height = baseImage.getHeight();
		 
		 int imageWidth = image.getWidth();
		 int imageHeight = image.getHeight();
		 
		 System.out.println(imageWidth);
		 
		 if(width<=imageWidth && height <=imageHeight)
		 {
			 int x=0;
			 int y=0;
			 double diff = Double.POSITIVE_INFINITY;
			 
			 System.out.println(imageWidth-width);
			 System.out.println(imageHeight-height);
			 for(int x1=0; x1 < imageWidth-width; x1++)
			 {
				 for(int y1=0; y1 < imageHeight-height; y1++)
				 {
					 double comparison = compareImages(image.getSubimage(x1, y1, width, height),baseImage);
					 
					 if(comparison < diff)
					 {
						 x=x1;
						 y=y1;
						diff = comparison; 
					 }
					 //System.out.println(diff);
					 
					// System.out.println("x="+x1+" , "+"y="+y1);
				 }
			 }
			 
			 System.out.println(diff);
			 
			 System.out.println("x="+x+" , "+"y="+y);
		 }
		 
		 
	}
	
	public void recognize(List<String> urls)
	{
		for(String url: urls)
		{
			try
			{
			 URL imageURL = new URL(url);
			
			  BufferedImage image = ImageIO.read(imageURL);
			  
			}
			catch(Exception exception)
			{
				exception.printStackTrace();
			}
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		SubImageRecognizer recognizer = new SubImageRecognizer();
		 //BufferedImage baseImage = ImageIO.read(new URL("http://4.imimg.com/data4/UR/UX/MY-17997158/cricket-bat-250x250.jpg"));
		 
		 //BufferedImage image = ImageIO.read(new URL("http://shoptrics.com/image/data/ss/SS%20Premium%20English%20Willow%20Cricket%20Bat2.jpg"));
		 
		
		
		
		
		//List<String>
		
	    // recognizer.processImageRecognition(baseImage, image);	 
	
		 
	}

}
