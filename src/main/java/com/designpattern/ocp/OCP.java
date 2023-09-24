package com.designpattern.ocp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

enum Color {
    RED, GREEN, BLUE
}

enum Size {
    SMALL, MEDIUM, HUGE
}

interface Specification<T> {
    boolean isSpecified(T item);
}

interface Filter<T> {

    List<T> filter(List<T> products);
}

class Product {
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), name + " " + color + " " + size);
    }
}

class ProductFilter { //Traditional Approach
    public List<Product> colorFilter(List<Product> productList, Color color) {
        return productList.stream().filter(product -> product.color == color).collect(Collectors.toList());
    }

    public List<Product> sizeAndColorFilter(List<Product> productList, Size size, Color color) {
        return productList.stream().filter(product -> product.size == size && product.color == color).collect(Collectors.toList());
    }
}

class ColorFilterNew implements Filter<Product>, Specification<Product> {
    Color color;

    public ColorFilterNew(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSpecified(Product item) {
        return item.color == color;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        return products.stream().filter(this::isSpecified).collect(Collectors.toList());
    }
}

class SizeAndColorNew implements Filter<Product>, Specification<Product> {
    public Size size;
    public Color color;

    public SizeAndColorNew(Size size, Color color) {
        this.size = size;
        this.color = color;
    }

    @Override
    public boolean isSpecified(Product item) {
        return item.size == size && item.color == color;
    }

    @Override
    public List<Product> filter(List<Product> products) {
        return products.stream().filter(this::isSpecified).collect(Collectors.toList());
    }
}

public class OCP {
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(new Product("Apple", Color.RED, Size.SMALL), new Product("Watermelon", Color.GREEN, Size.HUGE),
                new Product("Watermelon", Color.GREEN, Size.MEDIUM));
        ProductFilter productFilter = new ProductFilter();
        List<Product> greenProductList = productFilter.colorFilter(productList, Color.GREEN);
        System.out.println("--old Filter for Color--");
        greenProductList.forEach(System.out::println);
        ColorFilterNew colorFilterNew = new ColorFilterNew(Color.GREEN);
        List<Product> newFilteredProductForColor = colorFilterNew.filter(productList);
        System.out.println("--new Filter for Color--");
        newFilteredProductForColor.forEach(System.out::println);
        SizeAndColorNew sizeAndColorNew= new SizeAndColorNew(Size.MEDIUM, Color.GREEN);
        List<Product> newFilteredProductForSizeAndColor = sizeAndColorNew.filter(productList);
        System.out.println("--new Filter for Size And Color--");
        newFilteredProductForSizeAndColor.forEach(System.out::println);
    }
}
