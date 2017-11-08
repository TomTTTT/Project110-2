package edu.ucsd.cs110.temperature;

public class Celsius extends Temperature{
    public Celsius(float t){
        super(t);
    }

    @Override
    public Temperature toCelsius() {
        return new Celsius(this.getValue());
    }

    @Override
    public Temperature toFahrenheit() {
        return this;
    }

    public String toString(){
        //TODO: Complete this method
        return "";
    }
}
