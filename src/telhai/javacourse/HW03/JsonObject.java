package telhai.javacourse.HW03;

import java.util.Map;
import java.util.function.BiConsumer;

public class JsonObject implements JsonValue{
    private Map<String,JsonValue> object;

    public JsonObject(Map<String, JsonValue> object) {
        this.object = object;
    }

    public Map<String, JsonValue> getObject() {
        return object;
    }

    public void setObject(Map<String, JsonValue> object) {
        this.object = object;
    }


    @Override
    public JsonValue get(int i) {
        return null;
    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        //return the value of the given key
        if (object.containsKey(s)){
            return object.get(s);
        }
        throw new JsonQueryException("KEY NOT FOUND!");
    }



    @Override
    public String toString() {
        int countDown =  this.object.size();
        System.out.print("{");
        for (Map.Entry me : object.entrySet()) {

            System.out.print(me.getKey() + " : ");
            System.out.print( me.getValue() + " ");
           countDown--;
           if (countDown==0){
               System.out.print("}");
           }
           else System.out.println();

        }
        return "" ;
    }
}
