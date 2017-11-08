package edu.ucsd.cs110.tests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import edu.ucsd.cs110.temperature.Celsius;
import edu.ucsd.cs110.temperature.Temperature;
import junit.framework.TestCase;
@RunWith(Arquillian.class)
public class CelsiusTest extends TestCase{
private float delta = 0.001f;

    public void testCelsius(){
        float value = 12.34f;
        Celsius temp = new Celsius(value);

        assertEquals(value, temp.getValue(), delta);
    }

    public void testCelsiusToString(){
        float value = 12.34f;

        Celsius temp = new Celsius(value);
        String string = temp.toString();

        String beginning = "" + value;
        String ending = " C";

        // Verify the suffix of the formatted string
        assertTrue(string.startsWith(beginning));

        // Verify the prefix of the formatted string
        assertTrue(string.endsWith(ending));

        // Verify the middle of the formatted string
        int endIndex = string.indexOf(ending);

        // (Hint: what is the length of the middle of the string?)
        assertTrue(string.substring(0, endIndex).equals(beginning));
    }

    public void testCelsiusToCelsius()
    {
        Celsius temp = new Celsius(0);
        Temperature convert = temp.toCelsius();
        assertEquals(0, convert.getValue(), delta);
    }

    public void testCelsiusToFahrenheit(){
        Celsius temp = new Celsius(0);

        Temperature convert = temp.toFahrenheit();
        assertEquals(32, convert.getValue(), delta);

        temp = new Celsius(100);
        convert = temp.toFahrenheit();

        assertEquals(212, convert.getValue(), delta);
    }

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(edu.ucsd.cs110.temperature.Celsius.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
