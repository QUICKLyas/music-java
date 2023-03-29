package com.music.pythonpro.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 此类存储三个对象，
 * 指令，文件，参数
 * 参数可以为null
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CFP {
    private final String command = "python3";

    private String file;
    // 可以为空值
    private List<String> params;
}
