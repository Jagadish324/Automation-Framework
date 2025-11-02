//package base;
//
//import com.arc.frameworkWeb.helper.CommonHelper;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.safari.SafariDriver;
//import com.arc.frameworkWeb.utility.CONSTANT;
//
//import java.time.Duration;
//
//public class BaseClass extends CommonHelper {
//    public static WebDriver driver;
//    public BaseClass (){
//        super();
//    }
//    public WebDriver launchBrowser(){
////        InitConfig initConfig = new InitConfig();
//        if(CONSTANT.BROWSER_TYPE.equalsIgnoreCase("chrome")) {
//            driver = new ChromeDriver();
//            CONSTANT.DEVTOOLS = ((ChromeDriver) driver).getDevTools();
//        }
//        else if (CONSTANT.BROWSER_TYPE.equalsIgnoreCase("firefox")) {
//            driver = new FirefoxDriver();
//            CONSTANT.DEVTOOLS = ((FirefoxDriver) driver).getDevTools();
//        }
//        else if (CONSTANT.BROWSER_TYPE.equalsIgnoreCase("edge")){
//            driver = new EdgeDriver();
//            CONSTANT.DEVTOOLS = ((EdgeDriver) driver).getDevTools();
//        }
//        else if(CONSTANT.BROWSER_TYPE.equalsIgnoreCase("safari"))
//        {
//            driver = new SafariDriver();
//        }
//        new CommonHelper(driver);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(CONSTANT.IMPLICIT_WAIT));
//        return  driver;
//    }
//}
