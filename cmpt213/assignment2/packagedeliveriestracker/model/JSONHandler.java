package cmpt213.assignment2.packagedeliveriestracker.model;

import cmpt213.assignment2.packagedeliveriestracker.gson.extras.RuntimeTypeAdapterFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JSONHandler {

    /**
     * Reads the content from json file and loads the packageList accordingly.
     * @param theFile is json file name.
     */
    public static List<Package> readFromJSON(File theFile)
    {
        try
        {
            FileReader fileReader = new FileReader(theFile);
            Type type = new TypeToken<ArrayList<Package>>(){}.getType();
            RuntimeTypeAdapterFactory<Package> packageAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Package.class, "type")
                    .registerSubtype(Book.class,"Book")
                    .registerSubtype(Perishable.class, "Perishable")
                    .registerSubtype(Electronic.class,"Electronic");


            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                    new TypeAdapter<LocalDateTime>() {
                        @Override
                        public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                            jsonWriter.value(localDateTime.toString());
                        }
                        @Override
                        public LocalDateTime read(JsonReader jsonReader) throws IOException {
                            return LocalDateTime.parse(jsonReader.nextString());
                        }
                    }).registerTypeAdapterFactory(packageAdapterFactory)
                    .create();
            //fileReader.close();
            return gson.fromJson(fileReader, type);

        } catch (FileNotFoundException e) {
            System.err.println("Error in creating a FileReader object.");
        } catch (IOException e) {
            System.err.println("Error in closing the file.");
        }
        return null;
    }

    /**
     * writes the content of current packageList to json.
     * @param theFile is json file name.
     */
    public static void writeToJSON(File theFile,List<Package> packageList)
    {
        try {
            FileWriter fileWriter = new FileWriter(theFile);
            RuntimeTypeAdapterFactory<Package> packageAdapterFactory = RuntimeTypeAdapterFactory
                    .of(Package.class, "type")
                    .registerSubtype(Book.class, "Book")
                    .registerSubtype(Perishable.class, "Perishable")
                    .registerSubtype(Electronic.class,"Electronic");
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                    new TypeAdapter<LocalDateTime>() {
                        @Override
                        public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
                            jsonWriter.value(localDateTime.toString());
                        }
                        @Override
                        public LocalDateTime read(JsonReader jsonReader) throws IOException {
                            return LocalDateTime.parse(jsonReader.nextString());
                        }
                    }).registerTypeAdapterFactory(packageAdapterFactory)
                    .create();
            gson.toJson(packageList,fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error in writing the file.");
        }
    }
}
