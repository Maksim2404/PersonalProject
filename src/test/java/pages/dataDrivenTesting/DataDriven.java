package pages.dataDrivenTesting;

import base.BasePage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven extends BasePage {
    public DataDriven(WebDriver driver) {
        super(driver);
    }

    public ArrayList<String> getData(String testCaseName, String sheetName) throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();


        /*fileInputStream argument should be initialized before using XSSFWorkbook*/
        FileInputStream fileInputStream = new FileInputStream("C:\\Users\\Maksim Meleshkin\\Downloads\\Test.xlsx");
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);

        int numberOfSheets = xssfWorkbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            if (xssfWorkbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(i);

                /*Task1 - Identify Testcases column by scanning the entire 1st row*/

                Iterator<Row> rows = xssfSheet.iterator(); /*Sheet is a collection of rows!*/
                Row firstRow = rows.next();

                Iterator<Cell> cellIterator = firstRow.cellIterator(); /*Row is a collection of cells!*/
                int k = 0;
                int column = 0;

                while (cellIterator.hasNext()) {

                    Cell value = cellIterator.next();
                    if (value.getStringCellValue().equalsIgnoreCase("TestCases")) {

                        column = k;
                    }

                    k++;
                }
                System.out.println(column);

                /*Task2 - Once column is identified then scan entire testcase column to identify purchase testcase row*/

                while (rows.hasNext()) {

                    Row r = rows.next();
                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {

                        /*Task3 - after you grab purchase testcase row - pull all the data of that row and feed into*/
                        Iterator<Cell> cv = r.cellIterator();
                        while (cv.hasNext()) {

                            Cell c = cv.next();
                            if (c.getCellType() == CellType.STRING) {

                                arrayList.add(c.getStringCellValue());
                            } else {
                                arrayList.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return arrayList;
    }
}
