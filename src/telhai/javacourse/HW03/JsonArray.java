package telhai.javacourse.HW03;

import java.util.ArrayList;

public class JsonArray implements JsonValue {
    private ArrayList<JsonValue> array;

    public JsonArray(ArrayList<JsonValue> array) {
        this.array = array;
    }

    public ArrayList<JsonValue> getArray() {
        return array;
    }

    public void setArray(ArrayList<JsonValue> array) {
        this.array = array;
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        //return the value of the given index
        if (this.array.size() < i) throw new JsonQueryException("INDEX OUT OF BOUNDS");
        else return this.array.get(i);
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }


    @Override
    public String toString() {
        int counter = array.size();
        System.out.print("[");
        for (JsonValue i : array) {
            if (counter>1){
                System.out.print(i + ", ");
            }else System.out.print(i);
            counter--;
        }
        System.out.print("]");
        return "";
    }
}
