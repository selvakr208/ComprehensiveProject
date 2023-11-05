package assignment;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

    public static void main(String[] args) {
        try {
           // FileInputStream fis = new FileInputStream("./utilities/Employee_Details.xlsx");
            FileInputStream fis = new FileInputStream("./src/main/resources/Employee_Details.xlsx");
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            System.out.println("EMP_No " + "EMP_Name " + "EMP_Salary " + "EMP_Designation " + "EMP_Department ");
            for (Row row : sheet) {
                if (row.getRowNum() > 0) {
                    int empNo = (int) row.getCell(0).getNumericCellValue();
                    String empName = row.getCell(1).getStringCellValue();
                    double empSalary = row.getCell(2).getNumericCellValue();
                    String empDesignation = row.getCell(3).getStringCellValue();
                    String empDepartment = row.getCell(4).getStringCellValue();
                    System.out.print(empNo + "   " + empName + "   " + empSalary + "   " + empDesignation + "   " + empDepartment);
                    System.out.println();
                }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
