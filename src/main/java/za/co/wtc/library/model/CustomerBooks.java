package za.co.wtc.library.model;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "customer_books")
public class CustomerBooks {

    @Column(name = "customer_id")
    private long customerId;

    @Column(name = "book_id")
    private long bookId;

    @Column(name = "date_issued")
    private LocalDateTime dateIssued;

    @Column(name = "due_date")
    private LocalDateTime dueDate;

    

    public CustomerBooks(){}

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(LocalDateTime dateIssued) {
        this.dateIssued = dateIssued;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    
}
