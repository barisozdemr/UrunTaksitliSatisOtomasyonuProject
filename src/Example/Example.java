
package Example;

public class Example extends ExampleAbstract {
    
    private String name;
    
    public Example(String id, String name)
    {
        super(id);
        
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println(id + "\n" + name);
    }
}
