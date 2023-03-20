package com.music.python.controller;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@CrossOrigin
@RestController
public class TestController {

    @RequestMapping(value = "/test")
    public String test() {
//        interpreter.execfile("F:\\IDE\\Python\\demo\\test.py");
//        PyFunction function = interpreter.get("say_hello",PyFunction.class);
//        PyObject obj =  function.__call__();
        return null;
    }
}
