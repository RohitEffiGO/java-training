import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvReader{
    public String [] readColumnFromCsv(String filePath,String fileName,String columnName) throws IOException{
        /*
         * This method reads data from csv file by taking file path, file name and column name as input.
         * Remeber to pass file path as 'C:\\Windows\\System32' and pass file name as 'file.csv'
         */
        
        List<String> listHolder = new ArrayList<>();
        String fullPath = filePath + "\\" + fileName;
        BufferedReader reader = null;
        String line = "";

        try{
            reader = new BufferedReader(new FileReader(fullPath));
            String[] columns = reader.readLine().split(",");

            Integer colInteger = 0;

            for(String col:columns){
                if(col.startsWith(columnName)){
                    break;
                }
                colInteger++;
            }

            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                listHolder.add(row[colInteger]);
            }
        } catch(FileNotFoundException error){
            // replace by logger.
            System.out.println("file not found");
        } catch (Exception error){
            error.printStackTrace();
        }finally{
            reader.close();
        }

        return listHolder.toArray(new String[0]);
    }

    public String [] readColumnFromCsv(String filePath,String fileName,int columnIndex) throws IOException{
        List<String> listHolder = new ArrayList<>();
        String fullPath = filePath + "\\" + fileName;
        BufferedReader reader = null;
        String line =   "";

        try{
            reader = new BufferedReader(new FileReader(fullPath));

            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                listHolder.add(row[columnIndex]);
            }
        } catch (Exception error){
            error.printStackTrace();
        } finally{
            reader.close();
        }

        return listHolder.toArray(new String[0]);
    }

    public String readCellFromCsv(String filePath, String fileName, String columnName, String cellValue) throws IOException{
        String [] columnData = readColumnFromCsv(filePath, fileName, columnName);
        Optional<String> result = Arrays.stream(columnData).filter(data ->data.equals(cellValue)).findFirst();
        return (result.isPresent())?result.get():new String("Value does not exists.");
    }

    public String  readCellFromCsv(String filePath, String fileName, Integer columnIndex, Integer rowIndex) throws IOException{
        return readColumnFromCsv(filePath, fileName, columnIndex)[rowIndex];
    }

    public static void main(String[] args) throws IOException {
        CsvReader csvObj = new CsvReader();

        System.out.println(csvObj.readCellFromCsv("test\\inside", "test_data.csv", "Buddy", "Shalini"));
        System.out.println(csvObj.readCellFromCsv("test\\inside", "test_data.csv", 3, 1));

    }
}