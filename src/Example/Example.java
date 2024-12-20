
package Example;

public class Example extends ExampleAbstract {
    
    private String id;
    
    public Example(String name, String id)
    {
        super(name);
        
        this.id = id;
    }

    @Override
    public void display() {
        System.out.println(name + "\n" + id);
    }
}
