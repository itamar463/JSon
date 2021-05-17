package telhai.javacourse.HW03;

import java.util.ArrayList;

public class JsonArray implements JsonValue{
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
        if(this.array.size()<i) throw new JsonQueryException("INDEX OUT OF BOUNDS");
        else return this.array.get(i);
    }

    @Override
    public JsonValue get(String s) {
        return null;
    }



    @Override
    public String toString() {
        return "" + array;
    }
}
