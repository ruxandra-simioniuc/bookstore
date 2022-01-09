package pos.proiect.bookstore.repository;

public class OrderRepositoryCustomImpl implements OrderRepositoryCustom{

    private static String collectionName = "client";

    @Override
    public String getCollectionName() {
        return collectionName;
    }

    @Override
    public void setCollectionName(String collectionName) {
        OrderRepositoryCustomImpl.collectionName = collectionName;
    }
}
