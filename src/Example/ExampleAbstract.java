
package Example;

public abstract class ExampleAbstract {
    
    public String id;
    
    public ExampleAbstract(String id)
    {
        this.id = id;
    }
    
    public abstract void display();
    
    public String getId()
    {
        return id;
    }
}
