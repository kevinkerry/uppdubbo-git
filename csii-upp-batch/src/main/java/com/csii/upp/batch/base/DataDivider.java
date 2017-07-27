package com.csii.upp.batch.base;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据分组器
 * 
 * @author lubiao
 * 
 */
public class DataDivider {
	/**
	 * 指定分组数目，然后根据分组数目对传入数据进行分组操作
	 * 
	 * @param data
	 *            待分组数据
	 * @param groupNbr
	 *            需要分组的数目
	 * @return
	 */
	public static List<Object[]> divideByGroupNbr(Object[] data, int groupNbr) {
		int length = data.length;

		if (length <= groupNbr) {
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(data);
			return list;
		} else {
			List<List<Object>> list = new ArrayList<List<Object>>();
			int part = length / groupNbr;// 每一份的数量
			int remainder = length % groupNbr;// 未参与分组的数据量

			for (int i = 0; i < groupNbr; i++) {
				List<Object> temp = new ArrayList<Object>();
				for (int j = i * part; j < (i + 1) * part; j++) {
					temp.add(data[j]);
				}
				list.add(temp);
			}
			if (remainder > 0) {// 对剩余数据进行分配
//				for (int k = 1; k <= remainder; k++) {
//					list.get(k - 1).add(data[length - k]);
//				}
				for (int k = 0; k < remainder; k++) {
				list.get(k).add(data[part*groupNbr+k]);
			}
			}

			List<Object[]> result = new ArrayList<Object[]>();
			for (List<Object> item : list) {
				result.add(item.toArray());
			}
			return result;
		}
	}

	/**
	 * 指定每一份的配额，然后根据配额对传入数据进行拆分操作
	 * 
	 * @param data
	 *            待分组数据
	 * @param quota
	 *            每一份的配额
	 * @return
	 */
	public static List<Object[]> divideByQuota(Object[] data, int quota) {

		int length = data.length;

		if (length <= quota) {
			List<Object[]> list = new ArrayList<Object[]>();
			list.add(data);
			return list;
		} else {
			List<List<Object>> list = new ArrayList<List<Object>>();
			int part = quota;// 每一份的数量
			int groupNbr = length / quota;// 分组数量
			int remainder = length % quota;// 未参与分组的数据数量

			for (int i = 0; i < groupNbr; i++) {
				List<Object> temp = new ArrayList<Object>();
				for (int j = i * part; j < (i + 1) * part; j++) {
					temp.add(data[j]);
				}
				list.add(temp);
			}
			if (remainder > 0) {// 把剩余数据进行单独分组
				List<Object> temp = new ArrayList<Object>();
//				for (int k = 1; k <= remainder; k++) {
//					temp.add(data[length - k]);
				for (int k = 0; k < remainder; k++) {
					temp.add(data[part*groupNbr+k]);
				}
				list.add(temp);
			}

			List<Object[]> result = new ArrayList<Object[]>();
			for (List<Object> item : list) {
				result.add(item.toArray());
			}
			return result;
		}
	}
}
