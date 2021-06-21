package pos.utils;

import pos.app.Product;

public class AttributesParser extends Product {


    public static final int ATTRIBUTE_NAME_LENGTH0 = 5;
    public static final int ATTRIBUTE_NAME_LENGTH1 = 12;
    public static final int ATTRIBUTE_NAME_LENGTH2 = 6;
    public static final int ATTRIBUTE_NAME_LENGTH3 = 6;
    public static final int ATTRIBUTE_NAME_LENGTH4 = 4;
    public static final int ATTRIBUTE_NAME_LENGTH5 = 4;
    // name=String;description=String;price=double;stock=int;vat=int
    public static void editProduct(Product product, String attributes){
        String[] splitAttributes = attributes.split(";");
        for (String attribute : splitAttributes){
            if (attribute.startsWith("name=")) product.setName(attribute.substring(ATTRIBUTE_NAME_LENGTH0));
            if (attribute.startsWith("description=")) product.setDescription(attribute.substring(ATTRIBUTE_NAME_LENGTH1));
            if (attribute.startsWith("price=")) product.setPrice(Double.parseDouble(attribute.substring(ATTRIBUTE_NAME_LENGTH2)));
            if (attribute.startsWith("stock=")) product.setStock(Integer.parseInt(attribute.substring(ATTRIBUTE_NAME_LENGTH3)));
            if (attribute.startsWith("vat=")) product.setVat(Integer.parseInt(attribute.substring(ATTRIBUTE_NAME_LENGTH4)));
            if (attribute.startsWith("ean=")) product.setEan(attribute.substring(ATTRIBUTE_NAME_LENGTH5));
        }
    }

    public static String getSomeProductAttributes(Product product, int[] attributePosition){
        String[] splitAttributes = product.toString().split(";");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < attributePosition.length;i++){
            String attribute = splitAttributes[attributePosition[i]];
            if (attribute.startsWith("name=")) sb.append(attribute.substring(ATTRIBUTE_NAME_LENGTH0));
            if (attribute.startsWith("description=")) sb.append(attribute.substring(ATTRIBUTE_NAME_LENGTH1));
            if (attribute.startsWith("price=")) sb.append(attribute.substring(ATTRIBUTE_NAME_LENGTH2));
            if (attribute.startsWith("stock=")) sb.append(attribute.substring(ATTRIBUTE_NAME_LENGTH3));
            if (attribute.startsWith("vat=")) sb.append(attribute.substring(ATTRIBUTE_NAME_LENGTH4));
            if (attribute.startsWith("ean=")) sb.append(attribute.substring(ATTRIBUTE_NAME_LENGTH5));
        }
        return sb.toString();
    }

}
