package com.zdd.web.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
public class ImageTest {

	@RequestMapping("/imageio")
	@ResponseBody
	public String imageiotest() throws IOException{
		JSONObject json=new JSONObject();
		File file=new File("F:\\cuttest\\3.jpg");
		ImageIO.scanForPlugins();
		BufferedImage image = ImageIO.read(file);
		System.out.println(image.getColorModel());
	    OutputStream os = null;  
	    os = new FileOutputStream("F:\\cuttest\\4.jpg");  
	    ImageIO.write(image, "jpg", os);  
	    File filenew=new File("F:\\cuttest\\4.jpg");
	    BufferedImage imagenew=ImageIO.read(filenew);
	    System.out.println(imagenew.getColorModel());
		json.put("width", image.getWidth());
		json.put("height", image.getHeight());
		json.put("widthnew", imagenew.getWidth());
		json.put("heightnew", imagenew.getHeight());
		return json.toString();
	}
}
