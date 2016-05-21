package org.luisyang.integration.spring.service.impl;


import org.luisyang.integration.spring.exception.PerformanceException;
import org.luisyang.integration.spring.service.Instrument;
import org.luisyang.integration.spring.service.Performer;

public class Instrumentalist implements Performer {

	private String song;
    private int age;
    private Instrument instrument;
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSong() {
        return song;
    }
    public void setSong(String song) {
        this.song = song;
    }
    public Instrument getInstrument() {
        return instrument;
    }
    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }
    public Instrumentalist(){}
    public Instrumentalist(String song,int age,Instrument instrument){
        this.song = song;
        this.age = age;
        this.instrument = instrument;
    }
    public void perform() throws PerformanceException {
        System.out.println("Instrumentalist age:"+age);
        System.out.print("Playing "+song+":");
        instrument.play();
    }


}
