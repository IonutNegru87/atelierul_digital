package com.inegru.android.atelieruldigital.helloworld.week8;


import com.google.gson.annotations.SerializedName;

/**
 * Data class describing a user - must match the JSON from the API end point.
 *
 * This model should resemble the JSON describing the user.
 * Utility: use <a href="http://www.jsonschema2pojo.org/">http://www.jsonschema2pojo.org/</a>
 * to generate a POJO from a json.
 *
 * This data class also relies on
 * <a href="https://github.com/square/retrofit/tree/master/retrofit-converters/gson">GSON Converter</a>
 * to automatically convert from a JSON to this POJO.
 */
class User {

    // The annotation allows to change the field name. If the json key matches the field name
    // than the annotation is not needed.
    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    @SerializedName("phone")
    private String phone;

    @SerializedName("website")
    private String website;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
