package com.music.pythonpro;

import com.music.pythonpro.dao.NoParamPro;
import com.music.pythonpro.dao.ParamPro;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        NoParamPro noParamPro = new NoParamPro();
        ParamPro paramPro = new ParamPro();
        try {
            paramPro.proMain();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        //        try {
//            noParamPro.proMain();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
