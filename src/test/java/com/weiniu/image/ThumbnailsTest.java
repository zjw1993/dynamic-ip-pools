package com.weiniu.image;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.junit.Test;

public class ThumbnailsTest {

	private static final File SOURCEFILE = new File("E:\\test\\source.png");
	
	@Test  // 指定大小缩放
	public void testResize() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.size(100, 100)
			.toFile("E:\\test\\resize_w200_h100.jpg");
	}
	
	@Test  // 指定比例缩放
	public void testResizeScale() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.scale(0.5f)
//			.outputQuality(0.8f) // 输出质量
			.toFile("E:\\test\\resize_half.jpg");
	}

	@Test  // 裁剪中间500*500
	public void testCutCenter() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.sourceRegion(Positions.CENTER, 250, 250)
			.size(250, 250)
//			.scale(0.8f)
			.toFile("E:\\test\\cut_center_500w_500h.jpg");
	}
	
	@Test  // 裁剪底部中间500*500
	public void testCutBottomCenter() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.sourceRegion(Positions.BOTTOM_CENTER, 250, 250)
			.size(250, 250)
			.toFile("E:\\test\\cut_bottom_center_500w_500h.jpg");
	}
	
	@Test  // 裁剪底部中间500*500
	public void testCutPosition() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.sourceRegion(500, 100, 250, 250)
			.size(250, 250)
			.toFile("E:\\test\\cut_x500_y100_500w_500h.jpg");
	}
	
	@Test  // 旋转90度
	public void testRotate() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.rotate(90f)  // 旋转角度  整数：顺时针    负数 ： 逆时针
			.scale(1f)
			.toFile("E:\\test\\rotate_90.jpg");
	}
	
	@Test  // 旋转90度
	public void testWaterMark() throws MalformedURLException, IOException {
		Thumbnails.of(SOURCEFILE)
			.size(1000, 500)
			.watermark(Positions.BOTTOM_RIGHT,  
                ImageIO.read(new File("E:\\test\\mark.png")), 0.2f)
//            .outputQuality(0.8f)
            .toFile("E:\\test\\watermark.png");
	}
	
	
	
	
	
}
