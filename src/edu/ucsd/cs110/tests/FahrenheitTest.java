package edu.ucsd.cs110.tests;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.runner.RunWith;

import edu.ucsd.cs110.temperature.Fahrenheit;
import edu.ucsd.cs110.temperature.Temperature;
import junit.framework.TestCase;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class FahrenheitTest extends TestCase{
    private float delta = 0.001f;

    public void testFahrenheit(){
        float value = 12.34f;
        Fahrenheit temp = new Fahrenheit(value);
        assertEquals(value, temp.getValue(), delta);
    }

    public void testFahrenheitToString(){
        float value = 12.34f;
        Fahrenheit temp = new Fahrenheit(value);
        String string = temp.toString();
        String beginning = "" + value;
        String ending = " F";
        assertTrue(string.endsWith(ending));
        int endIndex = string.indexOf(ending);
        assertTrue(string.substring(0,endIndex).equals(beginning));
    }

    public void testFahrenheitToFahrenheit(){
        Fahrenheit temp = new Fahrenheit(32);
        Temperature convert = temp.toFahrenheit();
        assertEquals(32, convert.getValue(), delta);
    }

    public void testFahrenheitToCelsius(){
        Fahrenheit temp = new Fahrenheit(32);
        Temperature convert = temp.toCelsius();
        assertEquals(0, convert.getValue(), delta);
        temp = new Fahrenheit(212);
        convert = temp.toCelsius();
        assertEquals(100, convert.getValue(), delta);
    }
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(edu.ucsd.cs110.temperature.Fahrenheit.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

}
