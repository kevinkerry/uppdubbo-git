package com.csii.upp.batch.appl.risk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.csii.pe.core.PeException;
import com.csii.pe.core.PeRuntimeException;
import com.csii.upp.dao.generate.BlackgrayinfoDAO;
import com.csii.upp.dao.generate.InnercheckapplyDAO;
import com.csii.upp.dto.generate.Blackgrayinfo;
import com.csii.upp.dto.generate.BlackgrayinfoExample;
import com.csii.upp.dto.generate.Innercheckapply;
import com.csii.upp.dto.generate.InnercheckapplyExample;
import com.csii.upp.supportor.IDGenerateFactory;
import com.csii.upp.util.StringUtil;

/**
 * 此Application用于加载用于电信诈骗类型的黑、灰名单 此功能将遍历一个路径下所有的符合一个正则表达式格式的文件，如果符合该格式，则解析文件
 * 解析文件时，对每条记录进行处理
 * 解析文件时，如果解析失败，则过滤到此条数据，继续处理下一条，如果此条已经存在，并且操作为新增，或者不存在，操作为删除，则认为是失败，记录文件差错
 * 在解析一个文件结束后，记录解析结果，一共多少条，正常插入多少条，失败多少条
 *
 * @author XQY
 */
public class LoadBlackListAppl extends AbstractLoadBlackListAppl {

	// public static void main(String[] args) {
	// List<String> resultList = null;
	// try {
	// LoadBlackListAppl appl = new LoadBlackListAppl();
	// resultList = appl.getAvailableFileList();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// for (String str : resultList) {
	// System.out.println(str);
	// }
	// }

	// TODO The method should optimize,Adding the transaction control.
	@Override
	public void insertIntoFileParseTable(String fileName) throws SQLException {
		Innercheckapply fileProcess = new Innercheckapply();
		// TODO 此字段先置空
		fileProcess.setCheckapplynbr("123456");
		fileProcess.setUppersysnbr(DEFAULT_UPPER_SYSTEM_NAME);
		fileProcess.setDownsysnbr(DEFAULT_DOWN_SYSTEM_NAME);
		fileProcess.setCheckstartdate(Calendar.getInstance().getTime());
		fileProcess.setCheckenddate(Calendar.getInstance().getTime());
		fileProcess.setFilename(fileName);
		fileProcess.setDealcode(DEAL_CODE_MAP.get("INIT"));
		fileProcess.setDealmsg(DEAL_MSG_MAP.get("INIT"));
		fileProcess.setMemo1("开始处理");

		final Innercheckapply fileProcessInSQL = fileProcess;
		getTransactionTemplate().execute(new TransactionCallback() {
			@Override
			public Object doInTransaction(TransactionStatus transactionStatus) {
				try {
					InnercheckapplyExample example = new InnercheckapplyExample();
					example.createCriteria().andUppersysnbrEqualTo(DEFAULT_UPPER_SYSTEM_NAME)
							.andDownsysnbrEqualTo(DEFAULT_DOWN_SYSTEM_NAME)
							.andFilenameEqualTo(fileProcessInSQL.getFilename());
					List<Innercheckapply> processFileList = InnercheckapplyDAO.selectByExample(example);
					if (null == processFileList || processFileList.size() == 0) {
						if (StringUtil.isStringEmpty(fileProcessInSQL.getCheckapplynbr())) {
							fileProcessInSQL.setCheckapplynbr(IDGenerateFactory.generateSeqId());
						}
						InnercheckapplyDAO.insert(fileProcessInSQL);
					} else if (processFileList.size() == 1) {
						Innercheckapply processFile = processFileList.get(0);
						if (DEAL_CODE_MAP.get("SUCCESS").equals(processFile.getDealcode())) {
							throw new PeRuntimeException("The data is not expected.");
						}
						// fileProcessInSQL.setCheckapplynbr(null);
						InnercheckapplyDAO.updateByExampleSelective(fileProcessInSQL, example);
					} else {
						throw new PeRuntimeException("The data is not expected.");
					}
				} catch (Exception ex) {
					throw new PeRuntimeException(ex);
				}
				return null;
			}
		});
		if (StringUtil.isStringEmpty(fileProcess.getCheckapplynbr())) {
			fileProcess.setCheckapplynbr(IDGenerateFactory.generateSeqId());
		}
		InnercheckapplyDAO.insertSelective(fileProcess);
	}

	@Override
	protected boolean selectIfSuccessParseFileName(String fileName) throws SQLException {
		InnercheckapplyExample example = new InnercheckapplyExample();
		example.createCriteria().andUppersysnbrEqualTo(DEFAULT_UPPER_SYSTEM_NAME)
				.andDownsysnbrEqualTo(DEFAULT_DOWN_SYSTEM_NAME).andFilenameEqualTo(fileName)
				.andDealcodeEqualTo(DEAL_CODE_MAP.get("SUCCESS"));
		List<Innercheckapply> fileProcessList = InnercheckapplyDAO.selectByExample(example);
		if (fileProcessList.size() > 1) {
			throw new SQLException("The expected data is not more than 1.Please check the data");
		}
		return 1 == fileProcessList.size();
	}

	@Override
	protected void updateIntoFileParseTable(String fileName, Integer successLines, Integer failedLines)
			throws SQLException {
		final String fileNameInSQL = fileName;
		final int successLineInSQL = successLines.intValue();
		final int failedLineInSQL = failedLines.intValue();
		getTransactionTemplate().execute(new TransactionCallback() {
			public Object doInTransaction(TransactionStatus arg0) {
				try {
					InnercheckapplyExample example = new InnercheckapplyExample();
					example.createCriteria().andUppersysnbrEqualTo(DEFAULT_UPPER_SYSTEM_NAME)
							.andDownsysnbrEqualTo(DEFAULT_DOWN_SYSTEM_NAME).andFilenameEqualTo(fileNameInSQL);
					List<Innercheckapply> fileProcessList = InnercheckapplyDAO.selectByExample(example);
					if (fileProcessList.size() != 1) {
						throw new PeRuntimeException("The data is not expected.");
					}
					Innercheckapply fileProcessModel = fileProcessList.get(0);
					if (!DEAL_CODE_MAP.get("INIT").equals(fileProcessModel.getDealcode())) {
						throw new PeRuntimeException("The deal code is not expected.");
					}
					Innercheckapply fileProcess = new Innercheckapply();
					fileProcess.setUppersysnbr(DEFAULT_UPPER_SYSTEM_NAME);
					fileProcess.setDownsysnbr(DEFAULT_DOWN_SYSTEM_NAME);
					if (0 != failedLineInSQL) {
						fileProcess.setDealcode(DEAL_CODE_MAP.get("FAILED"));
						fileProcess.setMemo1("执行失败，成功" + successLineInSQL + "条，失败" + failedLineInSQL + "条。");
						InnercheckapplyDAO.updateByExampleSelective(fileProcess, example);
					} else {
						fileProcess.setDealcode(DEAL_CODE_MAP.get("SUCCESS"));
						fileProcess.setMemo1("执行成功。");
						InnercheckapplyDAO.updateByExampleSelective(fileProcess, example);
					}
				} catch (Exception ex) {
					throw new PeRuntimeException(ex);
				}
				return null;
			}
		});
	}

	protected void insertIntoBlackList(Object obj) throws SQLException {
		BlackgrayinfoDAO.insert((Blackgrayinfo) obj);
	}

	protected List<Blackgrayinfo> selectActiveBlackDataList(Object obj) throws SQLException {
		Blackgrayinfo blackInfo = (Blackgrayinfo) obj;
		BlackgrayinfoExample example = new BlackgrayinfoExample();
		example.createCriteria().andBlacktypeEqualTo(blackInfo.getBlacktype())
				.andBlacklevelEqualTo(blackInfo.getBlacklevel()).andBlackacctnbrEqualTo(blackInfo.getBlackacctnbr())
				.andBlackcerttypeEqualTo(blackInfo.getBlackcerttype())
				.andBlackcertnbrNotEqualTo(blackInfo.getBlackcertnbr());
		return BlackgrayinfoDAO.selectByExample(example);
	}

	// protected Blackgrayinfo selectActiveBlackDataList(Object obj) throws
	// SQLException {
	// Blackgrayinfo blackInfo = (Blackgrayinfo)obj;
	// String blackNo = blackInfo.getBlacknbr();
	// return BlackgrayinfoDAO.selectByPrimaryKey(blackNo);
	// }

	protected int updateBlackList(Object obj) throws SQLException {
		Blackgrayinfo blackInfo = (Blackgrayinfo) obj;
		BlackgrayinfoExample example = new BlackgrayinfoExample();
		example.createCriteria().andBlacktypeEqualTo(blackInfo.getBlacktype())
				.andBlacklevelEqualTo(blackInfo.getBlacklevel()).andBlackacctnbrEqualTo(blackInfo.getBlackacctnbr())
				.andBlackcerttypeEqualTo(blackInfo.getBlackcerttype())
				.andBlackcertnbrNotEqualTo(blackInfo.getBlackcertnbr());
		return BlackgrayinfoDAO.updateByExampleSelective(blackInfo, example);
	}

	/**
	 * 关于此方法的传入参数，不晓得
	 *
	 * @param entry
	 * @param argMaps
	 * @throws Exception
	 */
	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
		parseAllFiles();
		moveHistoryFiles();
	}

	/**
	 * 此方法中，selectIfSuccessParseFileName会出现SQL异常，出现则不再执行所有方法
	 * 此方法中，verityFile不会抛出异常，验证失败，则直接忽略该文件
	 *
	 * @throws PeException
	 *             如果出现意外，则直接抛出异常
	 */
	@Override
	protected void parseAllFiles() throws PeException {
		try {
			List<String> availRunFileNameList = getAvailableFileList();
			for (String str : availRunFileNameList) {
				if (true == selectIfSuccessParseFileName(str))
					break;
				if (false == verityFile(str))
					break;
				// parseEachFile方法抛出异常，直接执行下一个文件，忽略当前文件
				try {
					parseEachFile(str);
				} catch (Exception ex) {
					log.error(ex.getStackTrace());
				}
			}
		} catch (Exception ex) {
			log.error(ex.getStackTrace());
			throw new PeException("OS0099");
		}
	}

	protected void moveHistoryFiles() throws PeException {
		try {
			List<String> preMovedFileList = getPreMovedFileList();
			for (String str : preMovedFileList) {
				if (true != selectIfSuccessParseFileName(str))
					break;
				log.info("Move the Black history file:" + str);
				moveFileToHistoryPath(str);
			}

		} catch (Exception ex) {
			log.error(ex.getStackTrace());
		}
	}

	/**
	 * 两种方案 一种方案是，解析文件，然后形成List，然后使用脚本批量入库，这种方案在遇到在大数据量时，很容易崩溃
	 * 一种方案是，解析文件，逐条/逐N条，分别入库，现采用该方案 暂定返回类型为String，稍候再定（暂定为String为返回具体的处理信息）
	 *
	 * @param fileName
	 */
	@Override
	protected void parseEachFile(String fileName) throws SQLException, IOException {
		File runFile = null;
		BufferedReader reader = null;
		log.info("Parse file:" + fileName + "Start");
		insertIntoFileParseTable(fileName);
		runFile = new File(RUN_FILE_PATH + fileName);
		reader = new BufferedReader(new FileReader(runFile));
		int readingLine = 1;

		// 过滤不需要解析的行
		for (; readingLine < startLine; readingLine++) {
			if (null == reader.readLine())
				break;
		}

		String eachLineData;
		Integer successLines = 0;
		Integer failedLines = 0;
		while ((eachLineData = reader.readLine()) != null) {
			// 如果解析出现异常，则在失败的条目上加1，允许在解析单行时出现异常
			try {
				if (parseEachLine(fileName, eachLineData)) {
					successLines++;
				} else {
					failedLines++;
				}
			} catch (Exception ex) {
				log.error(ex.getStackTrace());
				failedLines++;
			}
		}
		updateIntoFileParseTable(fileName, successLines, failedLines);
		log.info("Parse file:" + fileName + "End");
	}

	@Override
	protected boolean parseEachLine(String fileName, String data) throws PeException, SQLException {
		log.info("Parse the String:" + data);
		String[] fieldData = data.split(fileFilter);
		// if(loadBlackFileFieldList.size()!=loadBlackFileFieldMap.size()) {
		// throw new PeException("The ");
		// }
		if (fieldData.length != LOAD_BLACK_FILE_FIELD_LIST.size()) {
			throw new PeException("The format of input's data  is different from the expected format.");
		}

		Map<String, String> blackFileData = new HashMap<String, String>();

		for (int i = 0; i < LOAD_BLACK_FILE_FIELD_FORMAT_MAP.size(); i++) {
			String fieldEachData = fieldData[i];
			// 数据超出，如果有校验标准，不合标准，则取前N位，如果不存在校验标准，则不校验，可能会入库失败，异常由上层拦截处理
			if (LOAD_BLACK_FILE_FIELD_FORMAT_MAP.containsKey(LOAD_BLACK_FILE_FIELD_LIST.get(i)) && fieldEachData
					.length() > LOAD_BLACK_FILE_FIELD_FORMAT_MAP.get(LOAD_BLACK_FILE_FIELD_LIST.get(i))) {
				log.error("The length of field:" + LOAD_BLACK_FILE_FIELD_LIST.get(i)
						+ "is more than the expected max length:"
						+ LOAD_BLACK_FILE_FIELD_FORMAT_MAP.get(LOAD_BLACK_FILE_FIELD_LIST.get(i)));
				fieldEachData = fieldEachData.substring(0,
						LOAD_BLACK_FILE_FIELD_FORMAT_MAP.get(LOAD_BLACK_FILE_FIELD_LIST.get(i)));
			}
			blackFileData.put(LOAD_BLACK_FILE_FIELD_LIST.get(i), fieldEachData);
		}
		log.debug("Parse the String:" + data + "OK." + "The map is " + blackFileData);

		return insertDataByBlackFileData(fileName, blackFileData);
	}

	protected boolean insertDataByBlackFileData(String fileName, Map<String, String> blackFileData)
			throws PeException, SQLException {
		Object blackData = null;
		try {
			blackData = generateBlackListDataFromFileData(fileName, blackFileData);
		} catch (Exception ex) {
			throw new PeException("model load error");
		}

		String operationFlag = blackFileData.get("OperationFlag");
		if ("+".equals(operationFlag)) {
			List currentBlackDataList = selectActiveBlackDataList(blackData);
			if (currentBlackDataList.size() != 0) {
				throw new PeException("Exist the active data!Can not operation the data");
			}
			insertIntoBlackList(blackData);
		} else if ("-".equals(operationFlag)) {
			// TODO 此处list不指定类型，因预计指定为List<Object>，因类型转换问题，暂置如此
			List currentBlackDataList = (List) selectActiveBlackDataList(blackData);
			if (currentBlackDataList.size() != 1) {
				throw new PeException("Not exist the active data");
			}
			updateBlackList(blackData);
		} else {
			throw new PeException("The data 'OperationFlag' format is error.");
		}

		return false;
	}

	protected Object generateBlackListDataFromFileData(String fileName, Map<String, String> blackFileData)
			throws PeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
			ClassNotFoundException {
		Class modelClass = null;
		try {
			modelClass = loadDefaultModelClass();
		} catch (ClassNotFoundException e) {
			throw new PeException("class:" + loadModelClassName + "not found.");
		}

		Object model = null;
		try {
			model = modelClass.newInstance();
		} catch (InstantiationException e) {
			throw new PeException("object init :" + loadModelClassName + "failed.");
		} catch (IllegalAccessException e) {
			throw new PeException("object init :" + loadModelClassName + "failed.");
		}
		return generateBlackListDataFromFileData(fileName, modelClass, model, blackFileData);
	}

	protected Object generateBlackListDataFromFileData(String fileName, Class modelClass, Object model,
			Map<String, String> data) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
					ClassNotFoundException {
		// TODO 代码需要优化,生成序号的逻辑
		setBlackNo(modelClass, model, generateSeqNo());
		// 设置级别，也就是黑名单或灰名单
		setBlackLevel(modelClass, model, generateBlackLevelFromFileName(fileName));
		// 设置类型，是客户涉案，还是账号涉案
		// TODO 代码编写
		// setBlackType(modelClass, model, generateBlackLevelFromData(data));
		// 设置账号，如果是账号涉案，则设置
		setBlackAcctNo(modelClass, model, data);
		// 设置客户涉案时的证件类型，暂未用
		setBlackCertType(modelClass, model, null);
		// 设置客户涉案时的证件号码
		setBlackCertNo(modelClass, model, data);
		// 设置案件来源
		setBlackCaseSource(modelClass, model, data);
		// 设置协案件类型
		setBlackCaseType(modelClass, model, data);
		// 设置涉案客户姓名
		setBlackAcctName(modelClass, model, data);
		// 设置涉案的大小额行号
		setBlackBankNo(modelClass, model, data);
		// 设置涉案的联系人
		setBlackContracts(modelClass, model, data);
		// 设置涉案的联系人的联系方式
		setBlackContractPhone(modelClass, model, data);
		// 设置生效失效标志，+为生效，-为失效
		setBlackActiveFlag(modelClass, model, data);
		// 设置添加文件，+则设置，-不设置
		setBlackInsertFile(modelClass, model, data);
		// 设置删除文件，+不设置，-设置
		setBlackDeleteFile(modelClass, model, data);
		// 设置更新时间
		setUpdateTime(modelClass, model, data);
		return model;
	}

	protected String generateBlackLevelFromFileName(String fileName) {
		if (fileName.contains("gray")) {
			return "0";
		}
		if (fileName.contains("black")) {
			return "1";
		}
		return null;
	}

	protected void setBlackNo(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackNo"), data);
	}

	protected void setBlackLevel(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackLevel"), data);
	}

	protected void setBlackType(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackType"), data);
	}

	protected void setBlackAcctNo(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackAcctNo"), data);
	}

	protected void setBlackCertType(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackCertType"), data);
	}

	protected void setBlackCertNo(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackCertNo"), data);
	}

	protected void setBlackCaseSource(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackCaseSource"), data);
	}

	protected void setBlackCaseType(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackCaseType"), data);
	}

	protected void setBlackAcctName(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackAcctName"), data);
	}

	protected void setBlackBankNo(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackBankNo"), data);
	}

	protected void setBlackContracts(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackContracts"), data);
	}

	protected void setBlackContractPhone(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackContractPhone"), data);
	}

	protected void setBlackActiveFlag(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackActiveFlag"), data);
	}

	protected void setBlackInsertFile(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackInsertFile"), data);
	}

	protected void setBlackDeleteFile(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setBlackDeleteFile"), data);
	}

	protected void setUpdateTime(Class modelClass, Object model, Object data)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, ClassNotFoundException {
		setValueFromSetMethod(modelClass, model, MODEL_SET_METHOD.get("setUpdateTime"), data);
	}

	protected boolean changeFileFieldToBlackModel(String fileName, Object loadModel) {
		return false;
	}
}
