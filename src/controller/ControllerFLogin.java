/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import ui.form.FLogin;

/**
 *
 * @author Nemanja
 */
public class ControllerFLogin {
    
    private FLogin fLogin;
    
    public ControllerFLogin() {
        
    }

    void otvoriFormuLogin() {
        fLogin = new FLogin();
        addListenersFLogin();
        fLogin.setVisible(true);
    }
    
    private void addListenersFLogin() {
        fLogin.addButtonLoginListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUICoordinator.getInstance().otvoriMainFormu();
                fLogin.dispose();
                System.out.println("otvaranje main forme");
            }
        });
        
        fLogin.addButtonCancelListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
    }

    
}
