package com.freespeedreader;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

public class TextReaderTest {

    @Test
    public void textReaderTest() {
    	String text="TWO WEEKS INTO Ted Kaptchuk’s first randomized";
    	System.out.println("TWO WEEKS INTO Ted Kaptchuk’s first randomized ");
    	String[] words=text.split(" ");
    	System.out.println(words.length/4d);
    	System.out.println(text.length()/4d);
    	System.out.println(11/4d);
    	BigDecimal value=new BigDecimal(2.33);
    	BigDecimal scaled = value.setScale(0, RoundingMode.HALF_UP);
    	System.out.println(scaled);
    	System.out.println(Math.ceil(11/4));
    }
}
