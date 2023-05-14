/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ProductDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Product;
import view.NhapHang;

/**
 *
 * @author admin
 */
public class NhapHangController 
{
    private ProductDao productDao;
    private NhapHang nhapHang;
    public NhapHangController(NhapHang nhapHang)
    {
        this.nhapHang = nhapHang;
        productDao = new ProductDao();
        nhapHang.showListProducts(productDao.getListProduct());
        nhapHang.addAddProductListener(new AddProductListener());
        nhapHang.addEdiProductListener(new EditProductListener());
        nhapHang.addDeleteProductListener(new DeleteProductListener());
        nhapHang.addClearListener(new ClearProductListener());
        nhapHang.addSortStudentNameListener(new SortProductNameListener());
        nhapHang.addSortProductPriceListener(new SortProductPriceListener());
        nhapHang.addListStudentSelectionListener(new ListProductSelectionListener());
        
    }

    public NhapHang getNhapHang() {
        return nhapHang;
    }
           
    class AddProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Product product = nhapHang.getProductInfo();
            if (product != null) 
            {
                List<Product> productLst = productDao.getListProduct();
                for (int i = 0; i < productLst.size(); i++)
                {
                    if (productLst.get(i).getName().equalsIgnoreCase(product.getName()))
                    {
                        productLst.get(i).setQuantity(productLst.get(i).getQuantity() + product.getQuantity());
                        productDao.edit(productLst.get(i));
                        break;
                    }
                    if (i == productLst.size() - 1)
                    {
                        productDao.add(product);
                        break;
                    }
                }
                nhapHang.showProduct(product);
                nhapHang.showListProducts(productDao.getListProduct());
                nhapHang.showMessage("Thêm thành công!");
                nhapHang.clearProductInfo();
            }
        }
    }
    
    class EditProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Product product = nhapHang.getProductInfo();
            if (product != null) {
                productDao.edit(product);
                nhapHang.showProduct(product);
                nhapHang.showListProducts(productDao.getListProduct());
                nhapHang.showMessage("Cập nhật thành công!");
                nhapHang.clearProductInfo();
            }
        }
    }
    
    class DeleteProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Product product = nhapHang.getProductInfo();
            if (product != null) {
                productDao.delete(product);
                nhapHang.showProduct(product);
                nhapHang.showListProducts(productDao.getListProduct());
                nhapHang.showMessage("Xóa thành công!");
                nhapHang.clearProductInfo();
            }
        }
    }
    
    class ClearProductListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            nhapHang.clearProductInfo();
        }
    }
    
    class SortProductNameListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productDao.sortProductByName();
            nhapHang.showListProducts(productDao.getListProduct());
        }
    }
    
    class SortProductPriceListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            productDao.sortProductByPrice();
            nhapHang.showListProducts(productDao.getListProduct());
        }
    }
    
    class ListProductSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            nhapHang.fillProductFromSelectedRow();
        }
    }
}
