package vn.com.telsoft.controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author ChienDX
 */
@ManagedBean(name = "TestController")
@ViewScoped
public class TestController implements Serializable {

    public TestController() {
    }

    public void handleFileUpload(FileUploadEvent event) {
        try {
            System.out.print(event.getFile().getFileName());

        } catch (Exception ex) {
            
        }
    }
}
