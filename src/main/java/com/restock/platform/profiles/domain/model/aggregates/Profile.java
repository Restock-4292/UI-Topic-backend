package com.restock.platform.profiles.domain.model.aggregates;

import com.restock.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.restock.platform.profiles.domain.model.entities.Business;
import com.restock.platform.profiles.domain.model.valueobjects.Avatar;
import com.restock.platform.profiles.domain.model.valueobjects.PersonName;
import com.restock.platform.profiles.domain.model.valueobjects.UserId;
import jakarta.persistence.*;

/**
 * Aggregate root for Profile
 */
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private PersonName name;

    @Embedded
    private Avatar avatar;

    private String email;
    private String address;
    private String phone;
    private String country;

    @Embedded
    private UserId userId;

    @Column(name = "business_id")
    private Integer businessId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "business_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Business business;

    public Profile() {
    }

    public Profile(Integer userId, Integer businessId) {
        this.userId = new UserId(userId);
        this.businessId = businessId;
        this.avatar = new Avatar();
        this.name = new PersonName();
        this.email = "";
        this.address = "";
        this.phone = "";
        this.country = "";
    }

    public Profile(String firstName, String lastName, String avatar, String email,
                   String phone, String address, String country, Integer userId, Integer businessId) {
        this.name = new PersonName(firstName, lastName);
        this.avatar = new Avatar(avatar);
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.country = country;
        this.userId = new UserId(userId);
        this.businessId = businessId;
    }

    public Profile(CreateProfileCommand command) {
        this(command.userId(), command.businessId());
    }

    public void update(String firstName, String lastName, String avatar, String email,
                       String phone, String address, String country) {
        if (firstName != null && lastName != null)
            this.name = new PersonName(firstName, lastName);
        if (avatar != null) this.avatar = new Avatar(avatar);
        if (email != null) this.email = email;
        if (address != null) this.address = address;
        if (phone != null) this.phone = phone;
        if (country != null) this.country = country;
    }

    public String getFullName() {
        return name.getFullName();
    }

    public Integer getId() {
        return id;
    }

    public Avatar getAvatar() {
        return avatar;
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

    public String getCountry() {
        return country;
    }

    public UserId getUserId() {
        return userId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
