package com.demo.translator.translatordemo.aop;

public class CommonJoinPoint {
    public static final String WEB_LAYER_EXECUTION = "execution(* com.demo.translator.translatordemo.web..*(..))";
    public static final String SERVICE_LAYER_EXECUTION = "execution(* com.demo.translator.translatordemo.service..*(..))";
    public static final String DATA_LAYER_EXECUTION = "execution(* com.demo.translator.translatordemo.repository..*(..))";
}
