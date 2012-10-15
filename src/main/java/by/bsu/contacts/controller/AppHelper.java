package by.bsu.contacts.controller;

import javax.servlet.ServletContext;
import java.io.InputStream;

public class AppHelper {
    private static AppHelper helper;
    private ServletContext context;

    private AppHelper(){};

    public static AppHelper getInstance(){
        if (helper == null){
            helper = new AppHelper();
        }
        return helper;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }

    public InputStream getResourseAsStream(String res){
        return context.getResourceAsStream(res);
    }

    public String getContextPath(){
        return context.getContextPath();
    }
}
