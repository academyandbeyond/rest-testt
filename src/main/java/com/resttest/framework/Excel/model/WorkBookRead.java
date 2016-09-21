package com.resttest.framework.Excel.model;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class WorkBookRead implements ApplicationContextAware{

	private static int maxRow;
	private LinkedMap mapOfExcelBaseObjects;
	private String excelFilePath;
	private TestCase tc;
	private Payload payload;
	private Header header;
	private Scenario scenario;
	
	ApplicationContext context = null;
	
	public void init() throws Exception {
		maxRow = 0;
		mapOfExcelBaseObjects = new LinkedMap();
		excelFilePath = "Workbook.xlsx";		
		extractExcelData();

	}

	public void extractExcelData() throws InvalidFormatException, IOException {

		InputStream inp = ClassLoader.getSystemResourceAsStream(excelFilePath);
		Workbook workbook = new XSSFWorkbook(inp);
		Sheet sheet = workbook.getSheetAt(0);

		maxRow = sheet.getLastRowNum();
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();

			if (row.getRowNum() == 0) {
					
				continue;
			}

			if (null != row.getCell(0)) {

				getTCObject(row, true);
				
				
			} else {

				if (row.getRowNum() < maxRow) {

					TestCase tcu = getTCObject(row, false);

					TestCase tm = (TestCase) mapOfExcelBaseObjects.get(mapOfExcelBaseObjects.lastKey());

					tm.getlistOfScenario().add(tcu.getScenario());

				}
			}
		}

		workbook.close();
		inp.close();
	}

	
	
	public TestCase getTCObject(Row nextrow, boolean val) {
		
		populatePrototypeReference();
		
		Iterator<Cell> cellIterator = nextrow.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();

			Object value = cellToObject(cell);
			int index = cell.getColumnIndex();

			switch (index) {
			case 0:

				if (value.toString() != null) {
					tc.setKey((String) value);
					break;
				}

			case 1:
				tc.setTestCase(value.toString());
				break;
			case 2:
				scenario.setId(value.toString());

				break;
			case 3:
				scenario.setUrl(value.toString());
				break;
			case 4:
				if (value instanceof String) {
					value = value.toString();
					scenario.setMethod(value.toString());
				}

				break;
			case 5:
				scenario.setValidate(value.toString());
				break;
			case 6:
				scenario.setCommand(value.toString());

				break;
			case 7:
				payload.setAppCompatibility(value.toString());
				break;
			case 8:
				payload.setAppName(value.toString());
				break;
			case 9:
				payload.setAppVersion(value.toString());
				break;
			case 10:
				payload.setDeviceId(value.toString());
				break;

			case 11:
				payload.setDeviceOs(value.toString());
				break;

			case 12:
				double j = (Double) value;
				payload.setDeviceOsVersion(j);
				break;

			case 13:
				payload.setDeviceType(value.toString());
				break;

			case 14:
				header.setApikey(value.toString());
				break;

			case 15:
				header.setContenttype(value.toString());
				break;

			case 16:
				header.setAccept(value.toString());
				break;

			case 17:
				header.setAccepttype(value.toString());
				break;

			case 18:
				header.setChanneltype(value.toString());
				break;
			case 19:
				scenario.setResponseattribute(value.toString());
				break;

			case 20:
				double k = (Double) value;

				scenario.setExpected(k);
				break;
			case 21:
				scenario.setPrimary(value.toString());
				break;
			}
		}

		scenario.setPayload(payload);
		scenario.setHeader(header);
		tc.setScenario(scenario);

		if (val == true) {
			tc.getlistOfScenario().add(scenario);
			mapOfExcelBaseObjects.put(tc.getKey(), tc);
		}
		
		
		return tc;

	}

	public void populatePrototypeReference(){
	
		tc = (TestCase) context.getBean("tc");
		payload = (Payload) context.getBean("payload"); ;
		header = (Header)context.getBean("header"); ;
		scenario = (Scenario) context.getBean("scenario");;
		
	}

	
	private Object cellToObject(Cell cell) {
		int type = cell.getCellType();
		if (type == Cell.CELL_TYPE_STRING) {
			return cleanString(cell.getStringCellValue());
		}

		if (type == Cell.CELL_TYPE_BOOLEAN) {
			return cell.getBooleanCellValue();
		}

		if (type == Cell.CELL_TYPE_NUMERIC) {
			if (cell.getCellStyle().getDataFormatString().contains("%")) {
				return cell.getNumericCellValue() * 100;
			}
			return numeric(cell);
		}

		if (type == Cell.CELL_TYPE_FORMULA) {
			switch (cell.getCachedFormulaResultType()) {
			case Cell.CELL_TYPE_NUMERIC:
				return numeric(cell);
			case Cell.CELL_TYPE_STRING:
				return cleanString(cell.getRichStringCellValue().toString());
			}
		}

		return null;
	}

	private Object numeric(Cell cell) {
		if (HSSFDateUtil.isCellDateFormatted(cell)) {
			return cell.getDateCellValue();
		}
		return Double.valueOf(cell.getNumericCellValue());
	}

	public LinkedMap getMapOfExcelBaseObjects() {
		return mapOfExcelBaseObjects;
	}

	private String cleanString(String str) {
		return str.replace("\n", "").replace("\r", "");
	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		// TODO Auto-generated method stub
		context = arg0;
		
	}

}
