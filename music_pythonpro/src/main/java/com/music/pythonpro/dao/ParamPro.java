package com.music.pythonpro.dao;


import com.music.pythonpro.Example.ProExample;
import com.music.pythonpro.pojo.CFP;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ParamPro extends ProExample {

    private CFP cmdArray;
    /**
     * 判断传入对象是否是空的
     * 因为当前的方法是需要将参数传入到python程序中运行
     * 假设当前的cfd类中的数据正确，那么将数据存储到本类中的实例中，
     * 否则不成功
     *
     * @param commandFileParams
     * @return
     */
    @Override
    public boolean proStart(CFP commandFileParams) {
        this.cmdArray = commandFileParams;
        if(commandFileParams.getParams() == null || commandFileParams.getParams().size() <= 0 || commandFileParams.getFile() == null) {
            // 表示出现
            return false;
        } else {
            // 启动程序主入口
            proEntry();
            return true;
        }
    }

    /**
     * 重写上面的方法 ，需要将参数传入python程序中
     * 有参数的运行方式，首先应该确定获取的对象 有 三个指令， 运行文件的绝对路径，最后的参数。
     */
    @Override
    public void proEntry() {
        String[] args = new String[]{};
        args[0] = this.cmdArray.getCommand();
        args[1] = this.cmdArray.getFile();
        args = createCmdArray(args,this.cmdArray.getParams().size() + 2, this.cmdArray.getParams());
        System.out.println(Arrays.toString(args));
        try {
            super.proMain(args);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    private String[] createCmdArray(String[] src, int length,List<String> params) {
        String[] args = new String[length];
        args[0] = src[0];
        args[1] = src[1];
        int i = 2;
        for (String param : params) {
            args[i] = param;
            i++;
        }
        return args;
    }
}
