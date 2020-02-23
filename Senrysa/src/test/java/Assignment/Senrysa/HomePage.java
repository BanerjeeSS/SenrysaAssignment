package Assignment.Senrysa;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class HomePage extends Base {
	
	public static XSSFWorkbook workbook=null;
    public static XSSFSheet worksheet=null;
    public static XSSFRow Row=null;
    public static DataFormatter formatter= new DataFormatter();

	@BeforeTest
	public void HomePagenavigate() throws IOException {
		driver=initializeDriver();
		driver.manage().window().maximize();
		driver.get("http://nextdoorhub.com");
		System.out.println(driver.getTitle());

}

	@Test
	public void assertTitle() throws InterruptedException {
		String actualTitle= driver.getTitle();
		//driver.getTitle().contains("NextDoorHub");
		//driver.getTitle().get
		// Title keeps on changing, please change the expected Title
		String expectedTitle= "NextDoorHub-Online Shopping Site in India- Online Shopping for Women, Men, Kids";
		Assert.assertEquals(actualTitle,expectedTitle);
		if(actualTitle.equals(expectedTitle)) {
		System.out.println("Pass");
	}
		else {
			System.out.println("Fail");
		}
	Thread.sleep(10000);
		}
	@Test
	public void signIn() throws InterruptedException {
		
		LandingPage l=new LandingPage(driver);
		l.getLogIn().click();
		Thread.sleep(10000);
	}
	
	@Test(dataProvider="ReadVariant")
	public void logIn(String Username,String Password) throws InterruptedException {
		Thread.sleep(10000);
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(10000);
		String MainWindow=driver.getWindowHandle();
		 Set<String> s1=driver.getWindowHandles();		
	        Iterator<String> i1=s1.iterator();		
	        		
	        while(i1.hasNext())			
	        {		
	            String ChildWindow=i1.next();		
	            		
	            if(!MainWindow.equalsIgnoreCase(ChildWindow))			
	            {    		
	                 
	                    // Switching to Child window
	                    driver.switchTo().window(ChildWindow);
		lp.getEmail().sendKeys(Username);
		lp.getPassword().sendKeys(Password);
		lp.clickSubmit().click();
	            }
	        }
	        driver.switchTo().window(MainWindow);
	}
	
	@DataProvider
	public static Object[][] ReadVariant() throws IOException
	{
		FileInputStream fileInputStream= new FileInputStream("C:\\Users\\user\\Desktop\\Demodata.xlsx"); //Excel sheet file location get mentioned here
        XSSFWorkbook workbook = new XSSFWorkbook (fileInputStream); //get my workbook 
        XSSFSheet worksheet=workbook.getSheet("testdata");// get my sheet from workbook
        XSSFRow Row=worksheet.getRow(0);     //get my Row which start from 0   
     
        int RowNum = worksheet.getPhysicalNumberOfRows();// count my number of Rows
        int ColNum= Row.getLastCellNum(); // get last ColNum 
         
        Object Data[][]= new Object[RowNum-1][ColNum]; // pass my  count data in array
         
            for(int i=0; i<RowNum-1; i++) //Loop work for Rows
            {  
                XSSFRow row= worksheet.getRow(i+1);
                 
                for (int j=0; j<ColNum; j++) //Loop work for colNum
                {
                    if(row==null)
                        Data[i][j]= "";
                    else
                    {
                        XSSFCell cell= row.getCell(j);
                        if(cell==null)
                            Data[i][j]= ""; //if it get Null value it pass no data 
                        else
                        {
                        	
                            String value=formatter.formatCellValue(cell);
                            Data[i][j]=value; //This formatter get my all values as string i.e integer, float all type data value
                        }
                    }
                }
            }
 
        return Data;
    }

	@Test
	public void steps() {
		driver.get("https://www.google.com");
		driver.navigate().back();
		System.out.println(driver.getCurrentUrl());
		driver.navigate().forward();
		driver.navigate().refresh();
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
		driver.quit();
	}
}
