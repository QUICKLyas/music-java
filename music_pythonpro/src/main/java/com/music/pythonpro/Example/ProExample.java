package com.music.pythonpro.Example;

import com.music.pythonpro.pojo.CFP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//import static com.music.commons.utils.DataUtils.timeUTC;

public abstract class ProExample {
    public abstract Object proStart(CFP commandFileParams);
    public abstract void proEntry() throws Exception;
    public void proMain(String[] args) throws IOException, InterruptedException{
        Process proc;
        proc = Runtime.getRuntime().exec(args);
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line =null;
//        while((line = in.readLine()) != null) {
//            System.out.println("["+timeUTC()+"]: "+ line);
//        }
        // 关闭输出流
        in.close();
        proc.waitFor();
    }



}
