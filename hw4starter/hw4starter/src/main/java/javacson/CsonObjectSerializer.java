package javacson;

import java.lang.reflect.Modifier;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class CsonObjectSerializer {
    public String serialize(List<Object> objects) {
        // TODO
        String output = "";

        // code for each plate (each unique class)
        List<Object> clonedObjects = new ArrayList<Object>();
        clonedObjects.addAll(objects);
        sortObjectList(clonedObjects);

        List<Class> classList = new ArrayList<Class>();
        for (int objectIndex = 0; objectIndex < objects.size(); objectIndex++) {
            Class reflectClass = clonedObjects.get(objectIndex).getClass();
            if (classList.contains(reflectClass)) {
                continue;
            } else {
                classList.add(reflectClass);
            }

            output = output + CsonCodepoints.TYPE_DEF;

            output += reflectClass.getSimpleName();
            Field[] fields = reflectClass.getFields();
            List<Field> fieldsList = Arrays.asList(fields);
            sortFieldList(fieldsList);

            for (int i = 0; i < fieldsList.size(); i++) {
                // have to reorder fields

                int fieldModifier = fieldsList.get(i).getModifiers();
                if (!Modifier.isPublic(fieldModifier)) {
                    continue;
                }
                if (Modifier.isStatic(fieldModifier)) {
                    continue;
                }
                output += CsonCodepoints.FIELD_DEF;
                // System.out.println(fields[i].getName());
                output += fieldsList.get(i).getName();
                output += CsonCodepoints.FIELD_DEF_TYPE;
                // System.out.println(fields[i].getType());
                // output+=fieldsList.get(i).getAnnotatedType();
                if (fieldsList.get(i).getType() == int.class 
                    || fieldsList.get(i).getType() == Integer.class) {
                    output += "int32";
                } else if (fieldsList.get(i).getType() == float.class 
                    || fieldsList.get(i).getType() == Float.class) {
                    output += "float32";
                } else if (fieldsList.get(i).getType() == long.class 
                    || fieldsList.get(i).getType() == Long.class) {
                    output += "int64";
                } else if (fieldsList.get(i).getType() == String.class) {
                    output += "string";
                } else if (fieldsList.get(i).getType() == boolean.class) {
                    output += "boolean";
                } else if (fieldsList.get(i).getType() == double.class 
                    || fieldsList.get(i).getType() == Double.class) {
                    output += "float64";
                } else {
                    throw new CsonSerializationError("unchecked excepting");
                }
            }
        }
        output += CsonCodepoints.CSON_SCHEMA_DATA_SEP;

        // code for each object
        for (int objectIndex = 0; objectIndex < objects.size(); objectIndex++) {
            Class reflectClass = objects.get(objectIndex).getClass();
            Field[] fields = reflectClass.getFields();
            List<Field> fieldsList = Arrays.asList(fields);
            sortFieldList(fieldsList);

            output += CsonCodepoints.OBJECT_VAL;
            output += reflectClass.getSimpleName();

            for (int i = 0; i < fields.length; i++) {
                int fieldModifier = fieldsList.get(i).getModifiers();
                if (!Modifier.isPublic(fieldModifier)) {
                    continue;
                }
                if (Modifier.isStatic(fieldModifier)) {
                    continue;
                }
                output += CsonCodepoints.FIELD_VAL;
                try {
                    // System.out.println(fields[i].get(objects.get(objectIndex)));
                    String addingString = "";
                    addingString += fieldsList.get(i).get(objects.get(objectIndex));
                    System.out.println((fieldsList.get(i).get(objects.get(objectIndex))));
                    if (addingString.equals("true")) {
                        output += CsonCodepoints.TRUE;
                        // output+="something something";
                    } else if (addingString.equals("false")) {
                        output += CsonCodepoints.FALSE;
                    } else {
                        output += fieldsList.get(i).get(objects.get(objectIndex));
                    }
                } catch (IllegalArgumentException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return output;
    }

    public String serialize(Object object) {
        // TODO
        // doing it for a single object
        String output = "";

        Class reflectClass = object.getClass();
        output += output + CsonCodepoints.TYPE_DEF;
        output += reflectClass.getSimpleName();
        Field[] fields = reflectClass.getFields();
        List<Field> fieldsList = Arrays.asList(fields);
        sortFieldList(fieldsList);

        for (int i = 0; i < fieldsList.size(); i++) {
            // have to reorder fields
            int fieldModifier = fieldsList.get(i).getModifiers();
            if (!Modifier.isPublic(fieldModifier)) {
                continue;
            }
            if (Modifier.isStatic(fieldModifier)) {
                continue;
            }
            output += CsonCodepoints.FIELD_DEF;
            output += fieldsList.get(i).getName();
            output += CsonCodepoints.FIELD_DEF_TYPE;

            if (fieldsList.get(i).getType() == int.class 
                || fieldsList.get(i).getType() == Integer.class) {
                output += "int32";
            } else if (fieldsList.get(i).getType() == float.class 
                || fieldsList.get(i).getType() == Float.class) {
                output += "float32";
            } else if (fieldsList.get(i).getType() == long.class 
                || fieldsList.get(i).getType() == Long.class) {
                output += "int64";
            } else if (fieldsList.get(i).getType() == String.class) {
                output += "string";
            } else if (fieldsList.get(i).getType() == boolean.class) {
                output += "boolean";
            } else if (fieldsList.get(i).getType() == double.class 
                || fieldsList.get(i).getType() == Double.class) {
                output += "float64";
            } else {
                throw new CsonSerializationError("unchecked excepting");
            }
        }

        output += CsonCodepoints.CSON_SCHEMA_DATA_SEP;

        output += CsonCodepoints.OBJECT_VAL;
        output += reflectClass.getSimpleName();
        for (int i = 0; i < fields.length; i++) {
            int fieldModifier = fieldsList.get(i).getModifiers();
            if (!Modifier.isPublic(fieldModifier)) {
                continue;
            }
            if (Modifier.isStatic(fieldModifier)) {
                continue;
            }
            output += CsonCodepoints.FIELD_VAL;
            try {
                // System.out.println(fields[i].get(objects.get(objectIndex)));
                String addingString = "";
                addingString += fieldsList.get(i).get(object);
                System.out.println((fieldsList.get(i).get(object)));
                if (addingString.equals("true")) {
                    output += CsonCodepoints.TRUE;
                    // output+="something something";
                } else if (addingString.equals("false")) {
                    output += CsonCodepoints.FALSE;
                } else {
                    output += fieldsList.get(i).get(object);
                }
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return output;
    }

    public void sortFieldList(List<Field> fieldsList) {
        Collections.sort(fieldsList, new Comparator<Field>() {

            public int compare(Field o1, Field o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void sortObjectList(List<Object> clonedObjects) {
        // System.out.println(clonedObjects.get(0).getClass().getSimpleName());
        Collections.sort(clonedObjects, new Comparator<Object>() {
            public int compare(Object o1, Object o2) {
                return (o1.getClass().getSimpleName()).compareTo(o2.getClass().getSimpleName());
            }
        });
    }
}