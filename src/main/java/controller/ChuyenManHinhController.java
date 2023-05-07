/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bean.DanhMucBean;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.w3c.dom.Node;
import view.BanHang;
import view.NhapHang;

/**
 *
 * @author admin
 */
public class ChuyenManHinhController 
{
//    private JPanel root;
//    private String kindSelected = "";
//    private List<DanhMucBean> listItem = null;
//
//    public ChuyenManHinhController(JPanel root) {
//        this.root = root;
//    }
//    
//    public void setView(JPanel jpnItem, JLabel jlbItem) {
//       kindSelected = "NhapHang";
//       jpnItem.setBackground(new Color(96, 100, 191));
//       jlbItem.setBackground(new Color(96, 100, 191));
//       
//       root.removeAll();
//       root.setLayout(new BorderLayout());
//       root.add(new NhapHang());
//       root.validate();
//       root.repaint();
//    }
//    
//    public void setEvent(List<DanhMucBean> listItem)
//    {
//        this.listItem = listItem;
//        for (DanhMucBean item: listItem)
//        {
//            item.getJlb().addMouseListener(new LabelEvent(item.getJpn(),item.getKind(),item.getJlb()));
//        }
//    }
//    
//    class LabelEvent implements MouseListener
//    {
//        private JPanel node, jpnItem;
//        private String kind;
//        private JLabel jlbItem;
//
//        public LabelEvent(JPanel jpnItem, String kind, JLabel jlbItem) {
//            this.jpnItem = jpnItem;
//            this.kind = kind;
//            this.jlbItem = jlbItem;
//        }
//        
//        
//        @Override
//        public void mouseClicked(MouseEvent e) 
//        {
//            switch (kind) {
//                case "BanHang":     
//                    node = new BanHang();
//                    break;
//                case "NhapHang":     
//                    node = new NhapHang();
//                    break;
//                default:
//                    node = new NhapHang();
//                    break;
//            }
//            root.removeAll();
//            root.setLayout(new BorderLayout());
//            root.add(node);
//            root.validate();
//            root.repaint();
//            jpnItem.setBackground(new Color(96, 100, 191));
//            jlbItem.setBackground(new Color(96, 100, 191)); 
//        }
//
//        @Override
//        public void mousePressed(MouseEvent e) 
//        {
//            kindSelected = kind;
//            jpnItem.setBackground(new Color(96, 100, 191));
//            jlbItem.setBackground(new Color(96, 100, 191));                    
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) 
//        {
//
//        }
//
//        @Override
//        public void mouseEntered(MouseEvent e) {
//            jpnItem.setBackground(new Color(96, 100, 191));
//            jlbItem.setBackground(new Color(96, 100, 191));
//        }
//
//        
//        @Override
//        public void mouseExited(MouseEvent e) {
//            if (!kindSelected.equalsIgnoreCase(kind))
//            {
//                jpnItem.setBackground(new Color(96, 100, 191));
//                jlbItem.setBackground(new Color(96, 100, 191));
//            }
//            else
//            {
//                jpnItem.setBackground(new Color(102, 255, 102));
//                jlbItem.setBackground(new Color(102, 255, 102));
//            }
//            
//        }
//        
//    }
//    
//    private void setChangeBackground(String kind)
//    {
//        for (DanhMucBean item: listItem)
//        {
//            if (item.getKind().equalsIgnoreCase(kind))
//            {
//                item.getJlb().setBackground(new Color(96, 100, 191));
//                item.getJpn().setBackground(new Color(96, 100, 191));
//            }
//            else
//            {
//                item.getJlb().setBackground(new Color(76, 175, 80));
//                item.getJpn().setBackground(new Color(76, 175, 80));
//            }
//        }
//    }
    
    private JPanel root;
    private String kindSelected = "";
    private List<DanhMucBean> listItem = null;

    public ChuyenManHinhController(JPanel root) {
        this.root = root;
    }
    
    public void setView(JPanel jpnItem, JLabel jlbItem) {
       kindSelected = "NhapHang";
       jpnItem.setBackground(new Color(96, 100, 191));
       jlbItem.setBackground(new Color(96, 100, 191));
       root.removeAll();
       NhapHang nhapHang = new NhapHang();
       NhapHangController nhapHangController = new NhapHangController(nhapHang);
       root.setLayout(new BorderLayout());
       root.add(nhapHangController.getNhapHang());
       root.validate();
       root.repaint();
    }
    
    public void setEvent(List<DanhMucBean> listItem)
    {
        this.listItem = listItem;
        for (DanhMucBean item: listItem)
        {
            item.getJlb().addMouseListener(new LabelEvent(item.getJpn(),item.getKind(),item.getJlb()));
        }
    }
    
    class LabelEvent implements MouseListener
    {
        private JPanel node, jpnItem;
        private String kind;
        private JLabel jlbItem;

        public LabelEvent(JPanel jpnItem, String kind, JLabel jlbItem) {
            this.jpnItem = jpnItem;
            this.kind = kind;
            this.jlbItem = jlbItem;
        }
        
        
        @Override
        public void mouseClicked(MouseEvent e) 
        {
            switch (kind) {
                case "BanHang":  
                    BanHang banHang = new BanHang();
                    BanHangController banHangController = new BanHangController(banHang);
                    node = banHangController.getBanHang();
                    break;
                case "NhapHang":     
                    NhapHang nhapHang1 = new NhapHang();
                    NhapHangController nhapHangController1 = new NhapHangController(nhapHang1);
                    node = nhapHangController1.getNhapHang();
                    break;
                default:
                    NhapHang nhapHang2 = new NhapHang();
                    NhapHangController nhapHangController2 = new NhapHangController(nhapHang2);
                    node = nhapHangController2.getNhapHang();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(node);
            root.validate();
            root.repaint();
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191)); 
        }

        @Override
        public void mousePressed(MouseEvent e) 
        {
            kindSelected = kind;
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));                    
        }

        @Override
        public void mouseReleased(MouseEvent e) 
        {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(96, 100, 191));
            jlbItem.setBackground(new Color(96, 100, 191));
        }

        
        @Override
        public void mouseExited(MouseEvent e) {
            if (!kindSelected.equalsIgnoreCase(kind))
            {
                jpnItem.setBackground(new Color(96, 100, 191));
                jlbItem.setBackground(new Color(96, 100, 191));
            }
            else
            {
                jpnItem.setBackground(new Color(102, 255, 102));
                jlbItem.setBackground(new Color(102, 255, 102));
            }
            
        }
        
    }
    
    private void setChangeBackground(String kind)
    {
        for (DanhMucBean item: listItem)
        {
            if (item.getKind().equalsIgnoreCase(kind))
            {
                item.getJlb().setBackground(new Color(96, 100, 191));
                item.getJpn().setBackground(new Color(96, 100, 191));
            }
            else
            {
                item.getJlb().setBackground(new Color(76, 175, 80));
                item.getJpn().setBackground(new Color(76, 175, 80));
            }
        }
    } 
}
