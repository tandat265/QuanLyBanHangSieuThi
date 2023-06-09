/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.DanhMucBean;
import dao.ProductDao;
import dao.UserDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Product;
import view.BanHang;
import view.HomeView;
import view.NhapHang;

/**
 *
 * @author admin
 */
public class HomeController 
{
    private HomeView homeView;
    private ProductDao productDao;
    private List<Product> listProduct;
    private List<Product> listSaleProduct;
    private NhapHang nhapHang;  
    private BanHang banHang;
    
    public HomeController(HomeView view) {
        
        //home, doi man hinh
        this.homeView = view;
        ChuyenManHinhController controller = new ChuyenManHinhController(homeView.getJpnView());
        controller.setView(homeView.getJpnQLBH(), homeView.getJlbQLBH());
        List<DanhMucBean> listItem = new ArrayList<>();
        listItem.add(new DanhMucBean("BanHang", homeView.getJpnBanHang(), homeView.getJlbBanHang()));
        listItem.add(new DanhMucBean("NhapHang", homeView.getJpnThongKe(), homeView.getJlbThongKe()));
        controller.setEvent(listItem);
        
        productDao = new ProductDao();
        homeView.setVisible(true);
        
        //nhap hang
        nhapHang = new NhapHang();        
        //ban hang
        banHang = new BanHang();
        
        
    }  
}
