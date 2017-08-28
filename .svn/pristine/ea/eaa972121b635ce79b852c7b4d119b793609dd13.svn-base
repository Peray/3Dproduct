package com.eastdawn.util;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;


public class ImgAction {
	//生成缩略图和给图上打上公司logo
	public static void main(String[] args) throws IOException{
		
	//源文件，必须是有文件
	String pathS="D:/tp/1.png";
	
	//目标文件
	String pathD="D:/ztp/1.png";
	//拷贝文件
	copyfile(pathS,pathD);
	//----------------------------------------------
	//生成缩小mimi图
    File stadimgfile2 = new File(pathD);
	//图片缓存
	BufferedImage img2 = ImageIO.read(stadimgfile2);
	//得到图片的宽和高
	double width = img2.getWidth();
	double height = img2.getHeight();
	int miniwidth = 120;//缩略图宽度
	int miniheight = 90;//缩略图高度
	double ratew = miniwidth / width;
	double rateh = miniheight / height;
	//获得适合的缩放比率，即以在规定缩略尺寸中完整显示图片内容的同时又保证最大的缩放比率
	double rate = Math.min(ratew, rateh);
	rate = (Math.rint((rate * 100) + 0.5)) / 100;
	BufferedImage imgmini = new java.awt.image.BufferedImage(miniwidth, miniheight,BufferedImage.TYPE_USHORT_565_RGB);

	Graphics2D gmini = imgmini.createGraphics();
	gmini.setBackground(Color.WHITE);
	gmini.clearRect(0, 0, miniwidth, miniheight);
	AffineTransform trans = new AffineTransform();
	trans.scale(rate, rate);
	AffineTransformOp op = new AffineTransformOp(trans, AffineTransformOp.TYPE_BILINEAR);
	gmini.drawImage(img2, op, (int) (miniwidth - (width * rate)) / 2, (int) (miniheight - (height * rate)) / 2);
	ImageIO.write(imgmini, "jpg", stadimgfile2);
	//---------------------------------------------------------
	//mimi图加水印
	BufferedImage img3 = ImageIO.read(stadimgfile2);
	int mimi_width2 = img3.getWidth();
	int mimi_height2 = img3.getHeight();
	BufferedImage imgmimi2 = new java.awt.image.BufferedImage(mimi_width2, mimi_height2,
	BufferedImage.TYPE_USHORT_565_RGB);
	//logo文件的位置，必须是真是的
	String logoFile = "D:/tdeng/My Pictures/logo.gif";
	BufferedImage watermark3 = ImageIO.read(new File(logoFile));
	Graphics2D gmimi2 = imgmimi2.createGraphics();
	gmimi2.drawImage(img3, null, 0, 0);
	gmimi2.drawImage(watermark3, null, mimi_width2 - watermark3.getWidth(), mimi_height2 - watermark3.getHeight());
	ImageIO.write(imgmimi2, "jpg", stadimgfile2);
	}
	//    使用FileInputStream和FileOuStream
	public static void copyfile(String pathSrc,String pathDest) throws IOException
	{
	FileInputStream fi=new FileInputStream(pathSrc);
	FileOutputStream fo=new FileOutputStream(pathDest);
	byte data[]=new byte[fi.available()];
	fi.read(data);
	fo.write(data);
	fi.close();
	fo.close();
	}
}
