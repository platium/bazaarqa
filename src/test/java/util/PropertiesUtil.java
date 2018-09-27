package util;


import java.io.File;
import java.io.FileReader;
import java.util.Properties;


public class PropertiesUtil
{

    public static String getByKey( String key )
    {
        String value = "";
        try
        {
            Properties p = new Properties();
            p.load( new FileReader(
                    new File( System.getProperty( "user.dir" ) + "/src/test/java/resources/config.properties" ) ) );
            value = p.getProperty( key );
        }
        catch ( Exception e )
        {
            e.printStackTrace();
        }

        return value;
    }
}
