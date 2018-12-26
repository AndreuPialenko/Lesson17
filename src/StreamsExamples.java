import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StreamsExamples {
    public static void justRead() {
        FileInputStream Fis = null;
        try {
//            Fis = new FileInputStream("test/test.txt");

            Fis = new FileInputStream("C:\\Users\\Java Core Student 1\\IdeaProjects\\Lesson17\\test\\test.txt");

            System.out.println("file size in bytes: " + Fis.available());

            int content;

            while ((content = Fis.read()) != -1) {    //-1 выводится в конце файла
                System.out.println((char) content);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (Fis != null) {
                try {
                    Fis.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
    }

    public static void readWithResources() {
        try (FileInputStream Fis = new FileInputStream("test/test1.txt")) {

            System.out.println("file size" + Fis.available());
            int content;

            while ((content = Fis.read()) != -1) {
                System.out.println((char) content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
//      justRead();
//        readWithResources();
//        readAndWrite();
//        readAndWriteWithoutClosing();
//        bufferedInputStream();
//        bufferedInputStreamWithResources();
//        getLinesFromFile("test/test.txt");
        writeByLines();

//        List<String> file = getLinesFromFile("test/test.txt");
//        for (String s: file){
//            System.out.println(s);
//        }
    }

    public static void readAndWrite(){
        try (FileInputStream Fis = new FileInputStream("test/test.txt");
             FileOutputStream Fos = new FileOutputStream("test/result.txt")){
         int content;
         while ((content = Fis.read()) != -1){
             System.out.println((char) content);
             Fos.write(content);
         }
        }catch (IOException e){
                 e.printStackTrace();
        }
    }

    public static void readAndWriteWithoutClosing(){
        FileInputStream Fis = null;
        FileOutputStream Fos = null;

        try {
            Fis = new FileInputStream("test/test.txt");
            Fos = new FileOutputStream("test/result.txt");

            int content;

            while ((content = Fis.read()) != -1){
                System.out.println((char) content);
                Fos.write(content);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void bufferedInputStream(){
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bus = null;

        try {
            inputStream = new FileInputStream("test/test.txt");
            bis = new BufferedInputStream(inputStream);
            bus = new BufferedOutputStream(new FileOutputStream("test/buff_res.txt"));

            while (bis.available() > 0){
                char c = (char) bis.read();
                System.out.println("char: " + c);
                bus.write(c);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if(inputStream != null && bis != null && bus != null){
                try {
                    inputStream.close();
                    bis.close();
                    bus.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bufferedInputStreamWithResources(){
        try(FileInputStream Fis = new FileInputStream("test/test.txt");
        BufferedInputStream bus = new BufferedInputStream(Fis);
        FileOutputStream Fos = new FileOutputStream("test/buf_res.txt");
        BufferedOutputStream bos = new BufferedOutputStream(Fos)){

        while (bus.available() > 0){
            char c = (char) bus.read();
            System.out.println("char: " + c);
            bos.write(c);
        }

        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public static List<String> getLinesFromFile(String filePath){
        File file = new File(filePath);
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while ((line = br.readLine()) != null){
                result.add(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;


    }

    public static void writeByLines(){ // желательно путь или массив добовлять во входные параметры
        List<String> Strings = new ArrayList<>();

        Strings.add("one");
        Strings.add("two");
        Strings.add("three");

        BufferedWriter writer =  null;

        try {
            writer = new BufferedWriter(new FileWriter("test/buff_string_res.txt"));
            for (String s: Strings){
                writer.write(s);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if (writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}

