package ru.sap.database.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@EqualsAndHashCode
@Getter
@Setter
@Table(name = "pictures")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contentType;
    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(nullable = false, length = 33554430)
    private byte[] data;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_picture",
            joinColumns = @JoinColumn(name = "picture_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Picture() {
    }

    public Picture(String contentType, byte[] data) {
        this.contentType = contentType;
        this.data = data;
    }
}
