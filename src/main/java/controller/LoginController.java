/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.User;
import view.HomeView;
import view.LoginView;

/**
 *
 * @author admin
 */
public class LoginController 
{
    private UserDao userDao;
    private LoginView loginView;
    private HomeView homeView;
    
    public LoginController(LoginView view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                loginView.showMessage("Đăng nhập thành công!");
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                homeView = new HomeView();
                    HomeController homeController = new HomeController(homeView);
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
