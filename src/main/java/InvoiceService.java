import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InvoiceService {

    List<Invoice> invoices = new CopyOnWriteArrayList<>(); // (1)

    public List<Invoice> findAll(){
        return invoices;
    }

    public Invoice create(String userId, Integer amount){
    // TODO real pdf creation

        Invoice invoice = new Invoice(userId, amount, "http://www.africau.edu/images/default/sample.pdf");
        invoices.add(invoice);
        return invoice;
    }
}