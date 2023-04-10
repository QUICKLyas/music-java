package com.music.pythonpro.dao;

import com.music.pythonpro.Example.ProExample;
import com.music.pythonpro.pojo.CFP;

import java.io.IOException;

import static com.music.commons.utils.DataUtils.timeUTC;

public class NoParamPro extends ProExample {
    private CFP cmdArray;

    /**
     * 无参进行使用python程序
     * 默认直接使用proEntry
     *
     * @param commandFileParams
     * @return
     */
    @Override
    public Object proStart(CFP commandFileParams) {
        // 启动Main程序
        this.cmdArray = commandFileParams;
        try {
            proEntry();
        } catch (Exception e) {
            System.out.println("["+timeUTC()+"]: "+e.getClass());
            // 表示出现了问题，需要返回问题
            return e;
        }
        return true;
    }

    @Override
    public void proEntry() throws RuntimeException{
        String[] args = new String[2];
        args[0] = this.cmdArray.getCommand();
        args[1] = this.cmdArray.getFile();
        try {
            super.proMain(args);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
