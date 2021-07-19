package 笔试区._temp_diction;


class _N <T>{
    private T key;
    _N(T key){
        this.key = key;
    }
    void _print(){
        System.out.println(this.key);
    }

}


public class _T {
    public static void main(String[] args) {
        _N<String> jk = new _N<>("abcdefg");
        jk._print();
    }
}
