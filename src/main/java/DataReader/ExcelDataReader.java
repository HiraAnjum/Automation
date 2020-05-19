package DataReader;


import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;





public class ExcelDataReader {
    public Map<String, String> getRowDataMap(String InputFile, int SheetNumber, int RowNum) {

        int cellTypeId;
        int j = 0;
        // XSSFSheet sheet1;

        //Sheet sheet = null;
        Map<String, String> rowdatamap = null;
        FileInputStream fis = null;
        // Object[][] dataset = null;
        Workbook workbook = null;
        XSSFSheet sheet1 = null;

        // Creating index map
        Map<String, Integer> index = new HashMap<String, Integer>();
        try {
            fis = new FileInputStream(InputFile);
            workbook = new XSSFWorkbook(fis);
            //sheet = (Sheet) workbook.getSheet(sheetName);
            sheet1 = (XSSFSheet) workbook.getSheetAt(SheetNumber);

            workbook.close();

            // mapping column index with column name.
            Row firstRow = sheet1.getRow(0);
            for (Cell cell : firstRow) {
                index.put(cell.getStringCellValue(), j);
                j++;
            }



            //running for loop for each excel row and storing values in map

            //initialize excel row map
            rowdatamap = new HashMap<String, String>();
            Row rowData = sheet1.getRow(RowNum);

            for (String key : index.keySet()) {
                int columnnum = (Integer) index.get(key);

                if (rowData.getCell(columnnum) != null)
                {

                    cellTypeId = rowData.getCell(columnnum).getCellType();
                    if (cellTypeId == 0) {
                        //DecimalFormat df = new DecimalFormat();
                        //rowdatamap.put(key, df.format(rowData.getCell(columnnum).getNumericCellValue()));
                        int i = (int)rowData.getCell(columnnum).getNumericCellValue();
                        String strcellvalue = String.valueOf(i);
                        rowdatamap.put(key, strcellvalue);
                    }
                    else if (cellTypeId == 1) {
                        rowdatamap.put(key, rowData.getCell(columnnum).toString());
                    }
                }
            }


        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowdatamap;
    }
}





