package ItemsManager;

public class Equipment extends Item{
    protected int defence;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {
        this.defence=itemBuilder.option1;
    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(defence)};
    }
}
