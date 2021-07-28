package by.ilyineugene.arrayapp.service;

public interface ArrayElementSorter<T extends Number> {

    public void bubbleSort(T[] array);

    public void shakerSort(T[] array);

    public void selectSort(T[] array);

}
