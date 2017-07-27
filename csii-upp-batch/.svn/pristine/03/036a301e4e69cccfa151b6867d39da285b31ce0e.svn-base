package com.csii.upp.batch.appl.risk;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.transaction.support.TransactionTemplate;

import com.csii.pe.core.PeException;
import com.csii.upp.batch.appl.base.BaseAppl;
import com.csii.upp.batch.base.BatchSupportor;

/**
 * 此方法为加载黑灰名单的基类，里面有一些公共的方法
 * 文件读写，数据库处理的方法，须经过本层封闭后，供上层使用
 * 一些常量数据，需要经过本层配置，处理后，给子类使用
 *
 * @author XQY
 */

public abstract class AbstractLoadBlackListAppl extends BaseAppl {

    //所有的格式
    protected List<String> filePatterns = new ArrayList<String>() {
        {
            add("all_black_[0-9]*.txt");
            add("all_gray_[0-9]*.txt");
            add("black_[0-9]*.txt");
            add("gray_[0-9]*.txt");
        }
    };
    //检查起始时间距离当前时间点
    protected Integer runStartDate = 30;
    protected Integer runEndDate = 5;

    //检查历史文件的时间区间，时间跨度
    protected Integer historyDate = 30;
    protected Integer historyDateBetween = 5;

    //校验文件完整性的时间间隔
    protected long verityFileTime = 5000;

    //每条数据中字段间的分隔符
    protected String fileFilter = "\\$";
    //从第几行开始解析，起始为1
    protected Integer startLine = 1;

    //文件路径
    protected static final String RUN_FILE_PATH = "D:\\test\\";
    //历史文件路径
    protected static final String HISTORY_FILE_PATH = "D:\\test\\history";

    protected static final String DEFAULT_UPPER_SYSTEM_NAME = "BATCH";
    protected static final String DEFAULT_DOWN_SYSTEM_NAME = "DX";

    protected static final Map<String,String> DEAL_CODE_MAP = new HashMap<String, String>() {
        {
            put("INIT","9");
            put("SUCCESS","0");
            put("FAILED","1");
        }
    };

    protected static final Map<String,String> DEAL_MSG_MAP = new HashMap<String, String>() {
        {
            put("INIT","开始处理");
            put("SUCCESS","");
            put("FAILED","");
        }
    };

    //文件列对应字段
    protected static final List<String> LOAD_BLACK_FILE_FIELD_LIST = new ArrayList<String>() {
        {
            add("Type");
            add("Value");
            add("OperationFlag");
            add("CaseSource");
            add("CaseType");
            add("AcctName");
            add("BankNo");
            add("Contracts");
            add("ContractPhone");
        }
    };

    //文件数据列对应的数据长度，均为String，不存在则不校验格式
    protected static final Map<String, Integer> LOAD_BLACK_FILE_FIELD_FORMAT_MAP = new HashMap<String, Integer>() {
        {
            put("Type", 20);
            put("Value", 40);
            put("OperationFlag", 1);
            put("CaseSource", 20);
            put("CaseType", 20);
            put("AcctName", 20);
            put("BankNo", 20);
            put("Contracts", 20);
            put("ContractPhone", 20);
        }
    };

    protected String loadModelClassName = "com.csii.upp.dto.generate.Blackgrayinfo";

    protected Class defaultClass;
//    protected Map<String,String> filedMapForModelGenerate = new HashMap<String, String>() {
//        {
//            put("blackcontactname","Contracts");
//            put("blackcontactphone","ContractPhone");
//            put("blackcasesource","CaseSource");
//            put("blackcasetype","CaseType");
//            put("blackacctname","AcctName");
//            put("blackbanknbr","BankNo");
//        }
//    };

    protected Map<String, String> MODEL_SET_METHOD = new HashMap<String, String>() {
        {
            put("setBlackNo", "setBlacknbr");
            put("setBlackLevel", "setBlacklevel");
            put("setBlackType", "setBlacktype");
            put("setBlackAcctNo", "setBlackacctnbr");
            put("setBlackCertType", "setBlackcerttype");
            put("setBlackCertNo", "setBlackcertnbr");
            put("setBlackCaseSource", "setBlackcasesource");
            put("setBlackCaseType", "setBlackcasetype");
            put("setBlackAcctName", "setBlackacctname");
            put("setBlackBankNo", "setBlackbanknbr");
            put("setBlackContracts", "setBlackcontactname");
            put("setBlackContractPhone", "setBlackcontactphone");
            put("setBlackActiveFlag", "setBlackactiveflag");
            put("setBlackInsertFile", "setBlackinsertfilename");
            put("setBlackDeleteFile", "setBlackdeletefilename");
            put("setUpdateTime", "setDatelastmaint");
        }
    };
    protected Map<String, Method> methodMap = new HashMap<String, Method>();

    protected List<String> getAvailableFileList() throws Exception {
        List<String> allFileNameList = getRunFileList();
        List<String> availFileNameList = new ArrayList<String>();
        for (String fileName : allFileNameList) {
            if (isMatchedByFileNamePattern(fileName) && isMatchedByRunDate(fileName)) {
                availFileNameList.add(fileName);
            }
        }
        //排序，按文件名升序，String已经实现了Comparable，不需要额外去写比较方法了
        Collections.sort(availFileNameList);
        return availFileNameList;
    }

    protected List<String> getPreMovedFileList() throws Exception {
        List<String> allFileNameList = getRunFileList();
        List<String> preMovedFileList = new ArrayList<String>();
        for (String fileName : allFileNameList) {
            if (isMatchedByFileNamePattern(fileName) && isMatchedByHistoryDate(fileName)) {
                preMovedFileList.add(fileName);
            }
        }
        return preMovedFileList;
    }

    protected void moveFileToHistoryPath(String fileName) throws IOException {
        moveFile(RUN_FILE_PATH + fileName, HISTORY_FILE_PATH + fileName);
    }

    //性能优化，取defaultClass
    protected Class loadDefaultModelClass() throws ClassNotFoundException {
        if (null == defaultClass) {
            defaultClass = Class.forName(loadModelClassName);
        }
        return defaultClass;
    }

    //性能优化，取Map中的已经建立好的方法对象来处理
    protected void setValueFromSetMethod(Class modelClass, Object model, String methodName, Object value) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, ClassNotFoundException {
        if (defaultClass != modelClass) {
            throw new ClassNotFoundException("The class is not equal with the expected one");
        }
        if (!methodMap.containsKey(methodName)) {
            methodMap.put(methodName, modelClass.getMethod(methodName));
        }
        //Method method = modelClass.getMethod(methodName);
        methodMap.get(methodName).invoke(model, value);
    }

    protected String generateSeqNo(){
        double dou = Math.random()*1000*1000*100;
        return String.format("%09d",(int)dou);
    }

    protected TransactionTemplate getTransactionTemplate() {
        return BatchSupportor.getTransactionTemplate();
    }

    protected abstract void parseAllFiles() throws PeException;

    protected abstract void parseEachFile(String fileName) throws Exception;

    protected abstract boolean parseEachLine(String fileName, String data) throws Exception;

    protected abstract void insertIntoFileParseTable(String fileName) throws SQLException;

    protected abstract boolean selectIfSuccessParseFileName(String fileName) throws SQLException;

    protected abstract void updateIntoFileParseTable(String fileName, Integer successLines, Integer failedLines) throws SQLException;

    protected List<String> getRunFileList() throws Exception {
        return getFileList(RUN_FILE_PATH);
    }

    //此方法预留，不会使用，请慎重
    protected List<String> getHistoryFileList() throws Exception {
        return getFileList(HISTORY_FILE_PATH);
    }

    protected List<String> getFileList(String filePath) throws IOException {
        File rootPath = new File(filePath);
        if (!rootPath.exists()) {
            throw new IOException("The input path is not exist.");
        }
        if (!rootPath.isDirectory()) {
            throw new IOException("The input path is not a directory.");
        }
        List<String> resultList = new ArrayList<String>();
        //此处采用getName来获取文件名
        for (File childFile : rootPath.listFiles()) {
            resultList.add(childFile.getName());
        }
        return resultList;
    }

    //此方法，只会返回成功，失败，不会抛出异常
    protected boolean verityFile(String fileName) {
        boolean result;
        try {
            result = verityPathFile(RUN_FILE_PATH + fileName, verityFileTime);
        } catch (Exception ex) {
            log.error(ex.getStackTrace());
            result = false;
        }
        return result;
    }

    protected boolean isMatchedByRunDate(String fileName) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.add(Calendar.DATE, -runStartDate);
        endCal.add(Calendar.DATE, runEndDate);

        return isMatchedByDate(fileName, startCal, endCal);
    }

    protected boolean isMatchedByHistoryDate(String fileName) {
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.add(Calendar.DATE, -historyDate - historyDateBetween);
        endCal.add(Calendar.DATE, -historyDate);

        return isMatchedByDate(fileName, startCal, endCal);
    }

    //基础方法，根据日期区间检查文件名
    public boolean isMatchedByDate(String fileName, Calendar beginDate, Calendar endDate) {
        boolean result = false;
        for (Calendar cal = beginDate; cal.before(endDate); cal.add(Calendar.DATE, 1)) {
            if (isMatchedByDate(fileName, cal)) {
                result = true;
                break;
            }
        }
        return result;
    }

    //基础方法，检查日期校验
    public boolean isMatchedByDate(String fileName, Calendar checkCal) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String datePattern = sdf.format(checkCal.getTime());
        return fileName.contains(datePattern);
    }

    public boolean moveFile(String oldFileName, String newFileName) throws IOException {
        File oldFile = new File(oldFileName);
        if (!oldFile.exists() || !oldFile.isFile()) {
            throw new IOException("The old file is not exist or not a file");
        }
        File newFile = new File(newFileName);
        if (newFile.exists()) {
            throw new IOException("The new file is already exist.");
        }
        return oldFile.renameTo(newFile);
    }

    protected boolean verityPathFile(String fileName, long verityFileTimeBetween) throws IOException, InterruptedException {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("The file is not exist or not a file.");
        }
        long startLength = file.length();
        Thread.sleep(verityFileTimeBetween);
        long endLength = file.length();
        return startLength == endLength;
    }

    //本类方法，上送文件名，匹配正则表达式
    protected boolean isMatchedByFileNamePattern(String fileName) {
        boolean result = false;
        for (String filePattern : filePatterns) {
            if (isMatched(fileName, filePattern)) {
                result = true;
                break;
            }
        }
        return result;
    }

    //基础方法，上送字符串和正则表达式，校验是否匹配
    protected boolean isMatched(String input, String regExp) {
        Pattern p = Pattern.compile(regExp);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }

}
