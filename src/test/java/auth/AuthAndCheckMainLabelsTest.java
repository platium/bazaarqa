package auth;


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import util.PropertiesUtil;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


public class AuthAndCheckMainLabelsTest
{
    private WebDriver driver;
    private StringBuffer verificationErrors = new StringBuffer();


    @Before
    public void setUp() throws Exception
    {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait( 30, TimeUnit.SECONDS );
    }


    @Test
    public void testBasicForm() throws Exception
    {
        driver.get( String.format( "https://%s/login", PropertiesUtil.getByKey( "url" )));
        driver.findElement( By.linkText( "GO AHEAD" ) ).click();
        driver.findElement( By.name( "email_or_username" ) ).click();
        driver.findElement( By.name( "email_or_username" ) ).clear();
        driver.findElement( By.name( "email_or_username" ) ).sendKeys( PropertiesUtil.getByKey( "user.email" ) );
        driver.findElement( By.id( "loginPassword" ) ).clear();
        driver.findElement( By.id( "loginPassword" ) ).sendKeys( PropertiesUtil.getByKey( "user.password" ) );
        driver.findElement( By.id( "subutai-login" ) ).click();
        assertEquals( "Environments", driver.findElement(
                By.xpath( "(.//*[normalize-space(text()) and normalize-space(.)='TOOLS'])[1]/following::span[1]" ) )
                                            .getText() );
        assertEquals( "AppScale", driver.findElement( By.xpath(
                "(.//*[normalize-space(text()) and normalize-space(.)='Environments'])[1]/following::span[1]" ) )
                                        .getText() );
        assertEquals( "War room", driver.findElement(
                By.xpath( "(.//*[normalize-space(text()) and normalize-space(.)='AppScale'])[1]/following::span[1]" ) )
                                        .getText() );
        assertEquals( "Peers", driver.findElement(
                By.xpath( "(.//*[normalize-space(text()) and normalize-space(.)='War room'])[2]/following::span[1]" ) )
                                     .getText() );
        driver.findElement( By.xpath(
                "(.//*[normalize-space(text()) and normalize-space(.)='Organizations'])[2]/following::span[1]" ) )
              .click();
        assertEquals( "Search:", driver.findElement( By.xpath(
                "(.//*[normalize-space(text()) and normalize-space(.)='Invitations'])[1]/following::label[1]" ) )
                                       .getText() );
    }


    @After
    public void tearDown() throws Exception
    {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if ( !"".equals( verificationErrorString ) )
        {
            fail( verificationErrorString );
        }
    }
}
