package com.apidemo.service;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.aspectj.bridge.IMessage;
import org.aspectj.bridge.Message;

@Getter
@Setter
public class RegistrationDto {
    private long id;
    @NotNull (message = "Cannot be null")
    @Size(min = 2,max = 10, message = "Should be between 2 to 10 characters")
    private String name;
    @Email(message = "Invalid email")
    private String emailId;
    @NotNull (message = "Cannot be null")
    @Size(min = 10,max = 10, message = "Should be 10 digits")
    private String mobile;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}