package com.csii.upp.batch.appl.eaccount;

import java.util.Map;

import com.csii.upp.batch.appl.base.BaseAppl;

/**
*@Author chenchao 
*@Description 统计对账结果
**/
public class ClassifyCheckResult extends BaseAppl {

	@Override
	protected void runAppl(Object entry, Map<String, Object> argMaps) throws Exception {
//		final Date checkDate = this.getCheckDate(argMaps);
//		final String checkdataFlag = StringUtil.parseObjectToString(argMaps.get(Dict.DZBZ));
////		final int dzkx = StringUtil.parseInteger(argMaps.get(Dict.DZKX));//最大宽限日
//		getTransactionTemplate().execute(new TransactionCallback() {
//			@Override
//			public Object doInTransaction(TransactionStatus arg0) {
//				String module = "";//判断对账结果查询模块
//				if (checkdataFlag.equals("ECORE")) {
//					module = "1";
//				}else if (checkdataFlag.equals("CCORE")) {
//					module = "2";
//				}else if (checkdataFlag.equals("IYBS")) {
//					module = "3";
//				}else if (checkdataFlag.equals("UNIONPAYEct")) {
//					module = "4";
//				}else if (checkdataFlag.equals("UNIONPAYDS")) {
//					module = "4";
//				}else if (checkdataFlag.equals("UNIONPAY")) {
//					module = "5";
//				}
//				try {
//					//查询T日对账结果，并插入对账结果表
//					Map<String, Object> param = new HashMap<String,Object>();
//					param.put("checkdataflag", checkdataFlag);
//					param.put("checkDate", checkDate);
//					List<String> innerList = InnerfundtransHistExtendDAO.getcheckresult(param);
//					Checkresultrecord record = new Checkresultrecord();
//					//判断是否有对账数据
//					if (innerList != null && innerList.size() >0) {
//						//如果存在对账不平则对账状态为对账不平，如果存在未对账，对账结果就是未对账，如果都不存在则对账平
//						if (innerList.contains("3") || innerList.contains("4")) {
//							record.setCheckdataflag(module);
//							record.setCheckresult("2");//对账不平
//							record.setStartdate(checkDate);
//							record.setEnddate(checkDate);
//							record.setDeptnbr("999000");
//							CheckresultrecordDAO.insertSelective(record);
//						} else if (innerList.contains("2")) {
//							record.setCheckdataflag(module);
//							record.setCheckresult("3");//未对账
//							record.setStartdate(checkDate);
//							record.setEnddate(checkDate);
//							record.setDeptnbr("999000");
//							CheckresultrecordDAO.insertSelective(record);
//						} else if (innerList.contains("1")) {
//							record.setCheckdataflag(module);
//							record.setCheckresult("1");//对账平
//							record.setStartdate(checkDate);
//							record.setEnddate(checkDate);
//							record.setDeptnbr("999000");
//							CheckresultrecordDAO.insertSelective(record);
//						}
//					}
//					//最大宽限日之后查询T-n日前对账结果，更新原对账结果
////					param.put("checkDate", DateUtil.addDate(checkDate, -dzkx));
////					List<String> innerList2 = InnerfundtransHistExtendDAO.getcheckresult(param);
////					Checkresultrecord record2 = new Checkresultrecord();
////					CheckresultrecordExample example = new CheckresultrecordExample();
////					example.createCriteria().andCheckdataflagEqualTo(module).
////						andStartdateEqualTo(DateUtil.addDate(checkDate, -dzkx));
////					if (innerList2 != null && innerList2.size() >0) {
////						if (innerList2.contains("3") || innerList2.contains("4")) {
////							record2.setCheckresult("2");//对账不平
////							CheckresultrecordDAO.updateByExampleSelective(record2, example);
////						} else if (innerList2.contains("2")) {
////							record2.setCheckresult("3");//未对账
////							CheckresultrecordDAO.updateByExampleSelective(record2, example);
////						} else if (innerList2.contains("1")) {
////							record2.setCheckresult("1");//对账平
////							CheckresultrecordDAO.updateByExampleSelective(record2, example);
////						}
////					}
//				} catch (Exception e) {
//					throw new PeRuntimeException(e);
//				}
//				return null;
//			}
//		});
//		
	}

	
}

