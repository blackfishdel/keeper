package com.del.keeper.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 拼接多张图片工具
 *
 * @author xie
 */
public class JoinTwoImageUtil {

	private static Logger log = LoggerFactory.getLogger(JoinTwoImageUtil.class);

	/**
	 * @param imgs    图片地址数组
	 * @param type    图片类型
	 * @param dst_pic 新文件地址
	 * @return
	 */
	public static boolean merge(String[] imgs, String type, String dst_pic) {
		// 获取需要拼接的图片长度
		int len = imgs.length;
		// 判断长度是否大于0
		if (len < 1) {
			return false;
		}
		File[] src = new File[len];
		BufferedImage[] images = new BufferedImage[len];
		int[][] ImageArrays = new int[len][];
		for (int i = 0; i < len; i++) {
			try {
				src[i] = new File(imgs[i]);
				images[i] = ImageIO.read(src[i]);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			// 从图片中读取RGB 像素
			ImageArrays[i] = new int[width * height];
			ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0,
							width);
		}

		int dst_height = 0;
		int dst_width = images[0].getWidth();
		// 合成图片像素
		for (int i = 0; i < images.length; i++) {
			dst_width = dst_width > images[i].getWidth() ? dst_width
							: images[i].getWidth();
			dst_height += images[i].getHeight();
		}
		// 合成后的图片
		log.debug("宽度:" + dst_width);
		log.debug("高度:" + dst_height);
		if (dst_height < 1) {
			log.info("dst_height < 1");
			return false;
		}
		// 生成新图片
		try {
			// dst_width = images[0].getWidth();
			BufferedImage ImageNew = new BufferedImage(dst_width, dst_height,
							BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			for (int i = 0; i < images.length; i++) {
				ImageNew.setRGB(0, height_i, dst_width, images[i].getHeight(),
								ImageArrays[i], 0, dst_width);
				height_i += images[i].getHeight();
			}

			//1.创建空白图片
			BufferedImage newBufferedImage = new BufferedImage(ImageNew.getWidth(),
							ImageNew.getHeight(), BufferedImage.TYPE_INT_RGB);
			//2.把原图转化为RBG图像，24位深度的jpg
			newBufferedImage.createGraphics().drawImage(ImageNew, 0, 0, Color.WHITE, null);

			File outFile = new File(dst_pic);
			ImageIO.write(newBufferedImage, "jpg", outFile);// 写图片 ，输出到硬盘
		} catch (Exception e) {
			log.warn(e.getMessage(), e);
			return false;
		}
		return true;
	}

//	public static void main(String[] args) {
//		merge(new String[]{"D:\\honc\\idcard\\1.jpg", "D:\\honc\\idcard\\2.jpg"}, "jpg", "D:\\honc\\idcard\\3.jpg");
//	}
}
