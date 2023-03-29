package com.music.pythonpro;

import com.music.pythonpro.dao.NoParamPro;
import com.music.pythonpro.dao.ParamPro;
import com.music.pythonpro.pojo.CFP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        CFP cfp = new CFP();
        cfp.setFile("F:\\IDE\\Python\\demo\\test.py");
        List<String> str = new ArrayList<>();
        str.add("1");
        str.add("2");
        cfp.setParams(str);
//        System.out.println( "Hello World!" );
//        NoParamPro noParamPro = new NoParamPro();
//        noParamPro.proStart(cfp);
        ParamPro paramPro = new ParamPro();
        paramPro.proStart(cfp);

    }
}
