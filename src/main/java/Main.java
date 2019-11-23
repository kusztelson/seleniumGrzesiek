import com.opencsv.CSVWriter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//comment the above line and uncomment below line to use Chrome
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.opencsv.CSVReader;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    private static final String SAMPLE_CSV_FILE_PATH = System.getProperty("user.dir") +
            "/src/main/resources/" +
            //"matematykaAdresKodInput.csv";
            "chemiaAdresKod.csv";
    private static final String STRING_ARRAY_SAMPLE = System.getProperty("user.dir") +
            //"/matematykaAdresKodOutput"
            "/chemiaAdresKodOutput"
            + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"))
            + ".csv";


    static CSVWriter csvWriter;
    static CSVReader csvReader;

    public static void main(String[] args) throws IOException, InterruptedException {
        String[] inputLine;
        initCSVWriter();
        initCSVReader();

        while((inputLine = getNextInput()) != null) {

        //for(int i = 0; i < 20; i++) {
            //inputLine = getNextInput();
            String[] finalInputLine = inputLine;
            System.out.println(java.time.LocalDateTime.now());

                new Thread(() -> {
                        String desiredText = doSeleniumStuff(finalInputLine[0], finalInputLine[1]);
                        csvWriter.writeNext(new String[]{ desiredText, finalInputLine[0], finalInputLine[1] });
                }
                ).start();

            Thread.sleep(10000);
        }
        csvWriter.close();
    }

    private static void initCSVWriter() throws IOException {
        Writer writer = Files.newBufferedWriter(Paths.get(STRING_ARRAY_SAMPLE));
        csvWriter = new CSVWriter(writer,
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
    }

    private static void initCSVReader() throws IOException {
        Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
        csvReader = new CSVReader(reader, ';');
    }

    private static String[] getNextInput() throws IOException {
        // Reading Records One by One in a String array
        String[] nextRecord;
        if ((nextRecord = csvReader.readNext()) != null) {
            return nextRecord;
        }
        else {
            return null;
        }
    }

    private static String doSeleniumStuff(String url, String code) {
        final int THIRD_ELEMENT = 2;

//        System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe");
//        WebDriver driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, 240);

        String baseUrl = url;

        // launch Fire fox and direct it to the Base URL
        driver.get(baseUrl);

        WebElement sideMenu = driver.findElement(By.className("switch--sidebar"));
        sideMenu.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("course-list_node")));

        List<WebElement> sideMenuList = driver.findElements(By.className("course-list_node"));
        String desiredText = sideMenuList.get(THIRD_ELEMENT).getAttribute("innerText");
        System.out.println(desiredText);

        driver.close();

        return desiredText;

    }

}
