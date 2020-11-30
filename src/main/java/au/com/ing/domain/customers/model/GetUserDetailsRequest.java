package au.com.ing.domain.customers.model;

import javax.validation.constraints.*;

public class GetUserDetailsRequest {

    @Min(1)
    @Max(5)
    @NotNull
    private long userID;


    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }
}