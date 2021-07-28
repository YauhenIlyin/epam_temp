package by.ilyineugene.arrayapp.entity;

import by.ilyineugene.arrayapp.exception.OperationArrayException;

import java.util.Arrays;

public class EntityArray<T extends Number> {

    private T[] array;

    public EntityArray() {

    }

    //на код ревью сказано, что вместо T[] лучше использовать T..., но не объяснено для чего
    public EntityArray(T[] array) {
        if (array != null) {
            this.array = Arrays.copyOf(array, array.length);
        }
    }

    public void setArray(T[] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    public T[] getArray() {
        return array;
    }

    public T[] getArrayCopy() {
        return Arrays.copyOf(array, array.length);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + " : " + array.getClass().getSimpleName() +
                " " + Arrays.toString(array);
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || this.getClass() != object.getClass()) {
            return false;
        }
        EntityArray comparedEntityArray = (EntityArray) object;
        if (this.array == null && comparedEntityArray.getArray() != null || this.array != null && comparedEntityArray.getArray() == null) {
            return false;
        }
        if (this.array == null && comparedEntityArray.getArray() == null) {
            return true; //должно быть true, даже при использовании дженериков разных типов, т.к. произойдет "стирание"
        }
        if (this.array.getClass() != comparedEntityArray.getArray().getClass()) {
            return false;
        }
        if (this.array.length != comparedEntityArray.getArray().length) {
            return false;
        }
        int index = 0;
        while (index < this.array.length) {
            if (this.array[index].doubleValue() != comparedEntityArray.getArray()[index].doubleValue()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hashCodeOfArray = 0;
        hashCodeOfArray += this.getClass().getName().hashCode();
        hashCodeOfArray += (array != null ? array.getClass().getSimpleName().hashCode() : 0);
        for (int index = 0; index < array.length; index++) {
            hashCodeOfArray += array[index].doubleValue() * 1.04;
        }
        return hashCodeOfArray;
    }

}
