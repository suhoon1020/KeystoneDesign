package ItemsManager;

public class Material extends Item{

    @Override
    public void setSpecificAttributes(ItemBuilder itemBuilder){

    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), "NONE"};
    }
}
