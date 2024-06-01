import java.lang.reflect.Field;

import itemInfos.Item;
import itemInfos.ItemBuilder;

public class TemplateMethodTEST {
    public static void main(String[] args) {
        Item test1 = new ItemBuilder()
            .type("Equipment")
            .name("equipmentTEST")
            .grade("Common")
            .desc("NONE")
            .option1(20)
            .build();

        Item test2 = new ItemBuilder()
            .type("Material")
            .name("materialTEST")
            .grade("Uncommon")
            .desc("NONE")
            .build();

        Item test3 = new ItemBuilder()
            .type("Potion")
            .name("potionTEST")
            .grade("Eqic")
            .desc("NONE")
            .option1(30)
            .build();

        Class<?> clazz;
        Field[] fields;

        clazz = test1.getClass();
        fields = clazz.getDeclaredFields();
        for(Field field : fields){
            System.out.println("장비 속성 : " + field.getName() + " = " + test1.getOption1());
        }

        clazz = test2.getClass();
        fields = clazz.getDeclaredFields();
        for(Field field : fields){
            System.out.println("재료 속성 : " + field.getName()+ " = " + test2.getOption1());
        }

        clazz = test3.getClass();
        fields = clazz.getDeclaredFields();
        for(Field field : fields){
            System.out.println("포션 속성 : " + field.getName()+ " = " + test3.getOption1());
        }

    }
}
