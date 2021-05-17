package telhai.javacourse.HW03;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonBuilder implements JsonValue {
    private CharScanner charScanner;
    private JsonValue value;
    private ArrayList<Character> digits = null;

    public JsonBuilder(File file) throws FileNotFoundException, JsonQueryException, JsonSyntaxException, ParseException {
        this.charScanner = new CharScanner(file);
        this.digits = new ArrayList<Character>(List.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));


        //instead of constructor menu everything in the func below!
        this.value = parseValue();


    }

    public JsonValue parseValue() throws JsonSyntaxException, ParseException {
        //just for choosing which kind of value!

        char jsonValueStart = this.charScanner.peek();
        if (jsonValueStart == '-' || digits.contains(jsonValueStart)) {
            return parseNumber();
        }
        switch (jsonValueStart) {
            case '"':
                return parseString();
            case '{':
                return parseObject();
            case '[':
                return parseArray();

        }
        return null;
    }


    public JsonString parseString() throws JsonSyntaxException {
        StringBuilder s = new StringBuilder();
        charScanner.next();
        while (charScanner.hasNext()) {
            if (charScanner.peek() != '\\' && charScanner.peek() != '"') {
                s.append(charScanner.peek());
                charScanner.next();
            } else if (charScanner.peek() == '\\') {
                charScanner.next();
                if (charScanner.peek() == '"' || charScanner.peek() == '\\') {
                    s.append(charScanner.peek());
                    charScanner.next();
                } else throw new JsonSyntaxException("INVALID SYNTAX");
            }
            if (charScanner.peek() == '"') {
                charScanner.next();
                return new JsonString(s.toString());
            }

        }
        throw new JsonSyntaxException("INVALID SYNTAX");
    }

    public JsonNumber parseNumber() throws JsonSyntaxException, ParseException {
        StringBuilder number = new StringBuilder();
        int dotCounter = 0;
        int eCounter = 0;
        number.append(charScanner.peek());
        if (charScanner.peek() == '-') {
            charScanner.next();
            if (!digits.contains(charScanner.peek()))
                throw new JsonSyntaxException("INVALID NUMBER");
        } else {
            charScanner.next();
        }
        while (charScanner.hasNext()) {
            if (charScanner.peek() == '.') {
                dotCounter++;
                number.append(charScanner.peek());
                charScanner.next();
                if (dotCounter > 1) throw new JsonSyntaxException("INVALID NUMBER");
                if (!digits.contains(charScanner.peek())) throw new JsonSyntaxException("INVALID NUMBER");

            }
            if (digits.contains(charScanner.peek())) {
                number.append(charScanner.next());
                if (charScanner.peek() == 'e' || charScanner.peek() == 'E') {
                    eCounter++;
                    number.append(charScanner.next());
                    if (charScanner.peek() == '-' || charScanner.peek() == '+') number.append(charScanner.next());

                }
            } else if (charScanner.peek() == 'e' || charScanner.peek() == 'E') {
                eCounter++;
                if (eCounter > 1) throw new JsonSyntaxException("INVALID NUMBER");
            } else break;
            //charScanner.next();
        }
        String str = number.toString();
        if (str.contains("e") || str.contains("E")) {
            BigDecimal bd = new BigDecimal(str);
            return new JsonNumber((Number) bd);
        }

        if (number.charAt(0) == '-') {
            float f = Float.parseFloat(str);
            Number n = (Number) f;
            return new JsonNumber(n);
        }


        Number n = NumberFormat.getInstance().parse(str);
        return new JsonNumber(n);
    }

    public JsonObject parseObject() throws JsonSyntaxException, ParseException {
        //parse object func!!
        Map<String, JsonValue> object = new HashMap<String, JsonValue>();
        while (charScanner.hasNext()) {
            if (charScanner.peek() == '}') break;
            charScanner.next();
            String key = parseString().toString();
            if (charScanner.peek() != ':') throw new JsonSyntaxException("INVALID OBJECT");
            charScanner.next();
            JsonValue value = parseValue();
            object.put(key, value);
            if (charScanner.peek() != ',' && charScanner.peek() != '}') throw new JsonSyntaxException("INVALID OBJECT");
        }
        return new JsonObject(object);
    }


    public JsonArray parseArray() throws JsonSyntaxException, ParseException, IndexOutOfBoundsException {
        ArrayList<JsonValue> arrayList = new ArrayList<JsonValue>();
        while (this.charScanner.hasNext() && this.charScanner.peek() != ']') {
            this.charScanner.next();
            if (this.charScanner.peek() != ',') {
                JsonValue value = this.parseValue();
                if (value == null) throw new JsonSyntaxException("VALUE IS NOT JSON VALUE");
                arrayList.add(value);
                continue;
            } else this.charScanner.next();
            if (this.charScanner.peek() == ',') throw new JsonSyntaxException("TO MANY --> , ");
        }
        if (!this.charScanner.hasNext()) throw new JsonSyntaxException("ERROR");
        this.charScanner.next();
        return new JsonArray(arrayList);
    }

    @Override
    public JsonValue get(int i) throws JsonQueryException {
        return this.value.get(i);
    }

    @Override
    public JsonValue get(String s) throws JsonQueryException {
        return this.value.get(s);

    }


    @Override
    public String toString() {
        return value.toString();
    }
}
