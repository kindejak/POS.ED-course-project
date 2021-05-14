package pos.app;

public class AttributesParser {

    private static final int ATTRIBUTE_NAME_LENGTH0 = 5;
    private static final int ATTRIBUTE_NAME_LENGTH1 = 12;
    private static final int ATTRIBUTE_NAME_LENGTH2 = 6;
    private static final int ATTRIBUTE_NAME_LENGTH3 = 6;
    private static final int ATTRIBUTE_NAME_LENGTH4 = 4;
    // name=String;description=String;price=double;stock=int;vat=int
    public static void editProduct(Product product,String attributes){
        String[] splitAttributes = attributes.split(";");
        for (String attribute : splitAttributes){
            if (attribute.startsWith("name=")) product.setName(attribute.substring(ATTRIBUTE_NAME_LENGTH0));
            if (attribute.startsWith("description=")) product.setDescription(attribute.substring(ATTRIBUTE_NAME_LENGTH1));
            if (attribute.startsWith("price=")) product.setPrice(Double.parseDouble(attribute.substring(ATTRIBUTE_NAME_LENGTH2)));
            if (attribute.startsWith("stock=")) product.setStock(Integer.parseInt(attribute.substring(ATTRIBUTE_NAME_LENGTH3)));
            if (attribute.startsWith("vat=")) product.setVat(Integer.parseInt(attribute.substring(ATTRIBUTE_NAME_LENGTH4)));
        }
    }

    public static void createOrder(String attributes){

    }
}
