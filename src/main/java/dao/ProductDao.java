/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import model.Product;
import model.ProductXML;
import utils.FileUtils;


/**
 *
 * @author admin
 */
public class ProductDao 
{
    private static final String STUDENT_FILE_NAME = "product.xml";
    private List<Product> listProduct;
    private List<Product> listMaProduct;
    private List<Product> listNameProduct;
    private List<Product> listFoodProduct;
    private List<Product> listDrinkProduct;
    private List<Product> listPersonalProduct;
    private List<Product> listStudyProduct;
    private List<Product> listWCProduct;

    
    public ProductDao() {
        this.listProduct = readListProducts();
        if (listProduct == null) {
            listProduct = new ArrayList<Product>(); 
        }
        listMaProduct = new ArrayList<Product>();
        listNameProduct = new ArrayList<Product>();
        listFoodProduct = new ArrayList<Product>();
        listDrinkProduct = new ArrayList<Product>();
        listPersonalProduct = new ArrayList<Product>();
        listStudyProduct = new ArrayList<Product>();
        listWCProduct = new ArrayList<Product>();
        for (int i = 0 ; i < listProduct.size(); i++)
        {
            String type = listProduct.get(i).getType();
            switch (type) {
                case "Thuc an":
                    listFoodProduct.add(listProduct.get(i));
                    break;
                case "Do uong":
                    listDrinkProduct.add(listProduct.get(i));
                    break;
                case "Do dung ca nhan":
                    listPersonalProduct.add(listProduct.get(i));
                    break;
                case "Do dung hoc tap":
                    listStudyProduct.add(listProduct.get(i));
                    break;
                case "Dung cu ve sinh":
                    listWCProduct.add(listProduct.get(i));
                    break;
                default:
                    throw new AssertionError();
            }
        }
        setMaHang();
    }
    
    public void setMaHang()
    {
        for (int i = 0 ; i < listFoodProduct.size(); i++)
        {
            listFoodProduct.get(i).setMaHang("TA" +(i+1));
        }
        for (int i = 0 ; i < listDrinkProduct.size(); i++)
        {
            listDrinkProduct.get(i).setMaHang("DU" +(i+1));
        }
        for (int i = 0 ; i < listPersonalProduct.size(); i++)
        {
            listPersonalProduct.get(i).setMaHang("DDCN" +(i+1));
        }
        for (int i = 0 ; i < listStudyProduct.size(); i++)
        {
            listStudyProduct.get(i).setMaHang("DDHT" +(i+1));
        }
        for (int i = 0 ; i < listWCProduct.size(); i++)
        {
            listWCProduct.get(i).setMaHang("DCVS" +(i+1));
        }
    }
    
    public void writeListProducts(List<Product> product) {
        ProductXML productXML = new ProductXML();
        productXML.setProduct(product);
        FileUtils.writeXMLtoFile(STUDENT_FILE_NAME, productXML);
    }
    
    public List<Product> readListProducts() {
        List<Product> list = new ArrayList<>();
        ProductXML productXML = (ProductXML) FileUtils.readXMLFile(
                STUDENT_FILE_NAME, ProductXML.class);
        if (productXML != null) {
            list = productXML.getProduct();
        }
        return list;
    }
    
    public void add(Product product) {
        listProduct.add(product);
        String type = product.getType();
            switch (type) {
                case "Thuc an":
                    listFoodProduct.add(product);
                    break;
                case "Do uong":
                    listDrinkProduct.add(product);
                    break;
                case "Do dung ca nhan":
                    listPersonalProduct.add(product);
                    break;
                case "Do dung hoc tap":
                    listStudyProduct.add(product);
                    break;
                case "Dung cu ve sinh":
                    listWCProduct.add(product);
                    break;
                default:
                    throw new AssertionError();
            }
        setMaHang();
        writeListProducts(listProduct);
    }
    
    public void edit(Product product) {
        int size = listProduct.size();
        for (int i = 0 ; i < listFoodProduct.size(); i++)
        {
            if (listFoodProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang()))
            {
                listFoodProduct.remove(i);
            }
        }
        for (int i = 0 ; i < listDrinkProduct.size(); i++)
        {
            if (listDrinkProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang()))
            {
                listDrinkProduct.remove(i);
            }
        }
        for (int i = 0 ; i < listPersonalProduct.size(); i++)
        {
            if (listPersonalProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang()))
            {
                listPersonalProduct.remove(i);
            }
        }
        for (int i = 0 ; i < listStudyProduct.size(); i++)
        {
            if (listStudyProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang()))
            {
                listStudyProduct.remove(i);
            }
        }
        for (int i = 0 ; i < listWCProduct.size(); i++)
        {
            if (listWCProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang()))
            {
                listWCProduct.remove(i);
            }
        }
        for (int i = 0; i < size; i++) {
            if (listProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang())) 
            {
                String type = product.getType();
                switch (type) 
                {
                    case "Thuc an":
                        listFoodProduct.add(listProduct.get(i));
                        break;
                    case "Do uong":
                        listDrinkProduct.add(listProduct.get(i));
                        break;
                    case "Do dung ca nhan":
                        listPersonalProduct.add(listProduct.get(i));
                        break;
                    case "Do dung hoc tap":
                        listStudyProduct.add(listProduct.get(i));
                        break;
                    case "Dung cu ve sinh":
                        listWCProduct.add(listProduct.get(i));
                        break;
                    default:
                        throw new AssertionError();
                }

                setMaHang();
                listProduct.get(i).setName(product.getName());
                listProduct.get(i).setPrice(product.getPrice());
                listProduct.get(i).setQuantity(product.getQuantity());
                listProduct.get(i).setType(product.getType());
                writeListProducts(listProduct);
                break;
            }
        }
    }
    
    public boolean delete(Product product) {
        boolean isFound = false;
        int size = listProduct.size();
        for (int i = 0; i < size; i++) 
        {
            if (listProduct.get(i).getMaHang().equalsIgnoreCase(product.getMaHang())) 
            {
                product = listProduct.get(i);
                listProduct.remove(product);
                writeListProducts(listProduct);
                String type = product.getType();
                switch (type) 
                {
                    case "Thuc an":
                        listFoodProduct.remove(product);
                        break;
                    case "Do uong":
                        listDrinkProduct.remove(product);
                        break;
                    case "Do dung ca nhan":
                        listPersonalProduct.remove(product);
                        break;
                    case "Do dung hoc tap":
                        listStudyProduct.remove(product);
                        break;
                    case "Dung cu ve sinh":
                        listWCProduct.remove(product);
                        break;
                    default:
                        throw new AssertionError();
                    }   
                setMaHang();
                return true;          
                }  
            }   
            return false;    
        }
    
    public void sortProductByName() {
        Collections.sort(listProduct, (Product product1, Product product2) -> product1.getName().compareTo(product2.getName()));
    }
    
    public void sortProductByPrice() {
        Collections.sort(listProduct, (Product product1, Product product2) -> {
            if (product1.getPrice()> product2.getPrice()) {
                return 1;
            }
            return -1;
        });
    }

    public List<Product> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Product> listProduct) {
        this.listProduct = listProduct;
    }
    
    public List<Product> searchNameProduct(String name)
    {
        for (int i = 0; i < listProduct.size(); i++)
        {
            if (listProduct.get(i).getName().toLowerCase().contains(name.toLowerCase()) || listProduct.get(i).getName().toLowerCase().equalsIgnoreCase(name.toLowerCase()))
            {
                listNameProduct.add(listProduct.get(i));
            }         
        }
        return listNameProduct;
    }
    
    public List<Product> searchMaProduct(String ma)
    {
        for (int i = 0; i < listProduct.size(); i++)
        {
            if (listProduct.get(i).getMaHang().toLowerCase().contains(ma.toLowerCase()) || listProduct.get(i).getMaHang().toLowerCase().equalsIgnoreCase(ma.toLowerCase()))
            {
                listMaProduct.add(listProduct.get(i));
            }         
        }
        return listMaProduct;
    }
}
