package com.chinadovey.parking.webapps.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * POI 工具类
 * 
 * @author Joshua
 *
 */
public class POIUtil {
	private Sheet sheet = null;

	/**
	 * 
	 * @throws IOException
	 */
	public List<List<String>> readCompactCosts(String excelPath) throws IOException {
		List<List<String>> list = new ArrayList<List<String>>();
		boolean isExcel2003 = true;
		if (excelPath.matches("^.+\\.xlsx$")) {
			isExcel2003 = false;
		}
		File file = new File(excelPath);
		if (file == null || !file.exists()) {
			throw new IOException("未找到上传文件");
		}
		FileInputStream inputStream = new FileInputStream(file);
		Workbook wb = new HSSFWorkbook(inputStream);
		sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		int cells = 0;
		for (int i = 1; i <= rows-1; i++) {
			Row row = sheet.getRow(i);
			if(row == null){
				continue;
			}
			if(cells == 0){
				cells = row.getPhysicalNumberOfCells();
			}

			List<String> msgs = new ArrayList<String>();
			for (int j = 0; j <= cells; j++) {
				Cell cell = row.getCell(j);
				if(cell == null){
					msgs.add("");
					continue;
				}
				msgs.add(getValue(cell));
			}
			list.add(msgs);
		}
		return list;
	}

	/**
	 * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行
	 * 
	 * @param file
	 *            读取数据的源Excel
	 * @param ignoreRows
	 *            读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1
	 * @return 读出的Excel中数据的内容
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String[][] getData(File file, int ignoreRows) throws FileNotFoundException, IOException {
		List<String[]> result = new ArrayList<String[]>();
		int rowSize = 0;
		BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		// 打开HSSFWorkbook
		POIFSFileSystem fs = new POIFSFileSystem(in);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFCell cell = null;
		for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
			HSSFSheet st = wb.getSheetAt(sheetIndex);
			// 第一行为标题，不取
			for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
				HSSFRow row = st.getRow(rowIndex);
				if (row == null) {
					continue;
				}
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				String[] values = new String[rowSize];
				Arrays.fill(values, "");
				boolean hasValue = false;
				for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
					String value = "";
					cell = row.getCell(columnIndex);
					if (cell != null) {
						// 注意：一定要设成这个，否则可能会出现乱码
						// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
						switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_STRING:
							value = cell.getStringCellValue();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							if (HSSFDateUtil.isCellDateFormatted(cell)) {
								Date date = cell.getDateCellValue();
								if (date != null) {
									value = new SimpleDateFormat("yyyy-MM-dd").format(date);
								} else {
									value = "";
								}
							} else {
								value = new DecimalFormat("0").format(cell.getNumericCellValue());
							}
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							// 导入时如果为公式生成的数据则无值
							if (!cell.getStringCellValue().equals("")) {
								value = cell.getStringCellValue();
							} else {
								value = cell.getNumericCellValue() + "";
							}
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							value = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							value = (cell.getBooleanCellValue() == true ? "Y" : "N");
							break;
						default:
							value = "";
						}
					}
					if (columnIndex == 0 && value.trim().equals("")) {
						break;
					}
					values[columnIndex] = rightTrim(value);
					hasValue = true;
				}

				if (hasValue) {
					result.add(values);
				}
			}
		}
		in.close();
		String[][] returnArray = new String[result.size()][rowSize];
		for (int i = 0; i < returnArray.length; i++) {
			returnArray[i] = (String[]) result.get(i);
		}
		return returnArray;
	}

	/**
	 * 读取Cell
	 * 
	 * @param cell
	 * @return
	 */
	public static String readCell(Cell cell) {
		String cellvalue = null;
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，取得该Cell的Date值
					Date date = cell.getDateCellValue();
					// 把Date转换成本地格式的字符串
					cellvalue = cell.getDateCellValue().toLocaleString();
					cellvalue = rightTrim(cellvalue);
				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					Long num = new Long((long) cell.getNumericCellValue());
					cellvalue = String.valueOf(num);
					cellvalue = rightTrim(cellvalue);
				}
				break;
			}
				// 如果当前Cell的Type为STRING
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getStringCellValue().replace("", "");
				cellvalue = rightTrim(cellvalue);
				break;
			// 默认的Cell值
			default:
				cellvalue = "";
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	/**
	 * 去掉字符串右边的空格
	 * 
	 * @param str去掉字符串右边的空格
	 * @return 去掉字符串右边的空格
	 */
	public static String rightTrim(String str) {
		if (str == null) {
			return "";
		}
		int length = str.length();
		for (int i = length - 1; i >= 0; i--) {
			if (str.charAt(i) != 0x20) {
				break;
			}
			length--;
		}
		return str.substring(0, length);
	}

	/**
	 * 
	 * 导出Excel
	 * 
	 * @throws IOException
	 */
	public static void exportExcel(List<List<Object>> cells, List<String> titles, String filePath, String sheetName)
			throws IOException {
		System.err.println(filePath);
		OutputStream out = new FileOutputStream(filePath);

		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 18);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		HSSFCellStyle style2 = workbook.createCellStyle();
		// 生成一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setFontHeightInPoints((short) 10);
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		int maxSize = titles.size();
		if (maxSize > 65535) {
			maxSize = 65535;
		}
		for (int i = 0; i < maxSize; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(titles.get(i));
			cell.setCellValue(text);
		}

		int maxRow = cells.size();
		for (int i = 0; i < maxRow; i++) {
			row = sheet.createRow(i + 1);
			List<Object> cols = cells.get(i);
			int maxCol = cols.size();
			for (int j = 0; j < maxCol; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(style2);
				Object obj = cols.get(j);
				if (obj instanceof Double) {
					System.err.println("double=" + obj);
					cell.setCellValue((Double) obj);
				} else if (obj instanceof Integer) {
					System.err.println("integer=" + obj);
					cell.setCellValue((Integer) obj);
				} else if (obj instanceof Float) {
					System.err.println("float=" + obj);
					cell.setCellValue((Float) obj);
				} else if (obj instanceof String) {
					System.err.println("str=" + obj);
					cell.setCellValue((String) obj);
				} else {
					cell.setCellValue(obj.toString());
				}
			}
		}

		workbook.write(out);
	}

	private String getValue(Cell cell) {
		if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
			return String.valueOf(cell.getNumericCellValue());
		} else {
			return String.valueOf(cell.getStringCellValue());
		}
	}
}
