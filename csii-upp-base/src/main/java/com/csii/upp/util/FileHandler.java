package com.csii.upp.util;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class FileHandler {

	private static Log log = LogFactory.getLog(FileHandler.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log.info("=====================Test Start========================");
		// TODO Auto-generated method stub

		FileHandler.unZip("F:\\checkFile\\unionpay\\104110554991350_20150122.zip", "D:\\checkFile\\unionpay\\");
		log.info("===================== Test End ========================");
	}

	public static boolean isExistsFile(String filePath, String fileName) {
		File file = new File(checkPathEnd(filePath) + fileName);
		if (!file.exists()) {
			return false;
		} else {
			return true;
		}
	}

	public static void createFile(String filePath, String fileName) {
		File file = new File(checkPathEnd(filePath) + fileName);
		if (!file.exists()) {
			try {
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				boolean create = file.createNewFile();
				if (!create) {
					log.error("cann't create File " + filePath);
				}
			} catch (IOException e1) {
				log.error("cann't create File " + filePath);
			}

		} else {
			if (file.isFile()) {
				file.delete();
			}
		}
	}

	public static void writeRecorde(String value, FileOutputStream out,
			String encoding) throws UnsupportedEncodingException, IOException {
		out.write(value.getBytes(encoding));
		out.flush();
	}

	public static String checkPathEnd(String filePath) {
		String tag = filePath.substring(filePath.length() - 1);
		if ("/".equals(tag) || "\\".equals(tag))
			return filePath;
		else
			return filePath + "/";
	}

	public static boolean makeFile(String filePath, String fileName,
			byte[] content) {
		FileOutputStream out = null;
		File file = null;
		boolean flag = false;
		try {
			file = new File(checkPathEnd(filePath) + fileName);
			if (!file.exists()) {
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				file.createNewFile();
			} else {
				File fi = new File(checkPathEnd(filePath)
						+ fileName
						+ "_"
						+ DateUtil.Date_To_DateTimeFormat(DateUtil
								.getCurrentDateTime()));
				file.renameTo(fi);
			}
			out = new FileOutputStream(file, false);
			out.write(content);
			out.flush();
			flag = true;
		} catch (FileNotFoundException e) {
			StringBuilder sb = new StringBuilder(
					"File not exist Error . fileName is ");
			sb.append(fileName).append(", Error info is ")
					.append(e.getMessage());
			log.error(sb);
		} catch (IOException e) {
			StringBuilder sb = new StringBuilder(
					"File process fail I/O Error . fileName is ");
			sb.append(fileName).append(", Error info is ")
					.append(e.getMessage());
			log.error(sb);
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder(
					"File process fail Error . fileName is ");
			sb.append(fileName).append(", Error info is ")
					.append(e.getMessage());
			log.error(sb);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception ex) {
					log.error("Close FileOutputStream [" + file.getName()
							+ "] Error.", ex);
				}
		}
		return flag;
	}

	public static byte[] readLocalFile(String localPath, String fileName) {
		try {
			File file = new File(checkPathEnd(localPath) + fileName);
			if (file.exists()) {
				FileInputStream is = new FileInputStream(file);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] bytes = new byte[1024];
				int c;
				while ((c = is.read(bytes)) != -1) {
					out.write(bytes, 0, c);
				}
				is.close();
				return out.toByteArray();
			} else
				return null;
		} catch (Exception ex) {
			log.error("read file error ,file name:" + localPath + fileName, ex);
			return null;
		}
	}

	public static synchronized boolean appendFile(String filePath,
			String fileName, byte[] content) {
		FileOutputStream out = null;
		File file = null;
		boolean flag = false;
		try {
			file = new File(checkPathEnd(filePath) + fileName);
			if (!file.exists()) {
				File dir = file.getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				file.createNewFile();
			}
			out = new FileOutputStream(file, true);
			out.write(content);
			out.flush();
			flag = true;
		} catch (FileNotFoundException e) {
			StringBuilder sb = new StringBuilder(
					"File not exist Error . fileName is ");
			sb.append(fileName).append(", Error info is ")
					.append(e.getMessage());
			log.error(sb);
		} catch (IOException e) {
			StringBuilder sb = new StringBuilder(
					"File process fail I/O Error . fileName is ");
			sb.append(fileName).append(", Error info is ")
					.append(e.getMessage());
			log.error(sb);
		} catch (Exception e) {
			StringBuilder sb = new StringBuilder(
					"File process fail Error . fileName is ");
			sb.append(fileName).append(", Error info is ")
					.append(e.getMessage());
			log.error(sb);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (Exception ex) {
					log.error("Close FileOutputStream [" + file.getName()
							+ "] Error.", ex);
				}
		}
		return flag;
	}

	/**
	 * 解压zip格式的压缩包
	 * 
	 * @param filePath
	 *            压缩文件路径
	 * @param outPath
	 *            输出路径
	 * @return 解压成功或失败标志
	 */
	public static Boolean unZip(String filePath, String outPath) {
		String unzipfile = filePath; // 解压缩的文件名
		try {
			ZipInputStream zin = new ZipInputStream(new FileInputStream(
					unzipfile));
			ZipEntry entry;
			// 创建文件夹
			while ((entry = zin.getNextEntry()) != null) {
				if (entry.isDirectory()) {
					File directory = new File(outPath, entry.getName());
					if (!directory.exists()) {
						if (!directory.mkdirs()) {
							System.exit(0);
						}
					}
					zin.closeEntry();
				} else {
					File myFile = new File(entry.getName());
					FileOutputStream fout = new FileOutputStream(outPath
							+ myFile.getPath());
					DataOutputStream dout = new DataOutputStream(fout);
					byte[] b = new byte[1024];
					int len = 0;
					while ((len = zin.read(b)) != -1) {
						dout.write(b, 0, len);
					}
					dout.close();
					fout.close();
					zin.closeEntry();
				}
			}
			return true;
		} catch (IOException e) {
			log.error(e.getMessage());
			return false;
		}
	}
}
