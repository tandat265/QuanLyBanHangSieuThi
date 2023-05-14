/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import model.Product;
import view.BanHang;

/**
 *
 * @author admin
 */
public class BanHangController 
{
    private ProductDao productDao;
    private BanHang banHang;
    
    public BanHangController(BanHang banHang)
    {
        this.banHang = banHang;
        productDao = new ProductDao();
        banHang.addSearchProductListener(new SearchProductListener());
        banHang.addProductListener(new AddProductListener());
        banHang.deleteProductListener(new DeleteProductListener());
        banHang.showDate();
        banHang.payProductListener(new PayProductListener());
    }

    public BanHang getBanHang() {
        return banHang;
    }
    
    
    class SearchProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            banHang.getListProduct().clear();
            String type = "";
            String searchTxt = banHang.getTxtSearch().getText();
            if (banHang.getComboSearch().getSelectedIndex() != -1) {
                    type = banHang.getComboSearch().getItemAt(banHang.getComboSearch().getSelectedIndex());
                }
            if (type.equalsIgnoreCase("Theo ten"))
            {
                banHang.setListProduct(productDao.searchNameProduct(searchTxt));
            }
            else if (type.equalsIgnoreCase("Theo ma hang hoa"))
            {
                banHang.setListProduct(productDao.searchMaProduct(searchTxt)); 
            }
            if (banHang.getListProduct().isEmpty())
                banHang.showMessage("Không có sản phẩm nào như vậy!");
            banHang.showListProducts(banHang.getListProduct());   
        }
    }
    
    class AddProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = banHang.getSearchTable().getSelectedRow();
            Object s = banHang.getQuatityProduct().getValue();
            int quantity = Integer.parseInt(s.toString());
            if (row >= 0)
            {
                banHang.getAdd().setEnabled(true);
                Product product = new Product();
                product.setMaHang(banHang.getSearchTable().getModel().getValueAt(row, 0).toString());
                product.setName(banHang.getSearchTable().getModel().getValueAt(row, 1).toString());
                product.setPrice(Integer.parseInt(banHang.getSearchTable().getModel().getValueAt(row, 2).toString()));
                product.setQuantity(quantity);
                for (int i = 0; i < banHang.getListSaleProduct().size(); i++)
                {
                    if (banHang.getListSaleProduct().get(i).getMaHang().equalsIgnoreCase(product.getMaHang()))
                    {
                        banHang.getListSaleProduct().get(i).setQuantity(banHang.getListSaleProduct().get(i).getQuantity() + product.getQuantity());
                        break;
                    }
                    else if (i == banHang.getListSaleProduct().size() - 1)
                    {
                        banHang.getListSaleProduct().add(product);
                        break;
                    }
                }
                if (banHang.getListSaleProduct().isEmpty())
                    banHang.getListSaleProduct().add(product);          
            }
            banHang.getTxtSearch().setText("");
            banHang.showListSaleProducts(banHang.getListSaleProduct());
            banHang.showBill();
            banHang.getQuatityProduct().setValue(0);
        }
    }
    
    class DeleteProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int row = banHang.getProductTable().getSelectedRow();
            if (row >= 0)
            {
                banHang.getDelete().setEnabled(true);
                Product product = new Product();
                String maHang = banHang.getProductTable().getModel().getValueAt(row, 0).toString();
                for (int i = 0 ; i < banHang.getListSaleProduct().size(); i++)
                {
                    if (maHang.equalsIgnoreCase(banHang.getListSaleProduct().get(i).getMaHang()))
                    {
                        banHang.setSum(banHang.getSum() - banHang.getListSaleProduct().get(i).getPrice()); 
                        banHang.getListSaleProduct().remove(i);
                        break;
                    }
                }
            }
            banHang.showListSaleProducts(banHang.getListSaleProduct());
            banHang.showBill();
        }
    }
  
    class PayProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            banHang.showMessage("Bill đang được in!");
            banHang.convertToTxt();
            banHang.subQuantity();
            DefaultTableModel model = (DefaultTableModel) banHang.getSearchTable().getModel();
            model.setRowCount(0);
            DefaultTableModel model1 = (DefaultTableModel) banHang.getProductTable().getModel();
            model1.setRowCount(0);
            banHang.getListProduct().clear();
            banHang.getListSaleProduct().clear();
            banHang.showBill();          
        }
    }
}
