package pl.robertczarnik.currencyconverter.calls;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "all_calls")
public class Call extends DateAudit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String url;

    public Call(String type, String url) {
        this.type = type;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
