package com.restock.platform.profiles.domain.model.entities;

import com.restock.platform.profiles.domain.model.commands.CreateBusinessCommand;
import jakarta.persistence.*;

/**
 * Aggregate root that represents a Business entity.
 */
@Entity
@Table(name = "businesses")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String email;
    private String address;
    private String phone;
    private String categories;

    public Business() {
        this.name = "";
        this.email = "";
        this.address = "";
        this.phone = "";
        this.categories = "";
    }

    public Business(String name, String email, String phone, String address, String categories) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.categories = categories;
    }

    public Business(CreateBusinessCommand command) {
        this(
                command.name(),
                command.email(),
                command.phone(),
                command.address(),
                String.join(",", command.categories())
        );
    }

    public void update(String name, String email, String phone, String address, String categories) {
        this.name = name != null ? name : this.name;
        this.email = email != null ? email : this.email;
        this.phone = phone != null ? phone : this.phone;
        this.address = address != null ? address : this.address;
        this.categories = categories != null ? categories : this.categories;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getCategories() {
        return categories;
    }
}
