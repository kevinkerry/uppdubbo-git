/**
 * 
 */
package com.csii.upp.paygate.action.foison;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Hashtable;

import com.csii.ibs.action.ajax.AbstractDynamicMessageAction;
import com.csii.pe.core.Context;
import com.csii.upp.dict.Dict;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * @author lixinyou
 * 生成电子回单二维码
 *
 */
public class GenerateElecReceiptAction extends AbstractDynamicMessageAction {

	private int imageWidth;
	private int imageHeight;
	
	@Override
	protected Object getData(Context context) {
		try
        {
        	String content = URLDecoder.decode(context.getData(Dict.CONTENT).toString(),"UTF-8");
        	String[] arrStr = content.split(",");
        	StringBuilder sb = new StringBuilder();
        	for (int i = 0; i < arrStr.length; i++) {
        		if(i == arrStr.length-1){
        			sb.append(arrStr[i].trim());
        		}else{
        			sb.append(arrStr[i].trim()).append(",\n");
        		}
			}
        	byte[] buffer = createQRCode(sb.toString(), getImageWidth(), getImageHeight());
	        return buffer;
        }
        catch(Exception exception)
        {
            return null;
        }
	}
	
	public byte[] createQRCode(String contents, int width ,int height){
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0); 
		BitMatrix matrix = null;
		byte[] buffer = null;
		try {
			matrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height,hints);
			buffer = writeToByte(matrix,"png");
		} catch (Exception e) {
		}
		return buffer;
	}
	public byte[] writeToByte(BitMatrix matrix,String format){
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			MatrixToImageWriter.writeToStream(matrix, format, stream);
		} catch (IOException e) {
		}
		return stream.toByteArray();
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
}
