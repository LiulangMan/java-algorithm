package Spring.User;

public class User {
    private int id;
    private String name;
    private String address;


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public User(){

    }
    public User(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public User queryUserById(int id){
        User user = null;
        switch (id){
            case 1: user = new User(1,"xxx","china1");break;
            case 2: user = new User(2,"xyx","china2");break;
            case 3: user = new User(3,"xzx","china3");break;

        }

        return user;
    }
}
