import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {
    private List<Purchase> logJournal = new ArrayList<>();
    private File file;

    public ClientLog(File file) throws IOException {
        this.file = file;
        if(!file.exists()){
            file.createNewFile();
        }
    }

    public void log(int productNum, int amount){
        logJournal.add(new Purchase(productNum, amount));
    }

    public void exportAsCSV() throws Exception{
        try(FileWriter writer = new FileWriter(file, true)){
            StatefulBeanToCsv<Purchase> csv =
                    new StatefulBeanToCsvBuilder<Purchase>(writer)
                    .withSeparator(',')
                    .withMappingStrategy(getStrategy())
                    .build();
            csv.write(logJournal);
        }
    }

    private static ColumnPositionMappingStrategy<Purchase> getStrategy(){
        ColumnPositionMappingStrategy<Purchase> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Purchase.class);
        strategy.setColumnMapping("id","quantity");
        return strategy;
    }

}
