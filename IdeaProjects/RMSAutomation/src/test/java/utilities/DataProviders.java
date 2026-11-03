package utilities;

import org.testng.annotations.DataProvider;

import java.io.*;

public class DataProviders
{
    private static final String PRODUCT_INDEX_FILE_PATH = "./testData/lastFetchedProductIndex.txt";
    private int lastFetchedProductIndex;
    private static final String PAN_INDEX_FILE_PATH = "./testData/lastFetchedPANIndex.txt";
    private int lastFetchedPANIndex;
    @DataProvider(name = "ProductTitleData")
    public Object[][] getProductTitle() {
        lastFetchedProductIndex = readLastFetchedProductIndex();
        try {
            String path = "./testData/AutomationProductTittles.xlsx";
            ExcelUtility xlutil = new ExcelUtility(path);
            int totalrows = xlutil.getRowCount("Sheet1");
            int totalcols = xlutil.getCellCount("Sheet1", 1);

            String productData[][] = new String[1][totalcols];
            for (int j = 0; j < totalcols; j++) {
                productData[0][j] = xlutil.getCellData("Sheet1", lastFetchedProductIndex + 1, j);
            }
            lastFetchedProductIndex++;    // Increment the counter for the next call
            writeLastFetchedProductIndex(lastFetchedProductIndex);

            return productData;
        } catch (IOException e) {
            throw new RuntimeException("Error reading test data from Excel file", e);
        }
    }
    private int readLastFetchedProductIndex() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_INDEX_FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions
        }
        return 0; // Default value if the file doesn't exist or cannot be read
    }

    private void writeLastFetchedProductIndex(int index) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PRODUCT_INDEX_FILE_PATH))) {
            writer.write(Integer.toString(index));
        } catch (IOException e) {
            // Handle exceptions
        }
    }
    @DataProvider (name = "PANData")
    public Object[][] getPAN() {
        lastFetchedPANIndex = readLastFetchedPANIndex();
        try {
            String path = "./testData/Vendor-pan.xlsx";
            ExcelUtility xlutil = new ExcelUtility(path);
            int totalrows = xlutil.getRowCount("Sheet1");
            int totalcols = xlutil.getCellCount("Sheet1", 1);

            String PANData[][] = new String[1][totalcols];
            for (int j = 0; j < totalcols; j++) {
                PANData[0][j] = xlutil.getCellData("Sheet1", lastFetchedPANIndex + 1, j);
            }
            lastFetchedPANIndex++;    // Increment the counter for the next call
            writeLastFetchedPANIndex(lastFetchedPANIndex);

            return PANData;
        } catch (IOException e) {
            throw new RuntimeException("Error reading test data from Excel file", e);
        }
    }
    private int readLastFetchedPANIndex() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PAN_INDEX_FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            // Handle exceptions
        }
        return 0; // Default value if the file doesn't exist or cannot be read
    }

    private void writeLastFetchedPANIndex(int index) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PAN_INDEX_FILE_PATH))) {
            writer.write(Integer.toString(index));
        } catch (IOException e) {
            // Handle exceptions
        }
    }
}
