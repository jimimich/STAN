public class Noun {

    private String name;
    //Description must be brief and all lowercase
    private String description;
    private Boolean stanLike;

    public Noun(String name, String description, Boolean stanLike){

        this.name = name;
        this.description = description;
        this.stanLike = stanLike;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStanLike() {
        return stanLike;
    }
    public void setStanLike(Boolean stanLike) {
        this.stanLike = stanLike;
    }

    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Like? " + stanLike;
    }

    

    
    
}
