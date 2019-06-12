package selenium.test.framework.Utils.Data;

import com.opencsv.CSVReader;
import selenium.test.framework.Utils.CustomFileUtils;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

public class CsvDataReader {
    Properties p;

    public CsvDataReader (Properties properties){
        this.p = properties;
    }

    public Object[][] getCsvData(String fileName) {
        Reader reader = null;
        List<String[]> csvData = null;
        try {
            reader = Files.newBufferedReader(
                    Paths.get(
                            new CustomFileUtils().getResourcesPath(
                                    p.getProperty("testData.path")+
                                        fileName)));

            CSVReader csvReader = new CSVReader(reader);
            csvData = csvReader.readAll();

        } catch (IOException e) {
            e.printStackTrace();
        }
        Object[][] data = null;
        if (csvData != null){
            data = new Object[csvData.size()][];
        }
        int index = 0;
        for (String[] record : csvData) {
            data[index] = record;
            index++;
        }
        return data;
    }
}
