package 笔试区._temp_diction_8;

public class Person {
    Integer id;
    String name;
    String address;
    Person lover;

    public Person getLover() {
        return lover;
    }

    public void setLover(Person lover) {
        this.lover = lover;
    }

    public Person(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


       public void makeLove(){

        System.out.println(this.name+" make love with "+lover.name);
    }


    public static void main(String[] args) {
        Person liXueLian = new Person(1,"李雪莲","重庆");

        liXueLian.setLover(new Person(2,"钟继伟","重庆"));

        liXueLian.makeLove();
    }
}
