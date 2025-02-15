package DataProviders;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchCriteria {
    @DataProvider(name = "searchCriteria")
    public static Object[][] fetchData() {
        String filePath = "src/test/resources/testdata/searchCriteria.csv"; // Adjust the path as needed
        List<Object[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {
                if (isHeader) { // Skip the header row
                    isHeader = false;
                    continue;
                }

                String[] columns = line.split(","); // Adjust delimiter if necessary
                if (columns.length > 1) {
                    // Exclude first column and store remaining
                    Object[] rowData = new Object[columns.length - 1];
                    System.arraycopy(columns, 1, rowData, 0, columns.length - 1);
                    dataList.add(rowData);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataList.toArray(new Object[0][]);
    }

}
