package com.example.demo.utils;

public class Logger {
    public static void d(String tag, String msg) {
        System.out.println("+++++++++++++++++++++++" + tag + "+++++++++++++++++++++++");
        System.out.println(rebuildMsg(msg));
    }

    private static String rebuildMsg(String msg) {
        StringBuffer sb = new StringBuffer();
        try {
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[4];
            sb.append("####    " + msg + " | ");
            sb.append(stackTraceElement.getFileName().substring(0, stackTraceElement.getFileName().length()) + ".");
            sb.append(stackTraceElement.getMethodName() + "(");
            sb.append(+stackTraceElement.getLineNumber() + ")    ####\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
