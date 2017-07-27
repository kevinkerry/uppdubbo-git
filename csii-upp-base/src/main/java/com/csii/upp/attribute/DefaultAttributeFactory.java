package com.csii.upp.attribute;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.annotation.AttributeProperties;
import com.csii.upp.annotation.BaseObjClass;

/**
 * 
 * @classname DefaultAttributeFactory
 * @description TODO(解析对象的属性特性)
 * @author xujin
 * 
 */
public class DefaultAttributeFactory implements AttributeFactory {

	protected final static Log log = LogFactory.getLog(AttributeFactory.class);

	private List<String> classNames;
	public void setClassNames(List<String> classNames) {
		this.classNames = classNames;
	}

	@SuppressWarnings("rawtypes")
	private Map<Class, Map<String, Attribute>> attributeMapMap = new HashMap<Class, Map<String, Attribute>>();

	protected String getGetterName(Field field) {
		StringBuilder sb = new StringBuilder();
		String name = field.getName();
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		if (field.getType() == boolean.class) {
			sb.append("is").append(name);
		} else {
			sb.append("get").append(name);
		}
		return sb.toString();
	}

	protected String getSetterName(Field field) {
		StringBuilder sb = new StringBuilder();
		String name = field.getName();
		sb.append("set").append(name.substring(0, 1).toUpperCase())
				.append(name.substring(1));
		return sb.toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public synchronized Map<String, Attribute> getAttributesMap(Class clazz) {
		Map<String, Attribute> attributes = new HashMap<String, Attribute>();
		Field[] fields = clazz.getDeclaredFields();
		if (clazz.getSuperclass() != null
				&& clazz.getSuperclass() != Object.class)
			attributes.putAll(getAttributesMap(clazz.getSuperclass()));
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if(Modifier.isFinal(field.getModifiers())){
				continue;
			}
			BaseObjClass dtoClass = field.getAnnotation(BaseObjClass.class);
			AttributeProperties props = field.getAnnotation(AttributeProperties.class);
			try {
				Method getter = clazz.getDeclaredMethod(
						getGetterName(field), (Class[]) null);
				getter.setAccessible(true);
				Method setter = clazz.getDeclaredMethod(getSetterName(field), field.getType());   
				setter.setAccessible(true);
				Attribute aat = new Attribute(fields[i].getName(), getter, setter, field.getType());
				if (dtoClass != null) {
					aat.setElementClass(dtoClass.getBaseObjClass());
				}
				if (props != null) {
					aat.setRequired(props.required());
					aat.setCheckConstraint(props.checkConstraint());
					aat.setDecimal(props.decimal());
					aat.setDateFormat(props.dateFormat());
				}
				attributes.put(aat.getName(), aat);
			} catch (SecurityException e) {
				throw new PeRuntimeException(e.getMessage());
			} catch (NoSuchMethodException e) {
				throw new PeRuntimeException(e.getMessage());
			}

		}
		attributeMapMap.put(clazz, attributes);
		return attributes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Attribute> getAttributes(Class clazz) {
		Map<String, Attribute> attributes = attributeMapMap.get(clazz);
		if (attributes != null) {
			return new ArrayList(attributes.values());
		}
		return new ArrayList(getAttributesMap(clazz).values());
	}

	@SuppressWarnings("rawtypes")
	public void initialize() throws ClassNotFoundException, IOException {
		for(String className: classNames){
			Class cls = Class.forName(className);
			Set<Class<?>> set = this.getClasses(cls.getPackage());
			for (Iterator it = set.iterator(); it.hasNext();) {
				this.getAttributes((Class) it.next());
			}
		}
	}

	/**
	 * 从包package中获取所有的Class
	 * 
	 * @param pack
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public Set<Class<?>> getClasses(Package pack)
			throws ClassNotFoundException, IOException {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 是否循环迭代
		boolean recursive = true;
		// 获取包的名字 并进行替换
		String packageName = pack.getName();
		String packageDirName = packageName.replace('.', '/');
		// 定义一个枚举的集合 并进行循环来处理这个目录下的things
		Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader()
				.getResources(packageDirName);
		// 循环迭代下去
		while (dirs.hasMoreElements()) {
			// 获取下一个元素
			URL url = dirs.nextElement();
			// 得到协议的名称
			String protocol = url.getProtocol();
			// 如果是以文件的形式保存在服务器上
			if ("file".equals(protocol)) {
				// 获取包的物理路径
				String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
				// 以文件的方式扫描整个包下的文件 并添加到集合中
				findAndAddClassesInPackageByFile(packageName, filePath,
						recursive, classes);
			} else if ("jar".equals(protocol)) {
				// 如果是jar包文件
				// 定义一个JarFile
				JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
				// 从此jar包 得到一个枚举类
				Enumeration<JarEntry> entries = jar.entries();
				// 同样的进行循环迭代
				while (entries.hasMoreElements()) {
					// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
					JarEntry entry = entries.nextElement();
					String name = entry.getName();
					// 如果是以/开头的
					if (name.charAt(0) == '/') {
						// 获取后面的字符串
						name = name.substring(1);
					}
					// 如果前半部分和定义的包名相同
					if (name.startsWith(packageDirName)) {
						int idx = name.lastIndexOf('/');
						// 如果以"/"结尾 是一个包
						if (idx != -1) {
							// 获取包名 把"/"替换成"."
							packageName = name.substring(0, idx).replace('/',
									'.');
						}
						// 如果可以迭代下去 并且是一个包
						if ((idx != -1) || recursive) {
							// 如果是一个.class文件 而且不是目录
							if (name.endsWith(".class") && !entry.isDirectory()) {
								// 去掉后面的".class" 获取真正的类名
								String className = name.substring(
										packageName.length() + 1,
										name.length() - 6);
								// 添加到classes
								classes.add(Class.forName(packageName + '.'
										+ className));
							}
						}
					}
				}
			}
		}
		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有Class
	 * 
	 * @param packageName
	 * @param packagePath
	 * @param recursive
	 * @param classes
	 * @throws ClassNotFoundException
	 * @throws PeException
	 */
	public void findAndAddClassesInPackageByFile(String packageName,
			String packagePath, final boolean recursive, Set<Class<?>> classes)
			throws ClassNotFoundException {
		// 获取此包的目录 建立一个File
		File dir = new File(packagePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		// 如果存在 就获取包下的所有文件 包括目录
		File[] dirfiles = dir.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory())
						|| (file.getName().endsWith(".class"));
			}
		});
		// 循环所有文件
		for (File file : dirfiles) {
			// 如果是目录 则继续扫描
			if (file.isDirectory()) {
				findAndAddClassesInPackageByFile(
						packageName + "." + file.getName(),
						file.getAbsolutePath(), recursive, classes);
			} else {
				// 如果是java类文件 去掉后面的.class 只留下类名
				String className = file.getName().substring(0,
						file.getName().length() - 6);
				// 添加到集合中去
				classes.add(Class.forName(packageName + '.' + className));
			}
		}
	}

}
