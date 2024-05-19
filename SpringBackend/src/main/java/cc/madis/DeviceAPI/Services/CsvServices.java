package cc.madis.DeviceAPI.Services;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
public class CsvServices implements Iterator<String[]> {

    protected String csvFilePath = "src/main/resources/devices-test.csv";
    private CSVReader csvReader;
    private Iterator<String[]> iter;

    public CsvServices() {
        this.setCsvFilePath(this.csvFilePath);
    }

    public void setCsvFilePath(String path) {
        try {
            this.csvReader = new CSVReader(new FileReader(this.csvFilePath));
            this.iter = this.csvReader.iterator();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void skip(int num) {
        try {
            this.csvReader.skip(num);
            for (var i = 0; i < num; i++) {
                this.iter.next();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean hasNext() {
        return this.iter.hasNext();
    }

    @Override
    public String[] next() {
        return this.iter.next();
    }

    @Override
    public void remove() {
        try {
            this.csvReader.close();
            this.iter.remove();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public ArrayList<String> readNext() {
        return new ArrayList(Arrays.asList(this.next()));
    }

}
