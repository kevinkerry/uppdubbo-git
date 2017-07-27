package com.csii.upp.paygate.action.qrcodepay;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.csii.ibs.action.ajax.AbstractDynamicMessageAction;
import com.csii.pe.core.Context;
import com.csii.upp.constant.CodeTypCd;
import com.csii.upp.dict.Dict;
import com.csii.upp.util.StringUtil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 生成二维码类
 * 
 * @author WY
 *
 */
public class CreatQRCodeAction extends AbstractDynamicMessageAction {
	private int imageWidth;
	private int imageHeight;
	private int picImageWidth;
	private int picImageHeight;
	private int frameWidth;

	@Override
	protected Object getData(Context context) {
		// 解析url
		try {
			byte[] buffer = null;
			String codeTypcd = context.getString(Dict.CODE_TYP_CD);
			String content = URLDecoder.decode(context.getData("content").toString(), "UTF-8");
			String rootPath = getClass().getResource("/").getFile().toString();
			String urlpre = rootPath.substring(0, (rootPath.length() - "WEB-INF/classes/".length()));
			String urlpic = null;
			if (StringUtil.isStringEmpty(codeTypcd)) {
				buffer = createQRCode(content, getImageWidth(), getImageHeight());
			} else {
				if (CodeTypCd.WECHAT.equals(codeTypcd)) {
					urlpic = "/images/wx.jpg";
				} else {
					urlpic = "images/zfb.jpg";
				}
				String url = urlpre + urlpic;
				buffer = createPicQRCode(content, getImageWidth(), getImageHeight(), url);
			}

			return buffer;
		} catch (Exception exception) {
			log.info("------------------生成二维码异常-------------------------");
			return null;
		}
	}

	// 创建二维码图形
	public byte[] createQRCode(String contents, int width, int height) {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.MAX_SIZE, 350);
		hints.put(EncodeHintType.MIN_SIZE, 100);
		BitMatrix matrix = null;
		byte[] buffer = null;
		try {
			matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			buffer = writeToByte(matrix, "png");
		} catch (Exception e) {
		}
		return buffer;
	}

	// 将图片转化为字节
	public byte[] writeToByte(BitMatrix matrix, String format) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			MatrixToImageWriter.writeToStream(matrix, format, stream);
		} catch (IOException e) {
		}
		return stream.toByteArray();
	}

	public byte[] imageToByte(BufferedImage image, String format) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, format, stream);
		} catch (IOException e) {
		}
		return stream.toByteArray();
	}

	/**
	 * 
	 * @param 有图片的的二维码生成
	 * 
	 */
	public byte[] createPicQRCode(String contents, int width, int height, String srcImagePath)
			throws WriterException, IOException {
		// 读取源图像
		BufferedImage scaleImage = scale(srcImagePath, picImageWidth, picImageHeight, true);
		int[][] srcPixels = new int[picImageWidth][picImageHeight];
		for (int i = 0; i < scaleImage.getWidth(); i++) {
			for (int j = 0; j < scaleImage.getHeight(); j++) {
				srcPixels[i][j] = scaleImage.getRGB(i, j);
			}
		}
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		BitMatrix matrix = null;
		byte[] buffer = null;
		try {
			matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
			// 二维矩阵转为一维像素数组
			int halfW = matrix.getWidth() / 2;
			int halfH = matrix.getHeight() / 2;
			int[] pixels = new int[width * height];

			for (int y = 0; y < matrix.getHeight(); y++) {
				for (int x = 0; x < matrix.getWidth(); x++) {
					// 读取图片
					if (x > halfW - picImageWidth / 2 && x < halfW + picImageWidth / 2 && y > halfH - picImageWidth / 2
							&& y < halfH + picImageWidth / 2) {
						pixels[y * width + x] = srcPixels[x - halfW + picImageWidth / 2][y - halfH + picImageWidth / 2];
						/*
						 * if(scaleImage.getRGB(x - halfW +picImageWidth/2,y -
						 * halfH +picImageWidth/2) == -657931){ pixels[y * width
						 * + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff; }
						 */

					}
					// 在图片四周形成边框
					/*
					 * else if ((x > halfW -picImageWidth/2 - frameWidth && x <
					 * halfW -picImageWidth/2 + frameWidth && y > halfH
					 * -picImageWidth/2 - frameWidth && y < halfH
					 * +picImageWidth/2 + frameWidth) || (x > halfW
					 * +picImageWidth/2 - frameWidth && x < halfW
					 * +picImageWidth/2 + frameWidth && y > halfH
					 * -picImageWidth/2 - frameWidth && y < halfH
					 * +picImageWidth/2 + frameWidth) || (x > halfW
					 * -picImageWidth/2 - frameWidth && x < halfW
					 * +picImageWidth/2 + frameWidth && y > halfH
					 * -picImageWidth/2 - frameWidth && y < halfH
					 * -picImageWidth/2 + frameWidth) || (x > halfW
					 * -picImageWidth/2 - frameWidth && x < halfW
					 * +picImageWidth/2 + frameWidth && y > halfH
					 * +picImageWidth/2 - frameWidth && y < halfH
					 * +picImageWidth/2 + frameWidth)) { pixels[y * width + x] =
					 * 0xfffffff; }
					 */ else {
						// 此处可以修改二维码的颜色，可以分别制定二维码和背景的颜色；
						pixels[y * width + x] = matrix.get(x, y) ? 0xff000000 : 0xfffffff;
					}
				}
			}
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			image.getRaster().setDataElements(0, 0, width, height, pixels);
			buffer = imageToByte(image, "png");
		} catch (Exception e) {
		}
		return buffer;
	}

	private static BufferedImage scale(String srcImageFile, int height, int width, boolean hasFiller)
			throws IOException {
		double ratio = 0.0; // 缩放比例
		File file = new File(srcImageFile);
		BufferedImage srcImage = ImageIO.read(file);
		Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
		// 计算比例
		if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
			if (srcImage.getHeight() > srcImage.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			destImage = op.filter(srcImage, null);
		}
		if (hasFiller) {// 补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphic = image.createGraphics();
			graphic.setColor(Color.white);
			graphic.fillRect(0, 0, width, height);
			if (width == destImage.getWidth(null))
				graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2, destImage.getWidth(null),
						destImage.getHeight(null), new Color(240, 255, 240), null);
			else
				graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0, destImage.getWidth(null),
						destImage.getHeight(null), Color.white, null);
			graphic.dispose();
			destImage = image;
		}
		return (BufferedImage) destImage;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public void setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}

	public void setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
	}

	public int getPicImageWidth() {
		return picImageWidth;
	}

	public void setPicImageWidth(int picImageWidth) {
		this.picImageWidth = picImageWidth;
	}

	public int getPicImageHeight() {
		return picImageHeight;
	}

	public void setPicImageHeight(int picImageHeight) {
		this.picImageHeight = picImageHeight;
	}

	public int getFrameWidth() {
		return frameWidth;
	}

	public void setFrameWidth(int frameWidth) {
		this.frameWidth = frameWidth;
	}

}