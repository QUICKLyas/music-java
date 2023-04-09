//package com.music.pythonpro.utils;
//
//import com.music.pythonpro.dao.NoParamPro;
//import com.music.pythonpro.dao.ParamPro;
//import com.music.pythonpro.pojo.CFP;
//import reactor.util.annotation.Nullable;
//
//import java.util.List;
//
//
//import static com.music.commons.utils.DataUtils.timeUTC;
//public class PythonStarter {
//    public static void startPythonPro(String file, @Nullable List<String> params) {
//        CFP cfp = new CFP(file,params);
//        System.out.println( "["+timeUTC()+"]: "+cfp.toString());
//        if (params == null || params.size() <=0) {
//        NoParamPro noParamPro = new NoParamPro();
//        noParamPro.proStart(cfp);
//        } else {
//            ParamPro paramPro = new ParamPro();
//            paramPro.proStart(cfp);
//        }
//    }
//}
