package guru.qa.jsonfile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class InfoHero {

    public String name;
    public int age;
    public String secretIdentity;
    @SerializedName("powers")
    public List<String> powers;
    public Address address;

}

