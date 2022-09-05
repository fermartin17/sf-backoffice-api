package sf.backoffice.api.shared.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "deleted")
    private boolean deleted;

    @Column(name = "creationDate", columnDefinition = "TIMESTAMP")
    private Date creationDate;

    public long getId() {
        return id;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
