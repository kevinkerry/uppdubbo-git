package com.csii.upp.dict;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.TreeSet;

/**
 * Insert the type's description here. Creation date: (2001-11-26 9:56:23, modified by Jimmy in 2005/8/30)
 * 
 * @author: David Zheng,Lawrence Dai,Jimmy
 */
public class DictGenerator {
	static java.util.HashMap keys = new java.util.HashMap();

	static java.util.HashMap comments = new java.util.HashMap();
	
	static String corePath="csii-upp-base";
	
	static String serverPath="csii-upp-base";
	

	/**
	 * DictGenerator constructor comment.
	 */
	public DictGenerator() {
		super();
	}

	/**
	 * Generates a dictionary class from a properties-file Creation date:
	 * (2002-8-29 18:16:57)
	 * 
	 * @param args
	 *            java.lang.String[]
	 */
	public static void main(String[] args) {
//		// generate naming dictionary.
  	    String fileName = "classes/config/message/dictionary_zh_CN.properties";
//
//		// dictionary and constants definition together.
		String targetName = "Dict";
//
     	make(fileName, targetName);

		// generate error dictionary.
			fileName = "classes/config/message/errorCode_zh_CN.properties";
		 targetName = "DictErrors";
		make(fileName, targetName);

	}
	
	/*
	 * 获取真实的文件路径，生成的目标java文件和本文件是同一个子目录下。
	 * 
	 */
	private static String getFullName(String targetName) throws UnsupportedEncodingException {
		if (targetName == null)
			return null;
		
		String path = "/" + DictGenerator.class.getName().replace('.', '/');
		path = path.substring(0, path.lastIndexOf('/') + 1);

		String thisPath = path + "DictGenerator.class";
		java.net.URL outUrl = DictGenerator.class.getResource(thisPath);
		String realPath = outUrl.getFile();
		realPath=URLDecoder.decode(realPath,"utf-8");
		path = realPath.substring(0, realPath.lastIndexOf('/') + 1);
		
		String classFileDir = realPath.substring(0, realPath.indexOf(thisPath));
		String packageDir = DictGenerator.class.getPackage().getName().replace('.', '/');
		
		//获取java文件的目录，此行代码适用于java文件和class文件不在同一目录下
		String javaFileDir = realPath.substring(0, realPath.indexOf(thisPath))
								.substring(0, classFileDir.lastIndexOf('/') + 1);
		//获取java文件的目录，此行代码适用于java文件和class文件在同一目录下
		//String javaFileDir = realPath.substring(0, realPath.indexOf(thisPath));
		
		String dictName = javaFileDir + "classes"+'/'+ packageDir +'/' + targetName + ".java";
		
		return dictName.replace("target/classes", "src/main/java");
	}
	
	/*
	 * 获取配置文件的真实的文件路径。
	 * 
	 */
	private static String getPropertiesFileFullName(String fillName) throws UnsupportedEncodingException {
		if (fillName == null)
			return null;
		
		String path = "/" + DictGenerator.class.getName().replace('.', '/');
		path = path.substring(0, path.lastIndexOf('/') + 1);

		String thisPath = path + "DictGenerator.class";
		java.net.URL outUrl = DictGenerator.class.getResource(thisPath);
		String realPath = outUrl.getFile();
		realPath=URLDecoder.decode(realPath,"utf-8");
		path = realPath.substring(0, realPath.lastIndexOf('/') + 1);
		
		String classFileDir = realPath.substring(0, realPath.indexOf(thisPath));
		
		//获取java文件的目录，此行代码适用于java文件和class文件不在同一目录下
		String javaFileDir = realPath.substring(0, realPath.indexOf(thisPath))
								.substring(0, classFileDir.lastIndexOf('/') + 1);
		//获取java文件的目录，此行代码适用于java文件和class文件在同一目录下
		//String javaFileDir = realPath.substring(0, realPath.indexOf(thisPath));
		javaFileDir=javaFileDir.replace(corePath,serverPath);
		String dictName = javaFileDir + fillName;

		return dictName;
	}
	
	/*
	 * 真正生成文件的动作是这个make完成的。
	 * 
	 */
	private static void make(String fileName, String targetName) {
		try {
			// fetch properties from file.
			targetName=targetName.replace('/', '/');
			fileName=getPropertiesFileFullName(fileName);
			File file = new File(fileName);
			//InputStream in = DictGenerator.class.getResourceAsStream(fileName);
			InputStream in=new FileInputStream(file);
			Properties fp = new KeyCheckProperties();
			fp.load(in);
			in.close();

			// if constants
			int delimitor = targetName.indexOf(',');
			String targetName1 = null;
			if (delimitor > 0) {
				targetName1 = targetName.substring(delimitor + 1);
				targetName = targetName.substring(0, delimitor);
			}

			// find the target java file.
			String dictName = getFullName(targetName);

			String consName = getFullName(targetName1);
//			File deleteFile=new File(dictName);
//			if(deleteFile.isFile()&&deleteFile.exists()){
//				deleteFile.delete();
//			}
			FileOutputStream out=new FileOutputStream(dictName);
			PrintStream prt = new PrintStream(out);
			PrintStream conPrt = consName == null ? null : new PrintStream(
					new java.io.FileOutputStream(consName));

			String packageName = DictGenerator.class.getName();
			packageName = packageName.substring(0, packageName.lastIndexOf('.'));

			prt.println("package " + packageName + ";");
			prt.println();
			prt.println("/**");
			prt.println(" * Auto create from dictionary properties files.");
			prt.println(" * every item in properties has Format like this:");
			prt.println(" * key=comment<0:on;1:off>");
			prt.println();
			prt.println(" * Creation date: (" + new Date() + ")");
			prt.println(" * @author: CSII PowerEngine $Auto Generated$");
			prt.println(" */");
			prt.println("public class " + targetName + " {");

			if (conPrt != null) {
				conPrt.println("package " + packageName + ";");
				conPrt.println("/**");
				conPrt.println(" * Auto create from dictionary properties files.");
				conPrt.println(" * every item in properties has Format like this:");
				conPrt.println(" * key=comment<0:on;1:off>");
				conPrt.println();
				conPrt.println(" * Creation date: (" + new Date() + ")");
				conPrt.println(" * @author: CSII PowerEngine $Auto Generated$");
				conPrt.println(" */");
				conPrt.println("public class " + targetName1 + " {");
			}

			java.util.Set set = new TreeSet(fp.keySet());
			/*----------------------------------------------------------------------------------
			 * 
			 * 
			 * 
			 */
			for (Iterator i = set.iterator(); i.hasNext();) {
				String name = (String) i.next();//源文件中的key，就是dict的源名字。

				String value = fp.getProperty(name);//源文件中的value，包括注释、常量的值、后缀名。
				int conStart = value.indexOf('<');
				int conEnd = value.indexOf('>');
				String cons = null;//常量串
				if (conEnd > conStart && conStart > 0) {
					cons = value.substring(conStart + 1, conEnd);
					value = value.substring(0, conStart);//注释
				}

				String nameInDict = toCanonical(name);//dict类的属性名字。

				//skip index type name. Lawrence Dai 2002/11/4
				if (name.indexOf('[') == -1 && name.indexOf(']') == -1) {
					StringBuilder buffer = new StringBuilder("    public static final String ");
					buffer.append(nameInDict);
					buffer.append("=\"").append(name).append("\";");

					int chars = 20 - buffer.length() % 20;
					if (chars == 20)
						chars = 0;

					buffer.append(nchar(' ', chars));

					String comment = new String(value.getBytes("ISO8859_1"),"utf-8");//
					buffer.append("//").append(comment);

					prt.println(buffer);
				}

				// write cons type.
				if (cons != null && conPrt != null) {
					String[] items = cons.split(";");

					for (int j = 0; j < items.length; j++) {
						StringBuilder buffer = new StringBuilder("    public static final String ");
						String[] keypair = items[j].split(":");
						buffer.append(nameInDict + "_" + toCanonical(keypair[1]));
						buffer.append("=\"").append(keypair[0]).append("\";");
						conPrt.println(buffer);
					}
					conPrt.println();
				}
			}
			//----------------------------------------------------------------------------------------------
			
			
			prt.println("}");
			prt.flush();
			prt.close();
			if (conPrt != null) {
				conPrt.println("}");
				conPrt.flush();
				conPrt.close();
			}

			System.out.print("finished dictionary generation.\njava file:" + dictName
					+ "\nproperties:" + fileName);
            System.out.println();

			if (consName != null) {
				System.out.print("finished constants generation.\njava file:" + consName
						+ "\nproperties:" + fileName);
                System.out.println();
			}

			if (keys.size() > 0) {
				System.err.print("[WARN] --- Found " + keys.size() + " duplicate key.");
				for (java.util.Iterator i = keys.entrySet().iterator(); i.hasNext();) {
					java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
					System.err.println();
					System.err.print(entry.getKey() + " = " + entry.getValue());
				}
				keys.clear();
			}

			if (comments.size() > 0) {
				System.err.print("[WARN] --- Found " + comments.size() + "duplidate comment.");
				for (java.util.Iterator i = comments.entrySet().iterator(); i.hasNext();) {
					java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
	                System.err.println();
					System.err.print("  "+entry.getKey() + " = " + entry.getValue());
				}
				comments.clear();
			}
			
			Thread.sleep(50);
			
			System.out.println();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	/*
	 * 把一个dict的名字编程java类的属性名字，规则是全部变成大写字母，如果源名字包括大写字母，就加“_”下划线。
	 * 其他字母、符号照写。
	 */
	private static String toCanonical(String name) {
		StringBuilder buf = new StringBuilder().append(name.charAt(0));
		for (int i = 1; i < name.length(); i++) {
			if (".".equals(String.valueOf(name.charAt(i)))){
				/*
				 * 如果是点号，并且不是最后一个字母，就加“_”下划线。
				 * 比如，System.Error --->  SYSTEM_ERROR
				 */
				if (i < name.length() - 1)
					buf.append('_');
			}else{
				if (Character.isUpperCase(name.charAt(i))) {
					/*
					 * 如果是大写字母，并且不是最后一个字母，而且紧跟后面的字母不是大写字母，就加“_”下划线。
					 * 比如，SystemError --->  SYSTEM_ERROR
					 * SystemOK      ---->   SYSTEM_OK  
					 */
					if (i < name.length() - 1 && !Character.isUpperCase(name.charAt(i + 1)))
						buf.append('_');
				}
				buf.append(name.charAt(i));
			}
		}
		return buf.toString().toUpperCase();
	}
	/*
	 * 把字符累加n次，作为string返回。
	 */
	private static StringBuilder nchar(char c, int n) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < n; i++)
			b.append(c);
		return b;
	}

	/*
	 * 
	 * 
	 * @author zsg
	 *
	 * 更改所生成类型注释的模板为
	 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
	 * 
	 * 是一个内部类，扩展了Properties类的put方法。
	 * 
	 */
	protected static class KeyCheckProperties extends Properties {
		
		private java.util.Map comm = new java.util.HashMap();

		public Object put(Object key, Object value) {
			String valStr = null;
			try {
				valStr = new String(((String) value).getBytes("ISO8859_1"),"utf-8").trim();
			} catch (UnsupportedEncodingException e) {
				System.err.print(e.getMessage());
			}
			if (super.containsKey(key)) {
				java.util.List l = (java.util.List) keys.get(key);
				if (l == null) {
					l = new java.util.ArrayList();
					keys.put(key, l);
					try {
						String valStr0 = new String(((String) get(key)).getBytes("ISO8859_1"),"utf-8");
						l.add(valStr0);
					} catch (UnsupportedEncodingException e) {
						System.err.print(e.getMessage());
					}
				}
				l.add(valStr);
			}
			
			String val0 = valStr;
			if(val0.indexOf('<') > 0){
				val0 = val0.substring(0, val0.indexOf('<'));
			}
			if (comm.containsValue(val0)) {
				java.util.List l = (java.util.List) comments.get(val0);
				if (l == null) {
					l = new java.util.ArrayList();
					comments.put(val0, l);

					for (java.util.Iterator i = comm.entrySet().iterator(); i.hasNext();) {
						java.util.Map.Entry entry = (java.util.Map.Entry) i.next();
						if (entry.getValue().equals(val0)) {
							l.add(entry.getKey());
						}
					}
				}
				l.add(key);
			}
			
			comm.put(key, val0);

			return super.put(key, value);
		}

	}
}
