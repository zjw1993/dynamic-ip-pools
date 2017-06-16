package com.weiniu.image;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import net.coobird.thumbnailator.Thumbnails;


public class Test {

	public static void main(String[] args) throws MalformedURLException, IOException {
		Thumbnails.of(new URL("http://118.89.150.235:9999/a.jpg"))
			.size(100, 100)
			.outputQuality(0.1f)
			.toFile("E:\\test\\resize1.jpg");
	}
	
}
