package com.music.pythonpro.dao;

import com.music.pythonpro.Example.ProExample;
import com.music.pythonpro.pojo.CFP;

import java.io.IOException;

public class NoParamPro extends ProExample {
    private Process proc;

    /**
     * 无参进行使用python程序
     * 默认直接使用proEntry
     * @param commandFileParams
     * @return
     */
    @Override
    public boolean proStart(CFP commandFileParams) {
        // 启动Main程序
//        try {
//            proMain(commandFileParams.getCommand() , commandFileParams.getFile());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public void proEntry() {

    }

    /**
     * 对上面的proMain进行重写
     */
    public void proMain(String command, String fileName) throws IOException, InterruptedException {
        String[] args =  new String[] {command,fileName};
        // 形成可以使用的command语句，用于后面的Runtime.getRuntime().exec()中。

    }

}
