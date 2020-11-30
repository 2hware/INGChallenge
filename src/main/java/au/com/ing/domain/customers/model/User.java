package au.com.ing.domain.customers.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Size ( min = 2 , message = "First name cannot be less than 2 characters")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size ( min = 2 , message = "Last name cannot be less than 2 characters")
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "gender")
    private String gender;

    @OneToOne
    private Address address;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
