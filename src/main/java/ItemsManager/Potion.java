package ItemsManager;

public class Potion extends Item {
    protected int effect;

    @Override
    protected void setSpecificAttributes(ItemBuilder itemBuilder) {

    }

    @Override
    public String[] getData() {
        return new String[]{type, name, grade, desc, Integer.toString(count), Integer.toString(effect)};
    }
}
