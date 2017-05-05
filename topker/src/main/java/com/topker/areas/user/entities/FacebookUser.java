package com.topker.areas.user.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("facebook_user")
public class FacebookUser extends SocialUser {

    private final static String PROVIDER_NAME = "FACEBOOK";

    public FacebookUser() {
        super(PROVIDER_NAME);
    }
}
