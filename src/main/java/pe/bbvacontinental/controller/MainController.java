package pe.bbvacontinental.controller;

import java.io.OutputStream;
import java.util.Iterator;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MainController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String readExcel(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
		System.out.println(file.getOriginalFilename());

		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

			XSSFSheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rowIterator = sheet.iterator();

			while (rowIterator.hasNext()) {
				Row currentRow = rowIterator.next();

				System.out.println(currentRow.getCell(0).toString());
				System.out.println(currentRow.getCell(1).toString());
				System.out.println(currentRow.getCell(2).toString());
			}
			
			XSSFWorkbook newWorkBook = new XSSFWorkbook();
			newWorkBook.createSheet("Hoja Prueba");
			
			XSSFSheet newSheet = newWorkBook.getSheet("Hoja Prueba");
			newSheet.createRow(0);
			Row newRow = newSheet.getRow(0);
			Cell cell = newRow.createCell(0);
			cell.setCellValue(1);
			//newWorkBook.getSheet("Hoja Prueba").createRow(0);
			//newWorkBook.getSheet("Hoja Prueba").getRow(0).getCell(0).setCellValue(1);
			
			response.resetBuffer();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=test.xls");
			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "private");
			response.setDateHeader("Expires", 1);
			
			OutputStream out = response.getOutputStream();
			newWorkBook.write(out);
			
			out.flush();
			out.close();
			newWorkBook.close();

			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "index";

	}
}
